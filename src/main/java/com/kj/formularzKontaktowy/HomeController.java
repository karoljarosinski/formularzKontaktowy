package com.kj.formularzKontaktowy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private MailService mailService;

    public HomeController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    @PostMapping("/send")
    public String sendEmail(Message message){
        mailService.sendMail(message.getSenderName(), message.getSenderEmail(), message.getContent());
        return "redirect:/";
    }
}
