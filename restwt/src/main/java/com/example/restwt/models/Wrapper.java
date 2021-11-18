package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wrapper {

    private String reason;
    private String videoName;
    private String repairPersonName;
    private String review;
    private Boolean sendVideo;

    public Wrapper(String reason, String videoName, String repairPersonName, String review, Boolean sendVideo) {
        this.reason = reason;
        this.videoName = videoName;
        this.repairPersonName = repairPersonName;
        this.review = review;
        this.sendVideo = sendVideo;
    }
}
