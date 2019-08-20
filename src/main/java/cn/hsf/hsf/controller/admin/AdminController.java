package cn.hsf.hsf.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author kaituozhe
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/login")
    public String selByAdmin(HttpSession session, Map<String,Object> map){
        session.setAttribute("loginUser", 1);
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login.html";
    }

}
