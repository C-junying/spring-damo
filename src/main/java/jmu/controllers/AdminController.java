package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.*;
import jmu.service.AdminService;
import jmu.service.impl.AdminServiceImpl;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    private AdminService adminService;




    @RequestMapping("/admin_user")
    public ModelAndView adminUserPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myUser");
        PageInfo<User> pageInfo = adminService.selectAllUser(currentPage,10);
        modelAndView.addObject("userPageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/user-delete")
    public ModelAndView userDelete(User user,@RequestParam(value = "page") Integer currentPage){
        adminService.userDelete(user);
        return adminUserPage(currentPage);
    }
    @RequestMapping("/user-search")
    public ModelAndView userSearch(User user,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myUser");
        System.out.println(user);
        PageInfo<User> pageInfo = adminService.usertSearch(user,currentPage,10);
        modelAndView.addObject("userPageInfo",pageInfo);
        modelAndView.addObject("user_data",user);
        return modelAndView;
    }
    @RequestMapping("/admin_add")
    public ModelAndView adminAdd(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myAdd");
        return modelAndView;
    }

}
