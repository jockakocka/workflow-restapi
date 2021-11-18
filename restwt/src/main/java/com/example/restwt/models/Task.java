package com.example.restwt.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {

    private String callbackURL;
    private String label;
    private String role;
    private String Org;
    private String HTML;
    private Boolean shown;

    public Task(String callbackURL, String label, String role, String Org, String HTML,Boolean shown) {
        this.callbackURL = callbackURL;
        this.label = label;
        this.role = role;
        this.Org = Org;
        this.HTML = HTML;
        this.shown=shown;
    }

    @Override
    public String toString() {
        return callbackURL+","+label+","+role+","+Org+","+HTML+","+shown+"\n";
    }
}
