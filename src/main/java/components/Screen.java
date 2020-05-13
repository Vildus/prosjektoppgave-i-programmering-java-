package components;

import java.io.Serializable;

public class Screen extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String getShortDescription() {
        return String.format("%d ″", this.screenSize);
    }

    public int getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
