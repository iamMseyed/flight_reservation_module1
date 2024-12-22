package com.seyed.flight_reservation.controller;

import com.seyed.flight_reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.seyed.flight_reservation.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    } //constructor based injection recommended instead of using @Autowired

    //when clicking on login url
    @RequestMapping("/show-login-page")
    public String showLoginPage(){
        return "login/login";
    }

    //when showReg url is hit
    @RequestMapping("/show-reg") //when home page is viewed, url is http://localhost:8080/flights/showReg // in app.prop file we set flights directory
    public String showRegistration(){
        return "login/showReg";
    }

    //when user registers
    @RequestMapping("/save-reg")
    public String saveReg(@ModelAttribute() User user, ModelMap modelMap){ //used ModelAttribute because registration from matches with entity class
        userRepository.save(user);
        modelMap.addAttribute("successMsg","You have successfully registered!");
        return "login/login";
    }

    @RequestMapping("/verify-login")
    public String verifyLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password, ModelMap modelMap){ //requestparam("should be same name as in jsp form field")
        User user = userRepository.findByEmail(emailId); //this will return the user on the basis of email id provided if available in the db
//        System.out.println("Welcome: "+user.getEmail());
        if(user!=null){
            if(user.getEmail().equals(emailId) && user.getPassword().equals(password))
                return "findFlights";
            else {
                modelMap.addAttribute("errorMsg","Please enter valid details!");
                return "login/login";
            }
        }else {
            modelMap.addAttribute("errorMsg","Please enter valid details!");
            return "login/login";
        }
    }
}
