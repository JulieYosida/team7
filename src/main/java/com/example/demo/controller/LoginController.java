package com.example.demo.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Bicycle;
import com.example.demo.entity.Pass;
import com.example.demo.entity.Users;
import com.example.demo.entity.travel;
import com.example.demo.repository.BicycleRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.PassRepository;
import com.example.demo.repository.TravelRepository;
import com.example.demo.service.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	

    @Autowired
    private MailService mailService;

    @Autowired
    LoginRepository loginRepository;
    
    @Autowired
    TravelRepository TravelRepository;
    
    @Autowired
    PassRepository PassRepository;
    
    @Autowired
    BicycleRepository BicycleRepository;

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
    public String checkResidence(String furigana1, String name, String addressFurigana, String address, String commuteRoute, String bicycleUse, String busRoute, HttpSession session) {
        session.setAttribute("furigana1", furigana1);
        session.setAttribute("name", name);
        session.setAttribute("addressFurigana", addressFurigana);
        session.setAttribute("address", address);
        session.setAttribute("commuteRoute", commuteRoute);
        session.setAttribute("bicycleUse", bicycleUse);
        session.setAttribute("busRoute", busRoute);
        return "checkresidence";
    }

    @RequestMapping(path = "/checkbicycle", method = RequestMethod.POST)
    public String checkBicycle(String applicationDate, String employeeNumber, String name, String nameFurigana,
                               String department, String address, String subsidyAmount, 
                               MultipartFile parkingProofPath, MultipartFile insuranceProofPath, HttpSession session,Model model) throws IOException {

		
    	
        session.setAttribute("applicationDate", applicationDate);
        session.setAttribute("employeeNumber", employeeNumber);
        session.setAttribute("name", name);
        session.setAttribute("nameFurigana", nameFurigana);
        session.setAttribute("department", department);
        session.setAttribute("address", address);
        session.setAttribute("subsidyAmount", subsidyAmount);

        if (parkingProofPath != null && !parkingProofPath.isEmpty()) {
        	byte[] byteData1 = parkingProofPath.getBytes();
    		String encodedImage1 = Base64.getEncoder().encodeToString(byteData1);
            session.setAttribute("parkingProofPath", encodedImage1);
        } else {
            session.setAttribute("parkingProofPath", "ファイルがアップロードされていません");
        }

        if (insuranceProofPath != null && !insuranceProofPath.isEmpty()) {
        	byte[] byteData2 = insuranceProofPath.getBytes();

    		String encodedImage2 = Base64.getEncoder().encodeToString(byteData2);
            session.setAttribute("insuranceProofPath", encodedImage2);
        } else {
            session.setAttribute("insuranceProofPath", "ファイルがアップロードされていません");
        }

        return "checkbicycle";
    }

    @RequestMapping(path = "/checkpass", method = RequestMethod.POST)
    public String checkpass(HttpSession session, 
    						String id,
    						String application_date, 
    						String employee_number,
    						String name, 
    						String furigana,
                            String address, 
                            String bus_route, 
                            String bus_period, 
                            String bus_fare, 
                            String train_route, 
                            String train_period, 
                            String train_fare, 
                            String total_fare) 
    {	session.setAttribute("id", id);
        session.setAttribute("application_date", application_date);
        session.setAttribute("employee_number", employee_number);
        session.setAttribute("name", name);
        session.setAttribute("furigana", furigana);
        session.setAttribute("address", address);
        session.setAttribute("bus_route", bus_route);
        session.setAttribute("bus_period", bus_period);
        session.setAttribute("bus_fare", bus_fare);
        session.setAttribute("train_route", train_route);
        session.setAttribute("train_period", train_period);
        session.setAttribute("train_fare", train_fare);
        session.setAttribute("total_fare", total_fare);
        
        
        return "checkpass";
    }

    @RequestMapping(path = "/checktravel", method = RequestMethod.POST)
    public String checkTravel(HttpSession session, 
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

        session.setAttribute("applicationDate", applicationDate);
        session.setAttribute("id", id);
        session.setAttribute("name", name);
        session.setAttribute("furigana", furigana);
        session.setAttribute("departureDate", departureDate);
        session.setAttribute("departureStation", departureStation);
        session.setAttribute("destinationStation", destinationStation);
        session.setAttribute("totalFare", totalFare);
        session.setAttribute("transportation", transportation);

        if (otherText != null && !otherText.trim().isEmpty()) {
            session.setAttribute("otherText", otherText);
        } else {
            session.setAttribute("otherText", null);
        }
        

        return "checktravel";
    }

    @RequestMapping(path = "/submitbicycle", method = RequestMethod.POST)
    public String submitBicycle(HttpSession session) {
    	Bicycle bicycle = new Bicycle();
    	bicycle.setApplicationDate(
    			session.getAttribute("applicationDate") != null 
    			? session.getAttribute("applicationDate").toString() 
    					: "未入力");
    	bicycle.setEmployeeNumber(
    			session.getAttribute("employeeNumber") != null
    			? session.getAttribute("employeeNumber").toString()
    					: "未入力");

    	bicycle.setName(
    			session.getAttribute("name") != null
    			? session.getAttribute("name").toString()
    					: "未入力");

    	bicycle.setNameFurigana(
    			session.getAttribute("nameFurigana") != null
    			? session.getAttribute("nameFurigana").toString()
    					: "未入力");

    	bicycle.setDepartment(
    			session.getAttribute("department") != null
    			? session.getAttribute("department").toString()
    					: "未入力");

    	bicycle.setAddress(
    			session.getAttribute("address") != null
    			? session.getAttribute("address").toString()
    					: "未入力");
        		
    	bicycle.setSubsidyAmount(
    			session.getAttribute("subsidyAmount") != null
				? session.getAttribute("subsidyAmount").toString()
						: "未入力");
        		
    	String base64String1 = (String) session.getAttribute("parkingProofPath");
    	byte[] imageBytes1 = Base64.getDecoder().decode(base64String1);
    	
    	
        		
    	
    			
				bicycle.setParkingProofPath(imageBytes1);
				
				String base64String2 = (String) session.getAttribute("insuranceProofPath");
				byte[] imageBytes2 = Base64.getDecoder().decode(base64String2);
				
    	
    	        bicycle.setInsuranceProofPath(imageBytes2);	
    	
    	BicycleRepository.save(bicycle);
    		
    		
    		
        return "redirect:/complete";
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public String submitResidence(HttpSession session) {
        System.out.println("データが送信されました");
        System.out.printf("フリガナ: %s, 氏名: %s, 住所フリガナ: %s, 住所: %s, 通勤経路: %s, 自転車: %s, バス: %s%n",
                          session.getAttribute("furigana1"), session.getAttribute("name"), session.getAttribute("addressFurigana"),
                          session.getAttribute("address"), session.getAttribute("commuteRoute"), session.getAttribute("bicycleUse"),
                          session.getAttribute("busRoute"));
        return "redirect:/complete";
    }

    @RequestMapping(path = "/submit2", method = RequestMethod.POST)
    public String submit2Residence(HttpSession session) {
        travel travel = new travel();
        travel.setId(Long.parseLong(session.getAttribute("id").toString()));
        travel.setApplicationDate(session.getAttribute("applicationDate").toString());
        travel.setName(session.getAttribute("name").toString());
        travel.setFurigana(session.getAttribute("furigana").toString());
        travel.setDepartureDate(session.getAttribute("departureDate").toString());
        travel.setDepartureStation(session.getAttribute("departureStation").toString());
        travel.setDestinationStation(session.getAttribute("destinationStation").toString());
        travel.setTotalFare(Double.parseDouble(session.getAttribute("totalFare").toString()));
        travel.setOtherText(session.getAttribute("otherText") != null ? session.getAttribute("otherText").toString() : "");
        TravelRepository.save(travel);
        return "redirect:/complete";
    }
    
    @RequestMapping(path = "/submitPass", method = RequestMethod.POST)
    public String submitPass(HttpSession session) {
        Pass pass = new Pass();

        pass.setApplicationDate(
            session.getAttribute("application_date") != null 
            ? session.getAttribute("application_date").toString() 
            : "未入力");

        pass.setName(
            session.getAttribute("name") != null 
            ? session.getAttribute("name").toString() 
            : "未入力");

        pass.setFurigana(
            session.getAttribute("furigana") != null 
            ? session.getAttribute("furigana").toString() 
            : "未入力");

        pass.setAddress(
            session.getAttribute("address") != null 
            ? session.getAttribute("address").toString() 
            : "未入力");

        pass.setBusRoute(
            session.getAttribute("bus_route") != null 
            ? session.getAttribute("bus_route").toString() 
            : "未入力");

        pass.setBusPeriod(
            session.getAttribute("bus_period") != null 
            ? session.getAttribute("bus_period").toString() 
            : "未入力");

        pass.setBusFare(
            session.getAttribute("bus_fare") != null 
            ? session.getAttribute("bus_fare").toString() 
            : "未入力");

        pass.setTrainRoute(
            session.getAttribute("train_route") != null 
            ? session.getAttribute("train_route").toString() 
            : "未入力");

        pass.setTrainPeriod(
            session.getAttribute("train_period") != null 
            ? session.getAttribute("train_period").toString() 
            : "未入力");

        pass.setTrainFare(
            session.getAttribute("train_fare") != null 
            ? session.getAttribute("train_fare").toString() 
            : "未入力");

        pass.setTotalFare(
            session.getAttribute("total_fare") != null 
            ? session.getAttribute("total_fare").toString() 
            : "未入力");
        
        pass.setEmployeeNumber(
            session.getAttribute("employee_number") != null
            ? Integer.parseInt(session.getAttribute("employee_number").toString())
            : 0);

        PassRepository.save(pass);
        String to = "shitianzun28@gmail.com";  // 宛先（固定）
        String subject = "新しい申請が届きました";  // 件名
        String text = "新しい申請が届きました。内容をご確認ください。";  // 本文

        // メール送信
        mailService.sendMail(to, subject, text);

        
        System.out.println("メール送信が完了しました。");
        
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
    public String compsubmit(HttpSession session) {
        session.setAttribute("message", "入力完了");
        return "redirect:/home";
    }

    @RequestMapping(path = "/guest", method = RequestMethod.POST)
    public String guestLogin(HttpSession session) {
        session.setAttribute("message", "ゲストとしてログインしました！");
        return "redirect:/route2";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String processLogin(String id, String password, HttpSession session) {
        session.setAttribute("id", id);
        session.setAttribute("password", password);

        Users user = loginRepository.findById(Integer.parseInt(id)).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/home";
        } else {
            session.setAttribute("message", "ログインに失敗しました");
            return "redirect:/login";
        }
    }
}