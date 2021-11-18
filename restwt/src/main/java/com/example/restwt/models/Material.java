package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Material {

    private String materialName;
    private int materialPrice;
    private String forPhone;

    public Material(String materialName,int materialPrice,String forPhone)
    {
        this.materialName=materialName;
        this.materialPrice=materialPrice;
        this.forPhone=forPhone;
    }
}
