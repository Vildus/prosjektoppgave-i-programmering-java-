package components;

public class RAM extends Component {
    public static final String CATEGORY = "RAM";

    private int memory;

    public RAM(String brand, String model, int memory) {
        super(brand, model);
        this.memory = memory;
    }

    @Override
    public String getCategory() {
        return CATEGORY;
    }

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
