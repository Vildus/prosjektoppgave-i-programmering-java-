package components;

public class Keyboard extends Component {
    private String interfaceType; //USB eller bluetooth

    public Keyboard(String brand, String model, double price, int articleNumber, String interfaceType) {
        super(brand, model, price, articleNumber);
        this.interfaceType = interfaceType;
    }

    @Override
    public String getCategory() {
        return "Keyboard";
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
