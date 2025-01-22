import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {
    private final DataSource dataSource;

    public ItemsDao() {
        this.dataSource = DataSource.getInstance();
    }

    public void createItem(Item item) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesToBd.CREATE_ITEM,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();

            try (ResultSet rsId = statement.getGeneratedKeys()) {
                if (rsId.next()) {
                    item.setId(rsId.getInt(1));
                }
            }
        }
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesToBd.GET_ALL_ITEMS);
             ResultSet rsAllItems = statement.executeQuery()) {
            while (rsAllItems.next()) {
                Item item = new Item();
                item.setId(rsAllItems.getInt("id"));
                item.setTitle(rsAllItems.getString("title"));
                item.setPrice(rsAllItems.getDouble("price"));
                items.add(item);
            }
        }
        return items;
    }

    public void updatePriceX2(Item item) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QueriesToBd.PRICE_UPDATE)) {
            statement.setDouble(1, item.getPrice());
            statement.setInt(2, item.getId());
            statement.executeUpdate();
        }
    }
}
