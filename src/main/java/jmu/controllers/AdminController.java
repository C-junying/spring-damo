package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.Airport;
import jmu.pojo.City;
import jmu.pojo.User;
import jmu.service.AdminService;
import jmu.service.impl.AdminServiceImpl;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @RequestMapping("/city-edit")
    public ModelAndView cityEdit(City city,@RequestParam(value = "page") Integer currentPage){
        adminService.cityEdit(city);
        return cityPage(currentPage);
    }
    @RequestMapping("/city-delete")
    public ModelAndView cityDelete(City city,@RequestParam(value = "page") Integer currentPage){
        adminService.cityDelete(city);
        return cityPage(currentPage);
    }
    @RequestMapping("/city-search")
    public ModelAndView citySearch(City city,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myCity");
        System.out.println(city);
        PageInfo<City> pageInfo = adminService.citySearch(city,currentPage,10);
        modelAndView.addObject("cityPageInfo",pageInfo);
        modelAndView.addObject("city_data",city);
        return modelAndView;
    }
    @RequestMapping("/airport")
    public ModelAndView airportPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myAirport");
        PageInfo<Airport> pageInfo = adminService.selectAllAirports(currentPage,20);
        modelAndView.addObject("airportPageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/airport-edit")
    public ModelAndView airportEdit(Airport airport,@RequestParam(value = "page") Integer currentPage){
        adminService.airportEdit(airport);
        return airportPage(currentPage);
    }
    @RequestMapping("/airport-delete")
    public ModelAndView airportDelete(Airport airport,@RequestParam(value = "page") Integer currentPage){
        adminService.airportDelete(airport);
        return airportPage(currentPage);
    }
    @RequestMapping("/airport-search")
    public ModelAndView airportSearch(Airport airport,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myAirport");
        System.out.println(airport);
        PageInfo<Airport> pageInfo = adminService.airportSearch(airport,currentPage,20);
        modelAndView.addObject("airportPageInfo",pageInfo);
        modelAndView.addObject("airport_data",airport);
        return modelAndView;
    }
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
