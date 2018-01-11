package com.jpa.hms.demo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room,Integer>{
      List<Room> findRoomsByIsAvail(boolean isavail);

      @Transactional
      @Modifying
      @Query(value="update room set isavail=true",nativeQuery = true)
      void update();

      //@Transactional
      @Query(value="select * from room where isavail=true and room.type=:type limit 1",nativeQuery = true)
      Room findFreeRoomByType(@Param("type") String type);

      @Transactional
      @Modifying
      @Query(value="update room set isavail=:isavail where id=:id",nativeQuery=true)
      void updateIsAvail(@Param("id") int id,@Param("isavail") boolean isavail);
}
