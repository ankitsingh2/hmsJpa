package com.jpa.hms.demo;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="roomid")
    private int roomid;

    public Booking(){
        super();
    }
    public Booking(int roomid){
        super();
        this.roomid = roomid;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setRoomId(int roomid){
        this.roomid = roomid;
    }
    public int getId(){
        return this.id;
    }
    public int getRoomId(){
        return this.roomid;
    }
    @Override
    public String toString(){
        return "Id: "+this.id + "  RoomId: " + this.roomid;
    }
}
