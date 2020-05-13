package components;

public class Screen extends Component {
    public static final String CATEGORY = "Screen";
    private int screenSize; // in inches

    public Screen(String brand, String model, int screenSize) {
        super(brand, model);
        this.screenSize = screenSize;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    public int getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
