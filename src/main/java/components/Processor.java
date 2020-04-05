package components;

public class Processor extends Component {
    private int processorCount;
    private double clockRate;

    public Processor(String brand, String model, int processorCount, double clockRate) {
        super(brand, model);
        this.processorCount = processorCount;
        this.clockRate = clockRate;
    }


    @Override
    public String getCategory() {
        return "Processor";
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

    public void setClockRate(double clockRate) {
        this.clockRate = clockRate;
    }
}
