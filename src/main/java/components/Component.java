package components;

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
        if (brand.isBlank()) {
            throw new IllegalBrandArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        if (model.isBlank()) {
            throw new IllegalModelArgumentException("Model cannot be empty");
        }
        this.model = model;
    }




}
