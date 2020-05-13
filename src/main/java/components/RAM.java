package components;

import java.io.Serializable;

public class RAM extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "RAM";

    private int memory; // in MB

    public RAM(String brand, String model, int memory) {
        super(brand, model);
        this.memory = memory;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getShortDescription() {
        return String.format("%d MB", this.memory);
    }

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
