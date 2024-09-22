
public class AppTest {

    public static void main(String[] args) {
        try {
            new TestRunner().runTests(ClassTest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
