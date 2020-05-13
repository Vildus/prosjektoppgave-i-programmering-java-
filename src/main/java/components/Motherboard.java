package components;

import java.io.Serializable;

public class Motherboard extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "Motherboard";

    private String sizeCategory;

    public Motherboard(String brand, String model, String sizeCategory) {
        super(brand, model);
        this.sizeCategory = sizeCategory;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getShortDescription() {
        return String.format("%s", this.sizeCategory);
    }

    public String getSizeCategory() {
        return this.sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }
}
