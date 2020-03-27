package components;

public class Server {

    int price;
    String name;

    public Server(int price, String name) {
        this.price = price;
        this.name = name;
    }
    public void hei(){
        System.out.println("Hei vilde");
    }

    public int getPrice(int i) {
        return i; //hei om dette er riktig
    }

    public void morradi() {
        System.out.println("er kul as ploppsann");
    }

    public void heipådeg() {
        //gjør noe her
    }

}
