package jmu.controllers;

import jmu.pojo.User;
import jmu.service.UserService;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/login",""})
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/selectall")
    public String selectAll(Model model){
        List<User> list = userService.selectAll();
        model.addAttribute("userList",list);
        return "queryAll";
    }
//    http://localhost:8080/user/register?userID=123456&userName=zhang&userPassword=123456
    @PostMapping("/register")
    public JsonResult<Void> addUser(User user){
        userService.insertUser(user);
        return new JsonResult<>(OK);
    }
//http://localhost:8080/login?userID=123456&userPassword=123456
    @PostMapping("/login")
    public JsonResult<User> doLogin(User user, HttpSession session) {
        User result = userService.login(user);
        session.setAttribute("userID",result.getUserID());
        session.setAttribute("permission",result.getPermission());
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/home")
    public ModelAndView homePage(HttpSession session){
        System.out.println(getUserIDFromSession(session));
        System.out.println(getPermissionFromSession(session));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
/**
 * 使用@RequestBody总会报错，类型不匹配
 */