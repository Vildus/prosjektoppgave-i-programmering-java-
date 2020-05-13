package components;

import java.io.Serializable;

public class Keyboard extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "Keyboard";

    private String interfaceType; //USB or bluetooth

    public Keyboard(String brand, String model, String interfaceType) {
        super(brand, model);
        this.interfaceType = interfaceType;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getShortDescription() {
        return String.format("%s", this.interfaceType);
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
