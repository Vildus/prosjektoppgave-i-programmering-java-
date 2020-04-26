package components;

public class HardDisk extends Component {
    public static final String CATEGORY = "HardDisk";
    private String type; //HDD eller SDD

    public HardDisk(String brand, String model, String type) {
        super(brand, model);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getCategory() {
        return CATEGORY;
    }

    public void setType(String type) {
        this.type = type;
    }
}
