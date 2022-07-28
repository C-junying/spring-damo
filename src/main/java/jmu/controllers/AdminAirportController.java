package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.Airport;
import jmu.service.AdminAirportService;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAirportController extends BaseController{
    @Autowired
    private AdminAirportService adminAirportService;
    @RequestMapping("/airport")
    public ModelAndView airportPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myAirport");
        PageInfo<Airport> pageInfo = adminAirportService.selectAllAirports(currentPage,20);
        modelAndView.addObject("airportPageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/airport-edit")
    public ModelAndView airportEdit(Airport airport,@RequestParam(value = "page") Integer currentPage){
        adminAirportService.airportEdit(airport);
        return airportPage(currentPage);
    }
    @RequestMapping("/airport-delete")
    public ModelAndView airportDelete(Airport airport,@RequestParam(value = "page") Integer currentPage){
        adminAirportService.airportDelete(airport);
        return airportPage(currentPage);
    }
    @RequestMapping("/add-airport")
    public JsonResult<Void> addAirport(Airport airport){
        adminAirportService.addAirport(airport);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/airport-search")
    public ModelAndView airportSearch(Airport airport,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myAirport");
        System.out.println(airport);
        PageInfo<Airport> pageInfo = adminAirportService.airportSearch(airport,currentPage,20);
        modelAndView.addObject("airportPageInfo",pageInfo);
        modelAndView.addObject("airport_data",airport);
        return modelAndView;
    }
    @RequestMapping("/query-airport")
    public JsonResult<List<Airport>> queryAirport(){
        List<Airport> list = adminAirportService.queryAllAirports();
        return new JsonResult<>(OK,list);
    }
}
