package com.jpa.hms.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostNameType {
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    public PostNameType(){
        super();
    }
    public PostNameType(String name, String type){
        super();
        this.name=name;
        this.type=type;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
}
