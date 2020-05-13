package components;

import java.io.Serializable;

public class PowerSupply extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "Power Supply";

    private int effect; //in watt

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

    @Override
    public String getShortDescription() {
        return String.format("%d W, I: %.1f V, O: %.1f V", this.effect, this.inputVoltage, this.outputVoltage);
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
