package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friend {

    private String friendName;
    private String materialToolOne;
    private String materialToolTwo;
    private String materialToolThree;
    private String materialToolFour;

    public Friend(String friendName, String materialToolOne, String materialToolTwo, String materialToolThree, String materialToolFour) {
        this.friendName = friendName;
        this.materialToolOne = materialToolOne;
        this.materialToolTwo = materialToolTwo;
        this.materialToolThree = materialToolThree;
        this.materialToolFour = materialToolFour;
    }
}
