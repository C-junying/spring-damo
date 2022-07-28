package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.*;
import jmu.pojo.*;
import jmu.service.AdminService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AirportMapper airportMapper;
    @Autowired
    private OnFlightMapper onFlightMapper;
    @Autowired
    private TicketTypeMapper ticketTypeMapper;
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private AircraftTypeMapper aircraftTypeMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<City> selectAllCities(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<City> list =  cityMapper.selectAllCities();
        PageInfo<City> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void cityEdit(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result == null){
            throw new CityNotFoundException("城市不存在异常");
        }
        Integer rows =  cityMapper.updateCity(city);
        if(rows!=1){
            throw new CityUpdateException("更新城市时产生未知异常");
        }
    }

    @Override
    public void cityDelete(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result == null){
            throw new CityNotFoundException("城市不存在异常");
        }
        Integer rows = cityMapper.deleteCityByID(city.getCityID());
        if(rows!=1){
            throw new CityDeleteException("删除城市时产生未知异常");
        }
    }

    @Override
    public PageInfo<City> citySearch(City city,Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<City> list =  cityMapper.queryCityByNameAndID(city);
        PageInfo<City> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Airport> selectAllAirports(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Airport> list =  airportMapper.selectAllAirports();
        PageInfo<Airport> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void airportEdit(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result == null){
            throw new AirportNotFoundException("机场不存在异常");
        }
        Integer rows =  airportMapper.updateAirport(airport);
        if(rows!=1){
            throw new AirportUpdateException("更新机场时产生未知异常");
        }
    }

    @Override
    public void airportDelete(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result == null){
            throw new AirportNotFoundException("机场不存在异常");
        }
        Integer rows = airportMapper.deleteAirportByID(airport.getAirportID());
        if(rows!=1){
            throw new CityDeleteException("删除机场时产生未知异常");
        }
    }

    @Override
    public PageInfo<Airport> airportSearch(Airport airport, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Airport> list =  airportMapper.airportSearch(airport);
        PageInfo<Airport> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<OnFlight> selectAllOnFlights(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<OnFlight> list =  onFlightMapper.selectAllOnFlights();
        PageInfo<OnFlight> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TicketType selectByTicketID(String ticketType) {
        return ticketTypeMapper.selectByID(ticketType);
    }

    @Override
    public List<Passenger> selectFlightAllPassenger(String ticketTypeID) {
        return onFlightMapper.queryAllPassengers(ticketTypeID);
    }

    @Override
    public void onFlightDelete(String onFlightID,String estimatedTakeoffTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date takeoff = sdf.parse(estimatedTakeoffTime);
        OnFlight result = onFlightMapper.selectByIDTime(onFlightID,takeoff);
        if(result == null){
            throw new OnFlightDeleteException("航班不存在异常");
        }
        Integer rows = onFlightMapper.deleteByIDTime(onFlightID,takeoff);
        if(rows!=1){
            throw new OnFlightDeleteException("删除航班时产生未知异常");
        }
    }

    @Override
    public PageInfo<OnFlight> onFlightSearch(OnFlight onFlight, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<OnFlight> list =  onFlightMapper.onFlightSearch(onFlight);
        PageInfo<OnFlight> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<User> selectAllUser(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<User> list =  userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void userDelete(User user) {
        User result = userMapper.selectID(user.getUserID());
        if(result == null){
            throw new UserNotFoundException("用户不存在异常");
        }
        Integer rows = userMapper.deleteUser(user.getUserID());
        if(rows!=1){
            throw new UserDeleteException("删除用户时产生未知异常");
        }
    }

    @Override
    public PageInfo<User> usertSearch(User user, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<User> list =  userMapper.userSearch(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addCity(City city) {
        City result = cityMapper.queryCityByID(city.getCityID());
        if(result!=null){
            throw new CityIDDuplicateException("城市已存在");
        }
        Integer rows = cityMapper.insertCity(city);
        if(rows!=1){
            throw new InsertException("插入城市时产生未知异常");
        }
    }

    @Override
    public void addAirport(Airport airport) {
        Airport result = airportMapper.queryAirportByID(airport.getAirportID());
        if(result!=null){
            throw new AirportIDDuplicateException("机场已存在");
        }
        Integer rows = airportMapper.insertAirport(airport);
        if(rows!=1){
            throw new InsertException("插入机场时产生未知异常");
        }
    }

    @Override
    public void addTerminal(Terminal terminal) {
        Terminal result = terminalMapper.queryTerminalByTAID(terminal.getTerminalID(), terminal.getAirportID());
        if(result!=null){
            throw new TerminalDuplicateException("航站楼已存在");
        }
        Integer rows = terminalMapper.insertTerminal(terminal);
        if(rows!=1){
            throw new InsertException("插入机场时产生未知异常");
        }
    }

    @Override
    public void addFlight(String onFlightID,String typeID,String estimatedTakeoffTime,String estimatedArrivalTime,String boardingGate,TicketType ticketType) throws ParseException {
        OnFlight flight = new OnFlight();
        flight.setOnFlightID(onFlightID);
        flight.setTypeID(typeID);
        flight.setBoardingGate(Integer.parseInt(boardingGate));
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat simple2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date takeoff = simple2.parse(estimatedTakeoffTime);
        Date arrive = simple2.parse(estimatedArrivalTime);
        flight.setEstimatedTakeoffTime(takeoff);
        flight.setActualTakeoffTime(takeoff);
        flight.setEstimatedArrivalTime(arrive);
        flight.setActualArrivalTime(arrive);
        String ticket = ticketType.getStartAirportID()+simple.format(takeoff);
        flight.setCurrentStatus("空闲");
        flight.setTicketTypeID(ticket);
        ticketType.setTicketTypeID(ticket);
        ticketType.setRemainingTickets(ticketType.getTicketCount());
        //以上都是数据处理
        //插入操作
        OnFlight result = onFlightMapper.selectByIDTime(flight.getOnFlightID(),flight.getEstimatedTakeoffTime());
        if(result!=null){
            throw new OnFlightDuplicateException("航班已存在");
        }
        Integer rows = onFlightMapper.insertOnFlight(flight);
        if(rows!=1){
            throw new InsertException("插入航班时产生未知异常");
        }
        TicketType result2 = ticketTypeMapper.selectByID(ticketType.getTicketTypeID());
        if(result2!=null){
            throw new TicketTypeDuplicateException("航班机票已存在");
        }
        Integer rows2 = ticketTypeMapper.insertTicketType(ticketType);
        if(rows2!=1){
            throw new InsertException("插入航班机票时产生未知异常");
        }
    }

    @Override
    public List<City> queryAllCities() {
        return cityMapper.selectAllCities();
    }

    @Override
    public List<Airport> queryAllAirports() {
        return airportMapper.selectAllAirports();
    }

    @Override
    public List<AircraftType> queryAllAircraftTypes() {
        return aircraftTypeMapper.selectAll();
    }
}
