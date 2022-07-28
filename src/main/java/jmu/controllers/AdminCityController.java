package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.City;
import jmu.service.AdminCityService;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminCityController extends BaseController{
    @Autowired
    private AdminCityService adminCityService;
    @RequestMapping("/city")
    public ModelAndView cityPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myCity");
        PageInfo<City> pageInfo = adminCityService.selectAllCities(currentPage,10);
        modelAndView.addObject("cityPageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/city-edit")
    public ModelAndView cityEdit(City city,@RequestParam(value = "page") Integer currentPage){
        adminCityService.cityEdit(city);
        return cityPage(currentPage);
    }
    @RequestMapping("/city-delete")
    public ModelAndView cityDelete(City city,@RequestParam(value = "page") Integer currentPage){
        adminCityService.cityDelete(city);
        return cityPage(currentPage);
    }
    @RequestMapping("/add-city")
    public JsonResult<Void> addCity(City city){
        adminCityService.addCity(city);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/city-search")
    public ModelAndView citySearch(City city,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myCity");
        System.out.println(city);
        PageInfo<City> pageInfo = adminCityService.citySearch(city,currentPage,10);
        modelAndView.addObject("cityPageInfo",pageInfo);
        modelAndView.addObject("city_data",city);
        return modelAndView;
    }
    @RequestMapping("/query-city")
    public JsonResult<List<City>> queryCity(){
        List<City> list = adminCityService.queryAllCities();
        return new JsonResult<>(OK,list);
    }
}
