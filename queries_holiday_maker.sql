
#1 all hotelroom with all option
CREATE OR REPLACE VIEW all_room AS
	select book_id, hotelroom_id, hotel_id, hotel_name, room_type, room_id, 
    room_price_per_night, number_of_people, check_in, check_out, km_to_beach, 
    km_to_city, guest_rating, pool, evening_entertainment, kids_club, restaurant
    from hotelrooms
	left join rooms on hotelrooms.room = rooms.room_id
	left join hotels on hotelrooms.hotel = hotels.hotel_id
	order by hotelroom_id;
select * from all_room;

#2 all booking
CREATE OR REPLACE VIEW booked_list AS
	select bookings.book_id as book_id, hotelroom_id, bookings.hotel_id as hotel_id, hotel_name, room_type, bookings.room_id as room_id,    
     room_price_per_night, bookings.number_of_people, bookings.check_in, bookings.check_out, km_to_beach, 
     km_to_city, guest_rating, pool, evening_entertainment, kids_club, restaurant 
    from bookings, all_room
    where bookings.hotel_id=all_room.hotel_id
    and bookings.room_id = all_room.room_id
	order by book_id;
select * from booked_list;


#3 alla hotelrooms med booked och utan booked

CREATE OR REPLACE VIEW all_room_booked_and_unbooked AS
	select * from all_room
    union all 
    select * from booked_list
    order by hotelroom_id;
    
select * from all_room_booked_and_unbooked;


#4. example
# 
SELECT * FROM all_room_booked_and_unbooked 
WHERE room_id = 1 and pool >= 0   
AND kids_club >= 0 AND evening_entertainment >= 0 
and restaurant >= 0 and  guest_rating >= 3
and km_to_beach <= 2.0 and km_to_city <= 100.0
group by hotelroom_id
HAVING check_in IS NULL OR check_out <= "2020-06-10" OR check_in >= "2020-06-11"
order by guest_rating desc;

