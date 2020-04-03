package components;

public class Motherboard extends Component {
    private String sizeCategory;

    public Motherboard(String brand, String model, double price, int articleNumber, String sizeCategory) {
        super(brand, model, price, articleNumber);
        this.sizeCategory = sizeCategory;
    }

    @Override
    public String getCategory() {
        return "Motherboard";
    }

    public String getSizeCategory() {
        return this.sizeCategory;
    }

    public void setSizeCategory(String sizeCategory) {
        this.sizeCategory = sizeCategory;
    }
}
