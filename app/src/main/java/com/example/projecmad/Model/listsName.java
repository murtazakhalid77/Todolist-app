package com.example.projecmad.Model;

import com.google.gson.annotations.SerializedName;

public class listsName {

    public listsName(String name) {
        this.name = name;
    }

    public listsName(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;
}
