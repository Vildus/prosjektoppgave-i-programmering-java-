package components;

public class Mouse extends Component {
    private String interfaceType; // USB eller bluetooth

    public Mouse(String brand, String model, double price, int articleNumber) {
        super(brand, model, price, articleNumber);
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
