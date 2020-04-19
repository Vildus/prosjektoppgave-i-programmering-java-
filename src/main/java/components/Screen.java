package components;

public class Screen extends Component {
    public static final String TYPE = "Screen";
    private int screenSize; // in inch (tommer)

    public Screen(String brand, String model, int screenSize) {
        super(brand, model);
        this.screenSize = screenSize;
    }

    @Override
    public String getCategory() {
        return TYPE;
    }

    public int getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
