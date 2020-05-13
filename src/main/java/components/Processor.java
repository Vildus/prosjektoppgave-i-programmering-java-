package components;

import java.io.Serializable;

public class Processor extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "Processor";
    private int processorCount;
    private int clockRate;

    public Processor(String brand, String model, int processorCount, int clockRate) {
        super(brand, model);
        this.processorCount = processorCount;
        this.clockRate = clockRate;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getShortDescription() {
        return String.format("%d cores, %d Hz", this.processorCount, this.clockRate);
    }

    public int getProcessorCount() {
        return this.processorCount;
    }

    public void setProcessorCount(int processorCount) {
        this.processorCount = processorCount;
    }

    public double getClockRate() {
        return this.clockRate;
    }

    public void setClockRate(int clockRate) {
        this.clockRate = clockRate;
    }
}
