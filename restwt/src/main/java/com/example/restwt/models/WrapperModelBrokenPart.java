package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrapperModelBrokenPart {

    private String model;
    private String brokenPart;

    public WrapperModelBrokenPart(String model, String brokenPart) {
        this.model = model;
        this.brokenPart = brokenPart;
    }
}
