package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class RouteController {

    // 定数としてAPIキーを設定します（値を設定してください）
    private final String API_KEY = "test_QMMh4JZJgnC"; // 駅すぱぁとAPIのキーを設定してください。

    // GETリクエストで統合された画面を表示
    @GetMapping("/route")
    public String getRoutePage(Model model) {
        return "route"; // 統合されたHTMLファイル
    }

    /**
     * 駅名から駅コードを取得するメソッド
     * @param stationName - ユーザーが入力した駅名
     * @return 駅コードと駅名のリストをJSON形式で返す
     */
    @PostMapping("/search-station")
    @ResponseBody
    public List<Map<String, Object>> searchStation(@RequestParam String stationName) {
        String apiUrl = "https://mixway.ekispert.jp/v1/json/station/light?key=" + API_KEY + "&name=" + stationName;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, ?> response = restTemplate.getForObject(apiUrl, Map.class);
            Map<String, ?> resultSet = parseMap(response, "ResultSet");
            List<Map<String, ?>> PointCodeList = parseList(resultSet, "Point");
            List<Map<String, Object>> PointCodeList2 = new ArrayList<>();
            PointCodeList.forEach(Point -> {
                Map<String, Object> StationMap = new HashMap<>();
                Map<String, ?> Station = parseMap(Point, "Station");
                StationMap.put("code", Station.get("code"));
                StationMap.put("Name", Station.get("Name"));
                PointCodeList2.add(StationMap);
            });
            return PointCodeList2; // JSON形式で返す
        } catch (Exception e) {
            return new ArrayList<>(); // 空リストを返す
        }
    }

    /**
     * 駅コードを使って経路探索を行うメソッド
     * @param fromCode - 出発駅のコード
     * @param toCode - 到着駅のコード
     * @param departureTime - 出発時刻
     * @param model - ビューにデータを渡すためのモデルオブジェクト
     * @return レスポンスをレンダリングするビュー名
     */
    @PostMapping("/search-route")
    public String searchRoute(@RequestParam String fromCode, @RequestParam String toCode,
                              @RequestParam String departureTime, Model model) {
        String Time = departureTime.replace(":", "");

        String subApiUrl = "https://mixway.ekispert.jp/v1/json/toolbox/course/condition?key=" + API_KEY + "&limitedExpress=never;";
        RestTemplate subRestTemplate = new RestTemplate();
        Map<String, ?> subResponse = subRestTemplate.getForObject(subApiUrl, Map.class);
        Map<String, ?> subResultSet = parseMap(subResponse, "ResultSet");
        String Condition = (String) subResultSet.get("Condition");

        String apiUrl = "http://api.ekispert.jp/v1/json/search/course/extreme?key=" + API_KEY
                + "&viaList=" + fromCode + ":" + toCode + "&time=" + Time + "&conditionDetail=" + Condition
                + "&sort=price";

        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, ?> response = restTemplate.getForObject(apiUrl, Map.class);

            Map<String, ?> resultSet = parseMap(response, "ResultSet");
            List<Map<String, ?>> coursList = parseList(resultSet, "Course");
            List<Map<String, Object>> coursList2 = new ArrayList<>();

            coursList.forEach(cours -> {
                Map<String, Object> map1 = new HashMap<>();
                coursList2.add(map1);

                List<Map<String, ?>> PriceList = parseList(cours, "Price");
                for (int i = 0; i < PriceList.size(); i++) {
                    if ("FareSummary".equals(PriceList.get(i).get("kind"))) {
                        map1.put("FareSummary", String.valueOf(PriceList.get(i).get("Oneway")));
                    }
                    if ("Teiki1Summary".equals(PriceList.get(i).get("kind"))) {
                        map1.put("Teiki1Summary", String.valueOf(PriceList.get(i).get("Oneway")));
                    }
                    if ("Teiki3Summary".equals(PriceList.get(i).get("kind"))) {
                        map1.put("Teiki3Summary", String.valueOf(PriceList.get(i).get("Oneway")));
                    }
                    if ("Teiki6Summary".equals(PriceList.get(i).get("kind"))) {
                        map1.put("Teiki6Summary", String.valueOf(PriceList.get(i).get("Oneway")));
                    }
                }

                if (cours.get("Teiki") != null) {
                    Map<String, ?> Teiki = parseMap(cours, "Teiki");
                    map1.put("displayRoute", (String) Teiki.get("DisplayRoute"));
                }

                Map<String, ?> Route = parseMap(cours, "Route");
                List<Map<String, ?>> LineList = parseList(Route, "Line");
                List<Map<String, ?>> PointList = parseList(Route, "Point");

                List<Map<String, ?>> retLine = new ArrayList<>();

                for (int i = 0; i < PointList.size(); i++) {
                    Map<String, String> retLineMap = new HashMap<>();

                    Map<String, ?> station = parseMap(PointList.get(i), "Station");
                    if (station != null) {
                        String stationName = (String) station.get("Name");

                        if (i == 0) {
                            retLineMap.put("FromStationName", stationName);
                        }
                        if (i == PointList.size() - 1) {
                            retLineMap.put("ToStationName", stationName);
                        }
                        if (i > 0 && i < PointList.size() - 1) {
                            retLineMap.put("StationName", stationName);
                        }
                    }

                    if (i < LineList.size()) {
                        String lineName = (String) LineList.get(i).get("Name");
                        retLineMap.put("LineName", lineName);
                    }

                    retLine.add(retLineMap);
                }

                map1.put("LineList", retLine);
            });

            
            model.addAttribute("coursList2", coursList2);

        } catch (Exception e) {
            model.addAttribute("error", "経路検索中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
            return "route"; // 統合されたビューにエラーを表示
        }
        return "route"; // 統合されたビュー名
    }

    @SuppressWarnings("unchecked")
    Map<String, ?> parseMap(Map<String, ?> map, String key) {
        return (Map<String, ?>) map.get(key);
    }

    @SuppressWarnings("unchecked")
    List<Map<String, ?>> parseList(Map<String, ?> map, String key) {
        if (map.get(key) instanceof List) {
            return (List<Map<String, ?>>) map.get(key);
        } else {
            List<Map<String, ?>> list = new ArrayList<>();
            list.add((Map<String, ?>) map.get(key));
            return list;
        }
    }
}
