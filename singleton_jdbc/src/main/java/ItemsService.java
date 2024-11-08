import java.sql.SQLException;
import java.util.List;

public class ItemsService {
    private final ItemsDao itemsDao;

    public ItemsService() {
        this.itemsDao = new ItemsDao();
    }

    public void create100Items() throws SQLException {
        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.setTitle("Предмет " + i);
            item.setPrice(Math.random() * 1000);
            itemsDao.createItem(item);
        }
    }

    public void doublePriceForAllItems() throws SQLException {
        List<Item> items = itemsDao.getAllItems();
        for (Item item : items) {
            item.setPrice(item.getPrice() * 2);
            itemsDao.updatePriceX2(item);
        }
    }

    public void printAllItems() throws SQLException {
        List<Item> items = itemsDao.getAllItems();

        for (Item item : items) {
            System.out.printf("ID: %d, Title: %s, Price: %.2f%n", item.getId(), item.getTitle(), item.getPrice());
        }
    }
}
