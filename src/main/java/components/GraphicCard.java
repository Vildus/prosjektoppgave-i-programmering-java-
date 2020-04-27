package components;

public class GraphicCard extends Component {
    public static final String CATEGORY = "Graphic Card";
    private int memory; //i megabyte


    public GraphicCard(String brand, String model, int memory) {
        super(brand, model);
        this.memory = memory;
    }

    //TODO: Skrive logikk på alle komponenter så vi kun godtar gyldige attributter.
    //TODO: Eksempel: Harddisk kan bare være HDD eller SDD. Memory kan være et intervall

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
