import java.util.Iterator;

public class MainApp {
    public static void main(String[] args) {
        Box box = new Box();

        System.out.println("Итератор от маленьких к большим: ");
        Iterator<String> fromSmallIterator = box.getFromSmallIterator();
        while (fromSmallIterator.hasNext()) {
            System.out.println(fromSmallIterator.next());
        }

        System.out.println();

        System.out.println("Итератор по цветам (все части): ");
        Iterator<String> byColorIterator = box.getByColorIterator();
        while (byColorIterator.hasNext()) {
            System.out.println(byColorIterator.next());
        }
    }
}
