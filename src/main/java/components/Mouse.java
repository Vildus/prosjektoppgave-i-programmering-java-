package components;

public class Mouse extends Component {
    private String interfaceType; // USB eller bluetooth

    public Mouse(String brand, String model, String interfaceType) {
        super(brand, model);
        this.interfaceType = interfaceType;
    }

    @Override
    public String getCategory() {
        return "Mouse";
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
