package vn.edu.hcmuaf.freshshop.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.hcmuaf.freshshop.service.AccountService;

import java.sql.SQLException;

@Controller
public class MyController {

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

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            boolean isLogin = new AccountService().login(username, password);
            if (isLogin) {
                request.getSession().setAttribute("username", username);
//                new CartServlet().doPost(request, response);
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
