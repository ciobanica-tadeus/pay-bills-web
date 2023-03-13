package com.example.application.model;

import javax.validation.constraints.NotBlank;

public class Provider {
    @NotBlank
    private String ID;
    private String name;
    private String address;
    private String typeProvider;
    private float unitPrice;

    public Provider(String ID, String name, String address, String typeProvider, float unitPrice) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.typeProvider = typeProvider;
        this.unitPrice = unitPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProviderName() {
        return name;
    }

    public void setProviderName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeProvider() {
        return typeProvider;
    }

    public void setTypeProvider(String typeProvider) {
        this.typeProvider = typeProvider;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
