package com.jpa.hms.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostBookingId {
    @JsonProperty("booking_id")
    private int bookingId;
    public PostBookingId(){
        super();
    }
    public PostBookingId(int bookingId){
        super();
        this.bookingId = bookingId;
    }
    public void setBookingId(int bookingId){
        this.bookingId = bookingId;
    }
    public int getBookingId(){
        return this.bookingId;
    }
}
