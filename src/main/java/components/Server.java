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
        return i; //nei det er ikke riktig
    }

}
