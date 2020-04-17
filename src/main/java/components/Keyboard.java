package components;

public class Keyboard extends Component {
    public static final String TYPE = "Keyboard";
    private String interfaceType; //USB eller bluetooth

    public Keyboard(String brand, String model, String interfaceType) {
        super(brand, model);
        this.interfaceType = interfaceType;
    }

    @Override
    public String getCategory() {
        return TYPE;
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
