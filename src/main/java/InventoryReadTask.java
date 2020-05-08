import inventory.Inventory;
import io.InventoryRepository;
import javafx.concurrent.Task;

public class InventoryReadTask extends Task<Inventory> {

    //Ingen kan forandre på denne. Kan ikke skrive til denne mer enn en gang
    //Og da er den thread safe:D
    private final int version;

    public InventoryReadTask(int version) {
        this.version = version;
    }

    @Override
    protected Inventory call() throws Exception {
        try {
            //Emulating opening large file
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        InventoryRepository inventoryRepository = new InventoryRepository();
        Inventory content = inventoryRepository.read();
        //FileOpener fileOpener = new FileOpenerString();
        //String content = fileOpener.openFile(version);
        return content;
    }
}

