package components;

public class Motherboard extends Component {
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

    public String getSizeCategory() {
        return this.sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }
}
