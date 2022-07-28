package jmu.controllers;

import com.github.pagehelper.PageInfo;
import jmu.pojo.*;
import jmu.service.AdminFlightService;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminFlightController extends BaseController{
    @Autowired
    AdminFlightService adminFlightService;
    @RequestMapping("/flight")
    public ModelAndView flightPage(@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myFlight");
        PageInfo<OnFlight> pageInfo = adminFlightService.selectAllOnFlights(currentPage,10);
        modelAndView.addObject("onFlightPageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/flight-ticket")
    public JsonResult<TicketType> flightPage(@RequestParam("ticketTypeID") String ticketTypeID){
        TicketType result = adminFlightService.selectByTicketID(ticketTypeID);
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/flight-passengers")
    public JsonResult<List<Passenger>> flightPassengers(@RequestParam("ticketTypeID") String ticketTypeID){
        List<Passenger> result = adminFlightService.selectFlightAllPassenger(ticketTypeID);
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/flight-delete")
    public ModelAndView flightDelete(@RequestParam("onFlightID") String onFlightID,
                                     @RequestParam("estimatedTakeoffTime") String estimatedTakeoffTime,
                                     @RequestParam(value = "page") Integer currentPage) throws ParseException {
        adminFlightService.onFlightDelete(onFlightID,estimatedTakeoffTime);
        return flightPage(currentPage);
    }
    @RequestMapping("/flight-search")
    public ModelAndView flightSearch(OnFlight onFlight,@RequestParam(value = "page",defaultValue = "1") Integer currentPage){
        ModelAndView modelAndView = new ModelAndView("myFlight");
        System.out.println(onFlight);
        PageInfo<OnFlight> pageInfo = adminFlightService.onFlightSearch(onFlight,currentPage,10);
        modelAndView.addObject("onFlightPageInfo",pageInfo);
        modelAndView.addObject("onflight_data",onFlight);
        return modelAndView;
    }
    @RequestMapping("/add-flight")
    public JsonResult<Void> addFlight(@RequestParam("onFlightID") String onFlightID,
                                      @RequestParam("typeID") String typeID,
                                      @RequestParam("estimatedTakeoffTime") String estimatedTakeoffTime,
                                      @RequestParam("estimatedArrivalTime") String estimatedArrivalTime,
                                      @RequestParam("boardingGate") String boardingGate,
                                      TicketType ticketType) throws ParseException {
        adminFlightService.addFlight(onFlightID,typeID,estimatedTakeoffTime,estimatedArrivalTime,boardingGate,ticketType);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/add-terminal")
    public JsonResult<Void> addTerminal(Terminal terminal){
        adminFlightService.addTerminal(terminal);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/query-type")
    public JsonResult<List<AircraftType>> queryType(){
        List<AircraftType> list = adminFlightService.queryAllAircraftTypes();
        return new JsonResult<>(OK,list);
    }
}
