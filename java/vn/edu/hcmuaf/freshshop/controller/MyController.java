package vn.edu.hcmuaf.freshshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.hcmuaf.freshshop.service.AccountService;
import vn.edu.hcmuaf.freshshop.service.ProductService;

import java.sql.SQLException;

@Controller
public class MyController {
    @Autowired
    ProductService productService;

    @GetMapping(value = {"/", "/home", "/index"})
    public String homePage(Model model) {
        model.addAttribute("currentPage", "home");
        return "index";
    }

    @GetMapping("/login")
    public String redirectLogin(Model model) {
        model.addAttribute("currentPage", "home");
        model.addAttribute("errorLogin", null);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

//    @GetMapping("/shop")
//    public String shop(Model model) {
//        return "shop";
//    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            boolean isLogin = new AccountService().login(username, password);
            if (isLogin) {
                request.getSession().setAttribute("username", username);
                model.addAttribute("currentPage", "home");
                return "redirect:/";
            } else {
                model.addAttribute("username", username);
                model.addAttribute("errorLogin", true);
//                request.getRequestDispatcher("login.jsp").forward(request, response);
                return "redirect:/login";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
