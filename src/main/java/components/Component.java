package components;

import java.io.Serializable;

public abstract class Component implements Serializable {

    private static final long serialVersionUID = 1L;

    private String brand;
    private String model;

    public Component(String brand, String model) {
        this.setBrand(brand);
        this.setModel(model);
    }

    public abstract String getCategory();

    public abstract String getShortDescription();

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        if (brand.isEmpty()) {
            throw new IllegalBrandArgumentException("Brand cannot be empty");
        }
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        if (model.isEmpty()) {
            throw new IllegalModelArgumentException("Model cannot be empty");
        }
        this.model = model;
    }
}


