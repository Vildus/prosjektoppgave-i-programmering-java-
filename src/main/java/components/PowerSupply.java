package components;

public class PowerSupply extends Component {
    public static final String CATEGORY = "Power Supply";
    private int effect; //i vatt
    private double inputVoltage;
    private double outputVoltage;

    public PowerSupply(String brand, String model, int effect, double inputVoltage, double outputVoltage) {
        super(brand, model);
        this.effect = effect;
        this.inputVoltage = inputVoltage;
        this.outputVoltage = outputVoltage;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
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
