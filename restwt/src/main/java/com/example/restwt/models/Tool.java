package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tool {

    private String toolName;
    private Integer toolPrice;

    public Tool(String toolName,Integer toolPrice){
        this.toolName=toolName;
        this.toolPrice=toolPrice;
    }
}
