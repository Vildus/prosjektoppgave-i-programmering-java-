package components;

import com.sun.xml.internal.bind.v2.TODO;

public class GraphicCard extends Component {
    public static final String TYPE = "Graphic Card";
    private int memory; //i megabyte


    public GraphicCard(String brand, String model, int memory) {
        super(brand, model);
        this.memory = memory;
    }

    //TODO: Skrive logikk på alle komponenter så vi kun godtar gyldige attributter.
    //TODO: Eksempel: Harddisk kan bare være HDD eller SDD. Memory kan være et intervall

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
