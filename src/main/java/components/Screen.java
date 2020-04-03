package components;

public class Screen extends Component {
    private int screenSize; // in inch (tommer)

    public Screen(String brand, String model, double price, int articleNumber, int screenSize) {
        super(brand, model, price, articleNumber);
        this.screenSize = screenSize;
    }

    @Override
    public String getCategory() {
        return "Screen";
    }

    public int getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
