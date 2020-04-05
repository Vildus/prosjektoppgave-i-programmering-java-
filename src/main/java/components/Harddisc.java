package components;

public class Harddisc extends Component {
    private String type; //HDD eller SDD

    public Harddisc(String brand, String model, String type) {
        super(brand, model);
        this.type = type;
    }

    @Override
    public String getCategory() {
        return "Harddisc";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
