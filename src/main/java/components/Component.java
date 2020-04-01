package components;

public class Component {
    private double price;
    private String type;
    private String Beskrivelse;


    public Component(double price, String type, String Beskrivelse) {
        this.price = price;
        this.type = type;
        this.Beskrivelse = Beskrivelse;
    }


    public String leggTil(){


        return "Dette går ikke" ;

    }


    public String RandomShit(){
        return "Shit!";
    }
}
