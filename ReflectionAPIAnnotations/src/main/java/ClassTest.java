import annotations.AfterSuite;
import annotations.BeforeSuite;
import annotations.Test;

public class ClassTest {

    @BeforeSuite
    public void start() {
        System.out.println("Метод: start, аннотация: BeforeSuite");
    }

    @Test(order = 2)
    public void test1() {
        System.out.println("Метод: test1 с приоритетом 2, аннотация: Test");
    }

    @Test(order = 3)
    public void test2() {
        System.out.println("Метод: test2 с приоритетом 3, аннотация: Test");
    }

    @Test(order = 4)
    public void test3() {
        System.out.println("Метод: test3 с приоритетом 4, аннотация: Test");
    }

    @Test(order = 1)
    public void test4() {
        System.out.println("Метод: test4 с приоритетом 1, аннотация: Test");
    }

    @Test
    public void test5() {
        System.out.println("Метод: test5 с приоритетом default=10, аннотация: Test.");
    }

    @Test(order = 6)
    public void test6() {
        System.out.println("Метод: test6 , аннотация: Test");
        throw new RuntimeException("Ошибка в данном тесте.");
    }

    @AfterSuite
    public void end() {
        System.out.println("Метод: end, аннотация: AfterSuite");
    }
}
