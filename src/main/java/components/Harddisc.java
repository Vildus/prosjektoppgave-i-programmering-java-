package components;

public class Harddisc extends Component {
    public static final String TYPE = "Harddisc";
    private String type; //HDD eller SDD

    public Harddisc(String brand, String model, String type) {
        super(brand, model);
        this.type = type;
    }

    @Override
    public String getCategory() {
        return TYPE;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
