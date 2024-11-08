import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {
    private final ItemsService itemsService;
    private final DataSource dataSource;

    public ItemsServiceProxy() {
        this.itemsService = new ItemsService();
        this.dataSource = DataSource.getInstance();
    }

    public void create100Items() {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            itemsService.create100Items();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doublePriceForAllItems() {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            itemsService.doublePriceForAllItems();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllItems() {
        try {
            itemsService.printAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
