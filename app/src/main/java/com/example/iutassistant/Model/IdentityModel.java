package com.example.iutassistant.Model;

public class IdentityModel implements IModel {
    private  String identity;

    public String getIdentity() {
        return identity;
    }

    public IdentityModel() {
    }

    public IdentityModel(String identity) {
        this.identity=identity;
    }


}
