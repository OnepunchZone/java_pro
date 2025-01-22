public class Main {
    public static void main(String[] args) {

        ItemsServiceProxy serviceProxy = new ItemsServiceProxy();

        try {
            serviceProxy.create100Items();
            System.out.println("Создано 100 предметов.");
            System.out.println("Список предметов ДО увеличения цен:");
            serviceProxy.printAllItems();

            serviceProxy.doublePriceForAllItems();
            System.out.println("Увеличена цена в 2 раза.");
            System.out.println("Список предметов ПОСЛЕ увеличения цен:");
            serviceProxy.printAllItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
