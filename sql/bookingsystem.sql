drop database if exists bookingsystem;
create database bookingsystem;
use bookingsystem;

# ----------------------------------------------------------
drop table if exists user;
create table user
(
    user_id               varchar(20) not null primary key comment '账号',
    user_name             varchar(100) comment '用户名',
    user_password         varchar(30) comment '密码',
    permission           int default 0 comment '权限等级',
	phone				 char(11) comment '手机号码',
    email                national varchar(50) comment '邮箱',
    birth_date            date comment '出生日期',
    balance              double default 0 comment '余额'
) comment '用户';

drop table if exists passenger;
create table passenger
(
    passenger_id          char(18) not null primary key comment '身份证ID',
    passenger_name        varchar(100) comment '姓名',
    sex                  int comment '性别',
    phone                varchar(11) comment '联系电话'
) comment '乘机人';

# ----------------------------------------------------------
drop table if exists city;
create table city (
	city_id               varchar(20) not null primary key comment '城市ID',
	city_name             varchar(200) comment '城市名称'
) comment '城市' ;

drop table if exists airport;
create table airport
(
    airport_id            char(3) not null primary key comment 'IATA机场三字码',
    city_id               varchar(20) comment '城市ID',
    airport_name          varchar(200) comment '机场名称',
    foreign key (city_id) references city(city_id)
) comment '机场';

drop table if exists terminal;
create table terminal
(
    terminal_id          varchar(10) not null primary key comment '航站楼ID',
    airport_id           char(3) comment '机场ID',
    terminal_name        varchar(200) comment '航站楼名称',
    foreign key (airport_id) references airport(airport_id)
) comment '航站楼';

drop table if exists airname;
create table airname
(
	city_id               varchar(20) not null primary key comment '城市ID',
	airport_id           char(3) comment '机场ID',
    terminal_id          varchar(10) not null primary key comment '航站楼ID',
	city_name             varchar(200) comment '城市名称',
	airport_name          varchar(200) comment '机场名称',
    terminal_name        varchar(200) comment '航站楼名称',
	primary key(city_id,airport_id,terminal_id),
	foreign key (city_id) references city(city_id),
    foreign key (airport_id) references airport(airport_id),
	foreign key (terminal_id) references terminal(terminal_id)
) comment '飞机名';

drop table if exists aircrafttype;
create table aircraftType
(
    type_id              varchar(20) not null primary key comment '机型ID',
    type_name            varchar(100) comment '机型名称'
) comment '机型';

drop table if exists flight;
create table flight
(
    flight_id            varchar(6) not null primary key comment '航班ID 公司代码+3/4位数',
    start_station_id     varchar(10) comment '起始站ID',
    end_station_id       varchar(10) comment '终点站ID',
    type_id             varchar(20) comment '机型ID',
    ticket_pricing       double comment '机票定价',
    foreign key (start_station_id) references terminal(terminal_id),
    foreign key (end_station_id) references terminal(terminal_id),
    foreign key (type_id) references aircraftType(type_id)
) comment '航班';

drop table if exists onFlight;
create table onFlight
(
    on_flight_id           varchar(18) not null primary key comment '执飞航班ID',
    flight_id             varchar(6) comment '航班ID',
    execution_price       double comment '执行价格',
    current_status        varchar(10) comment '当前状态',
    estimated_takeoff_time datetime comment '预计起飞时间',
    actual_takeoff_time    datetime comment '实际起飞时间',
    estimated_arrival_time datetime comment '预计到达时间',
    actual_arrival_time    datetime comment '实际到达时间',
    boarding_gate         varchar(10) comment '登机口',
	
    foreign key (flight_id) references flight(flight_id)
) comment '执飞航班';

drop table if exists cabin;
create table cabin
(
    cabin_id              int not null primary key auto_increment comment '舱位ID',
    cabin_class           varchar(50) comment '舱位等级'
) comment '舱位';

drop table if exists ticketType;
create table ticketType
(
    ticket_type_id        varchar(20) not null primary key comment '机票ID',
    on_flight_id          varchar(18) comment '执飞航班ID',
    cabin_id              int comment '舱位ID',
    basic_price           double comment '基本销售价格',
    discount              double comment '折扣',
	insurance_costs       double comment '保险费用',
    ticket_count          int comment '总数',
    remaining_tickets     int comment '余量',
    luggage_limit         double comment '行李件数',
    foreign key (on_flight_id) references onFlight(on_flight_id),
    foreign key (cabin_id) references cabin(cabin_id)
) comment '机票类型';

# ----------------------------------------------------------


drop table if exists `order`;
create table `order`
(
    order_id              varchar(20) not null primary key comment '订单ID',
    user_id           varchar(20) comment '用户ID',
    order_costs           double comment '总费用',
    payment_status        varchar(10) comment '支付状态',
    foreign key (user_id) references user(user_id)
) comment '订单';

drop table if exists airTicket;
create table airTicket
(
    air_ticket_id          varchar(20) not null primary key comment '机票类型',
    passenger_id          char(18) comment '乘机人ID',
    order_id              varchar(20) comment '订单ID',
    ticket_type_id         varchar(20) comment '机票类型ID',
    total_costs           double comment '总费用',
    luggage_count         int comment '行李数',
    seat_number            varchar(10) comment '座位号',
	book_time    datetime comment '订票时间',
    foreign key (passenger_id) references passenger(passenger_id),
    foreign key (order_id) references `order`(order_id),
    foreign key (ticket_type_id) references ticketType(ticket_type_id)
) comment '机票';

drop table if exists luggage;
create table luggage
(
    luggage_id            int not null primary key auto_increment comment '行李ID',
    air_ticket_id          varchar(20) comment '机票ID',
    weight               double comment '重量',
    length               double comment '长',
    width                 double comment '宽',
    height                 double comment '高',
    shipping_costs        double comment '托运费',
    foreign key (air_ticket_id) references airTicket(air_ticket_id)
) comment '行李';

