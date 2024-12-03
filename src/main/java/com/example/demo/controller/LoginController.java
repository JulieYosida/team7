package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    @RequestMapping(path = "/route2", method = RequestMethod.GET)
    public String route2() {
        return "route2";
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
    @RequestMapping(path = "/complete", method = RequestMethod.GET)
    public String complete() {
        return "complete";
    }

    @RequestMapping(path = "/applybicycle", method = RequestMethod.GET)
    public String applybicycle() {
        return "applybicycle";
    }

    @RequestMapping(path = "/applypass", method = RequestMethod.GET)
    public String applypass() {
        return "applypass";
    }

    @RequestMapping(path = "/applytravel", method = RequestMethod.GET)
    public String applytravel() {
        return "applytravel";
    }

    @RequestMapping(path = "/residence", method = RequestMethod.GET)
    public String residence() {
        return "residence";
    }
    @RequestMapping(path = "/checkresidence", method = RequestMethod.POST)
    public String checkResidence(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute, Model model) {
        model.addAttribute("furigana1", furigana1);
        model.addAttribute("name", name);
        model.addAttribute("addressFurigana", addressFurigana);
        model.addAttribute("address", address);
        model.addAttribute("commuteRoute", commuteRoute);
        model.addAttribute("bicycleUse", bicycleUse);
        model.addAttribute("busRoute", busRoute);
        return "checkresidence";
    }
    @RequestMapping(path = "/checkbicycle", method = RequestMethod.POST)
    public String checkBicycle(String applicationDate, String employeeNumber, String name, String nameFurigana,
                               String department, String address, String subsidyAmount, 
                               MultipartFile parkingProof, MultipartFile insuranceProof, Model model) {

        model.addAttribute("applicationDate", applicationDate);
        model.addAttribute("employeeNumber", employeeNumber);
        model.addAttribute("name", name);
        model.addAttribute("nameFurigana", nameFurigana);
        model.addAttribute("department", department);
        model.addAttribute("address", address);
        model.addAttribute("subsidyAmount", subsidyAmount);

        // parkingProofのファイル名をモデルに追加
        if (parkingProof != null && !parkingProof.isEmpty()) {
            model.addAttribute("parkingProofFileName", parkingProof.getOriginalFilename());
        } else {
            model.addAttribute("parkingProofFileName", "ファイルがアップロードされていません");
        }

        // insuranceProofのファイル名をモデルに追加
        if (insuranceProof != null && !insuranceProof.isEmpty()) {
            model.addAttribute("insuranceProofFileName", insuranceProof.getOriginalFilename());
        } else {
            model.addAttribute("insuranceProofFileName", "ファイルがアップロードされていません");
        }

        return "checkbicycle";
    }
    @RequestMapping(path = "/checkpass", method = RequestMethod.POST)
    public String checkpass(Model model, String applicationDate, String id, String name, String furigana, 
                                String address, String busRoute, String busPeriod, String busFare, 
                                String trainRoute, String trainPeriod, String trainFare, String totalFare) {
        model.addAttribute("applicationDate", applicationDate);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("furigana", furigana);
        model.addAttribute("address", address);
        model.addAttribute("busRoute", busRoute);
        model.addAttribute("busPeriod", busPeriod);
        model.addAttribute("busFare", busFare);
        model.addAttribute("trainRoute", trainRoute);
        model.addAttribute("trainPeriod", trainPeriod);
        model.addAttribute("trainFare", trainFare);
        model.addAttribute("totalFare", totalFare);
        return "checkpass";
    }
    @RequestMapping(path = "/checktravel", method = RequestMethod.POST)
    public String checkTravel(Model model, 
                              String applicationDate, 
                              String id, 
                              String name, 
                              String furigana,
                              String departureDate, 
                              String departureStation, 
                              String destinationStation, 
                              String totalFare, 
                              @RequestParam(value = "transportation", required = false) String[] transportation,
                              @RequestParam(value = "otherText", required = false) String otherText) {
        // フォームから送信されたデータをModelに追加
        model.addAttribute("applicationDate", applicationDate);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("furigana", furigana);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("departureStation", departureStation);
        model.addAttribute("destinationStation", destinationStation);
        model.addAttribute("totalFare", totalFare);

        // 選択された交通機関をModelに追加
        model.addAttribute("transportation", transportation);

        // その他の交通機関が入力されている場合は追加
        if (otherText != null && !otherText.trim().isEmpty()) {
            model.addAttribute("otherText", otherText);
        } else {
            model.addAttribute("otherText", null);
        }

        // "checktravel"というテンプレートを返す
        return "checktravel";
    }

    @RequestMapping(path = "/submitbicycle", method = RequestMethod.POST)
    public String submitBicycle(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute, Model model) {
        return "redirect:/complete";
    }
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public String submitResidence(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute) {
        
        System.out.println("データが送信されました");
        System.out.printf("フリガナ: %s, 氏名: %s, 住所フリガナ: %s, 住所: %s, 通勤経路: %s, 自転車: %s, バス: %s%n",
                          furigana1, name, addressFurigana, address, commuteRoute, bicycleUse, busRoute);


        return "redirect:/complete";
    }
    @RequestMapping(path = "/sin", method = RequestMethod.POST)
    public String sins() {
        return "sin";
    }
    @RequestMapping(path = "/sins", method = RequestMethod.POST)
    public String sin() {
        return "redirect:/sin";
    }
    @RequestMapping(path = "/compsubmit", method = RequestMethod.POST)
    public String compsubmit(Model model) {
        // ゲストログインの場合の処理を追加
        // 必要であれば、ゲスト用の情報をModelに追加できます
        model.addAttribute("message", "入力完了");
        
        return "redirect:/home";
    }
    @RequestMapping(path = "/guest", method = RequestMethod.POST)
    public String guestLogin(Model model) {
        // ゲストログインの場合の処理を追加
        // 必要であれば、ゲスト用の情報をModelに追加できます
        model.addAttribute("message", "ゲストとしてログインしました！");
        
        // ゲスト用ダッシュボードに遷移
        return "redirect:/route2";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String processLogin(String id, String password, Model model, HttpSession session) {
        model.addAttribute("id", id);
        model.addAttribute("password", password);
        session.setAttribute("id", id);
        session.setAttribute("password", password);

        User user = loginRepository.findById(Integer.parseInt(id)).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("message", "ログインに失敗しました");
            return "redirect:/login";
        }
    }
}
