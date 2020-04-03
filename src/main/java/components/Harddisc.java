package components;

public class Harddisc extends Component {
    private String type; //HDD eller SDD

    public Harddisc(String brand, String model, double price, int articleNumber, String type) {
        super(brand, model, price, articleNumber);
        this.type = type;
    }

    @Override
    public String getCategory() {
        return null;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
