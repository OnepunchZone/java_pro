import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box {
    private final List<List<String>> matryshki;
    private final List<String> colors;
    private int maxParts = Constants.MAX_PARTS;

    public Box() {
        matryshki = new ArrayList<>();
        colors = List.of("Жёлтая", "Красная", "Фиолетовая", "Синяя");

        for (String color: colors) {
            List<String> matryshka = new ArrayList<>();
            for (int i = 1; i <= maxParts; i++) {
                matryshka.add(color + " матрёшка :" + " Часть " + i);
            }
            matryshki.add(matryshka);
        }
    }

    public Iterator<String> getFromSmallIterator() {
        return new FromSmallIterator(matryshki);
    }

    public Iterator<String> getByColorIterator() {
        return new ByColorIterator(matryshki);
    }
}
