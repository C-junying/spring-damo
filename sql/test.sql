--起始站->终点站
select a1.ticket_type_id,start_city,start_airport,start_terminal,end_city,end_airport,end_terminal,ticket_pricing,discount,insurance_costs,ticket_count,remaining_tickets
from air_start_airport a1 left join air_end_airport a2 on a1.ticket_type_id = a2.ticket_type_id
where a1.ticket_type_id="XMN2022072508" and (a1.start_city = "厦门" or a1.start_city ="厦门市") and (a2.end_city = "上海" or a2.end_city = "上海市");