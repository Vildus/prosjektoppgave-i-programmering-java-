package components;

import java.io.Serializable;

public class HardDisk extends Component implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY = "Hard Disk";

    private String type; //HDD or SDD

    private int size; // in MB

    public HardDisk(String brand, String model, String type, int size) {
        super(brand, model);
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public String getCategory() {
        return CATEGORY;
    }

    @Override
    public String getShortDescription() {
        return String.format("%d MB (%s)", this.size, this.type);
    }

    public void setType(String type) {
        this.type = type;
    }
}
