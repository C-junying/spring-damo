--城市，机场，航站楼
insert into airname(city_id,airport_id,terminal_id,city_name,airport_name,terminal_name)
select DISTINCT c.city_id,a.airport_id,t.terminal_id,c.city_name,a.airport_name,t.terminal_name
from city c left join airport a on c.city_id = a.city_id left join terminal t on a.airport_id=t.airport_id
where a.airport_id !="" and t.terminal_id!="";


--起始站
CREATE VIEW air_start_airport AS
select DISTINCT t.ticket_type_id,a.city_name start_city,a.airport_name start_airport,a.terminal_name start_terminal,t.ticket_pricing,t.discount,t.insurance_costs,t.ticket_count,t.remaining_tickets
from tickettype t,air_name a
where t.start_airport_id = a.airport_id
--终点站
CREATE VIEW air_end_airport AS
select DISTINCT t.ticket_type_id,a.city_name end_city,a.airport_name end_airport,a.terminal_name end_terminal
from tickettype t,air_name a
where t.end_airport_id = a.airport_id

--航班
CREATE VIEW air_onflight AS
select `onflight`.`on_flight_id` AS `on_flight_id`,`aircrafttype`.`type_name` AS `type_name`,`onflight`.`ticket_type_id` AS `ticket_type_id`,`onflight`.`current_status` AS `current_status`,`onflight`.`estimated_takeoff_time` AS `estimated_takeoff_time`,`onflight`.`actual_takeoff_time` AS `actual_takeoff_time`,`onflight`.`estimated_arrival_time` AS `estimated_arrival_time`,`onflight`.`actual_arrival_time` AS `actual_arrival_time`,`onflight`.`boarding_gate` AS `boarding_gate` from (`onflight` left join `aircrafttype` on((`onflight`.`type_id` = `aircrafttype`.`type_id`)))

