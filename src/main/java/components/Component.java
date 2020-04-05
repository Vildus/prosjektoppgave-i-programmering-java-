package components;

import validation.InvalidPriceException;

import java.io.Serializable;

public abstract class Component implements Serializable {
    private String brand;
    private String model;



    public Component(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract String getCategory();

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }




}
