package components;

public class GraphicCard extends Component {
    public static final String TYPE = "Graphic Card";
    private int memory; //i megabyte


    public GraphicCard(String brand, String model, int memory) {
        super(brand, model);
        this.memory = memory;
    }

    @Override
    public String getCategory() {
        return TYPE;
    }

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
