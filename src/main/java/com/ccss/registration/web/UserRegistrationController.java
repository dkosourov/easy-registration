package com.ccss.registration.web;

import com.ccss.registration.email.Mail;
import com.ccss.registration.model.User;
import com.ccss.registration.service.EmailService;
import com.ccss.registration.service.UserService;
import com.ccss.registration.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
            BindingResult result) {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        sendMessageWithPassword(userDto);
        return "redirect:/registration?success";
    }

    private void sendMessageWithPassword(UserRegistrationDto pUserDto) {
        Mail mail = new Mail();
        mail.setTo(pUserDto.getEmail());
        mail.setSubject("Welcome! Here is your password!");
        mail.setText(getGeneratedText(pUserDto.getPassword()));
        emailService.sendSimpleMessage(mail);
    }

    private String getGeneratedText(String pPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append("We are very glad you've registered on our site. ").append(
                "Here is your password: ").append(pPassword);
        return sb.toString();
    }

}