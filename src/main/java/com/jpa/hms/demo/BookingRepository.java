package com.jpa.hms.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Integer>{
    Booking findBookingByRoomid(int roomid);

}
