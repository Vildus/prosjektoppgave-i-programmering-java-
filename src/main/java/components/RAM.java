package components;

public class RAM extends Component {
    private int memory;

    public RAM(String brand, String model, double price, int articleNumber, int memory) {
        super(brand, model, price, articleNumber);
        this.memory = memory;
    }

    @Override
    public String getCategory() {
        return "RAM";
    }

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
