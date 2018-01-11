package com.jpa.hms.demo;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="bookingid")
    private int bookingid;

    public Customer(){
        super();
    }
    public Customer(String name,int bookingid){
        super();
        this.name=name;
        this.bookingid=bookingid;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBookingID(int bookingId){
        this.bookingid = bookingId;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getBookingId(){
        return this.bookingid;
    }
    @Override
    public String toString(){
        return "ID: " + this.id + "  Name: " +  this.name + "  BookingId: " + this.bookingid;
    }
}
