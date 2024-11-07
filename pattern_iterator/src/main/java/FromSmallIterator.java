import java.util.Iterator;
import java.util.List;

public class FromSmallIterator implements Iterator<String>, Constants {
    private final List<List<String>> matryshki;
    private int currentPart = 0;
    private int currentMat = 0;
    private int maxParts = Constants.MAX_PARTS;

    public FromSmallIterator(List<List<String>> matryshki) {
        this.matryshki = matryshki;
    }

    @Override
    public boolean hasNext() {
        return currentPart < maxParts;
    }

    @Override
    public String next() {
        String result = matryshki.get(currentMat).get(currentPart);
        currentMat++;

        if (currentMat == matryshki.size()) {
            currentMat = 0;
            currentPart++;
        }

        return result;
    }
}