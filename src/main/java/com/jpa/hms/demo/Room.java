package com.jpa.hms.demo;

import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "isavail")
    private boolean isAvail;

    @Column(name = "type")
    private String type;

    public Room(){
        super();
    }
    public Room(boolean isAvail, String type){
        super();
        //this.id = id;
        this.isAvail = isAvail;
        this.type = type;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setIsAvail(boolean isAvail){
        this.isAvail = isAvail;
    }
    public void setType(String type){
        this.type = type;
    }
    public int getId(){
        return this.id;
    }
    public boolean getIsAvail(){
        return this.isAvail;
    }
    public String getType(){
        return this.type;
    }
    @Override
    public String toString(){
        return "Room no.:" + this.id + "  Available:" + this.isAvail + "  Type:" + this.type;
    }
}
