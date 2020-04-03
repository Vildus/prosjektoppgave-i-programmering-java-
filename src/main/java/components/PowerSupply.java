package components;

public class PowerSupply extends Component {
    private int effect; //i vatt
    private double inputVoltage;
    private double outputVoltage;

    public PowerSupply(String brand, String model, double price, int articleNumber, int effect, double inputVoltage, double outputVoltage) {
        super(brand, model, price, articleNumber);
        this.effect = effect;
        this.inputVoltage = inputVoltage;
        this.outputVoltage = outputVoltage;
    }

    @Override
    public String getCategory() {
        return "Power Supply";
    }

    public int getEffect() {
        return this.effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public double getInputVoltage() {
        return this.inputVoltage;
    }

    public void setInputVoltage(double inputVoltage) {
        this.inputVoltage = inputVoltage;
    }

    public double getOutputVoltage() {
        return this.outputVoltage;
    }

    public void setOutputVoltage(double outputVoltage) {
        this.outputVoltage = outputVoltage;
    }
}
