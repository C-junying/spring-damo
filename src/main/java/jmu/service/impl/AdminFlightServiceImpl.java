package jmu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jmu.mapper.AircraftTypeMapper;
import jmu.mapper.OnFlightMapper;
import jmu.mapper.TerminalMapper;
import jmu.mapper.TicketTypeMapper;
import jmu.pojo.*;
import jmu.service.AdminFlightService;
import jmu.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
@Service
@Transactional
public class AdminFlightServiceImpl implements AdminFlightService {
    @Autowired
    private OnFlightMapper onFlightMapper;
    @Autowired
    private TicketTypeMapper ticketTypeMapper;
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private AircraftTypeMapper aircraftTypeMapper;
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
    public List<AircraftType> queryAllAircraftTypes() {
        return aircraftTypeMapper.selectAll();
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
}
