package jmu.controllers;

import jmu.pojo.*;
import jmu.service.UserService;
import jmu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/login",""})
    public ModelAndView loginPage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.invalidate();
        modelAndView.setViewName("login");
        return modelAndView;
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
        System.out.println(result);
        session.setAttribute("userID",result.getUserID());
        session.setAttribute("userName",result.getUserName());
        if(result.getPermission() == 1)
            session.setAttribute("permission",result.getPermission());
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/home")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
    @RequestMapping("/user_center")
    public ModelAndView userPage(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("myInfor");
        User result = userService.selectID(getUserIDFromSession(session));
        modelAndView.addObject("user",result);
        return modelAndView;
    }
    @RequestMapping("/user_update")
    public JsonResult<Void> userUpdate(User user){
        userService.updateUser(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("/user_passenger")
    public ModelAndView userPassenger(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("myPassenger");
        List<Passenger> result = userService.userPassenger(getUserIDFromSession(session));
        modelAndView.addObject("userPassenger",result);
        return modelAndView;
    }
    @RequestMapping("/passenger_add")
    public JsonResult<List<Passenger>> passengerAdd(Passenger passenger, HttpSession session){
        passenger.setUserID(getUserIDFromSession(session));
        userService.passengerAdd(passenger);
        List<Passenger> result = userService.userPassenger(getUserIDFromSession(session));
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/passenger_delete")
    public JsonResult<List<Passenger>> passengerDelete(Passenger passenger, HttpSession session){
        userService.passengerDelete(getUserIDFromSession(session),passenger.getPassengerID());
        List<Passenger> result = userService.userPassenger(getUserIDFromSession(session));
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/user_balance")
    public ModelAndView userBalance(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("myPay");
        User result = userService.selectID(getUserIDFromSession(session));
        modelAndView.addObject("balance",result.getBalance());
        return modelAndView;
    }
    @RequestMapping("/user_pay")
    public JsonResult<Integer> userPay(@RequestParam("balance") Integer balance, HttpSession session){
        userService.userPay(getUserIDFromSession(session),balance);
        User result = userService.selectID(getUserIDFromSession(session));
        return new JsonResult<>(OK,result.getBalance());
    }
    @RequestMapping("/user_order")
    public JsonResult<Void> userOrder(){
        return new JsonResult<>(OK);
    }
    @RequestMapping("/flight")
    public ModelAndView userFlightView(){
        return new ModelAndView("bookFlight").addObject("show",0);
    }
    @RequestMapping("/user_flight")
    public ModelAndView userFlight(@RequestParam("startCity")String startCity,
                                   @RequestParam("endCity")String endCity,
                                   @RequestParam("date")String date){
        ModelAndView modelAndView = new ModelAndView("bookFlight");
        List<OnFlight> list = userService.queryTakeoffAndArrive(startCity,endCity,date,date);
        if(list.size() == 0){
            modelAndView.addObject("msg","没有任何航班信息");
        }
        modelAndView.addObject("startCity",startCity);
        modelAndView.addObject("endCity",endCity);
        modelAndView.addObject("date",date);
        modelAndView.addObject("flightList",list);
        modelAndView.addObject("show",1);
        return modelAndView;
    }
    @RequestMapping("/query-passenger")
    public JsonResult<List<Passenger>> queryPassenger(HttpSession session){
        List<Passenger> result = userService.userPassenger(getUserIDFromSession(session));
        return new JsonResult<>(OK,result);
    }
    @RequestMapping("/order-create")
    public JsonResult<Void> orderCreate(@RequestParam("flight")String flight_infor,
                                        @RequestParam("passList") String passList,
                                        HttpSession session) throws ParseException {
        userService.orderCreate(flight_infor,passList,getUserIDFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("/order-success")
    public ModelAndView orderSuccess(){
        return new ModelAndView("orderSuccess");
    }
    @RequestMapping("/myorder")
    public ModelAndView myorder(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("myOrder");
        User user = userService.selectID(getUserIDFromSession(session));
        List<Order> orderList = userService.queryAllOrder(getUserIDFromSession(session));
        modelAndView.addObject("user",user);
        modelAndView.addObject("orderList",orderList);
        return modelAndView;
    }
}
/**
 * 使用@RequestBody总会报错，类型不匹配
 */