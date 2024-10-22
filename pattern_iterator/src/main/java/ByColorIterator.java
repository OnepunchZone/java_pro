import java.util.Iterator;
import java.util.List;

public class ByColorIterator implements Iterator<String>, Constants {
    private final List<List<String>> matryshki;
    private int currentMat = 0;
    private int currentPart = 0;
    private int maxParts = Constants.MAX_PARTS;

    public ByColorIterator(List<List<String>> matryshki) {
        this.matryshki = matryshki;
    }

    @Override
    public boolean hasNext() {
        return currentMat < matryshki.size() && currentPart < maxParts;
    }

    @Override
    public String next() {
        String result = matryshki.get(currentMat).get(currentPart);
        currentPart++;

        if (currentPart == maxParts) {
            currentPart = 0;
            currentMat++;
        }

        return result;
    }
}
