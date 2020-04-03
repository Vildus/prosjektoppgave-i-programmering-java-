package components;

public class GraphicCard extends Component {
    private int memory; //i megabyte

    
    public GraphicCard(String brand, String model, double price, int articleNumber, int memory) {
        super(brand, model, price, articleNumber);
        this.memory = memory;
    }

    @Override
    public String getCategory() {
        return "Graphic Card";
    }

    public int getMemory() {
        return this.memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
