package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.City;
import jmu.service.AdminService;
import jmu.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    private AdminService adminService;

    @RequestMapping("/city")
    public ModelAndView cityPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myCity");
        PageInfo<City> pageInfo = adminService.selectAllCities(currentPage,10);
        modelAndView.addObject("cityPageInfo",pageInfo);
        return modelAndView;
    }
}
