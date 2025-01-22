public class QueriesToBd {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/chat_db";
    public static final String GET_ALL_ITEMS = "SELECT * FROM items";
    public static final String PRICE_UPDATE = "UPDATE items SET price = ? WHERE id = ?";
    public static final String CREATE_ITEM = "INSERT INTO items (title, price) VALUES (?, ?)";
}
