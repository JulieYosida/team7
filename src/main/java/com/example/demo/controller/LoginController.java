package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
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

    @RequestMapping(path = "/submitbicycle", method = RequestMethod.POST)
    public String submitBicycle(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute, Model model) {
        return "redirect:/home";
    }
    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public String submitResidence(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute) {
        
        System.out.println("データが送信されました");
        System.out.printf("フリガナ: %s, 氏名: %s, 住所フリガナ: %s, 住所: %s, 通勤経路: %s, 自転車: %s, バス: %s%n",
                          furigana1, name, addressFurigana, address, commuteRoute, bicycleUse, busRoute);


        return "redirect:/home";
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
