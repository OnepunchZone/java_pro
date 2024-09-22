import myexceptions.InvalidTestException;
import annotations.AfterSuite;
import annotations.BeforeSuite;
import annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public void runTests(Class<?> testClass)  {
        boolean testFlag = false;
        int correctTests = 0;
        int countTests = 0;
        int num1 = 3;
        int num2 = 7;
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        Map<Integer, Method> testsMap = new HashMap<>();

        for (Method method : testClass.getDeclaredMethods()) {

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod != null) {
                    throw new InvalidTestException("Метод с аннотацией @BeforeSuite может быть только один.");
                }
                beforeSuiteMethod = method;
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod != null) {
                    throw new InvalidTestException("Метод с аннотацией @AfterSuite может быть только один.");
                }
                afterSuiteMethod = method;
            }

            if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).order();
                if (priority < 1 || priority > 10) {
                    throw new InvalidTestException("Приоритет теста должен быть в диапазоне от 1 до 10.");
                }
                testsMap.put(priority, method);
                testFlag = true;
            }
        }

        if (!testFlag && beforeSuiteMethod == null && afterSuiteMethod == null) {
            throw new InvalidTestException("Отсутствуют аннотированные методы.");
        }

        if (beforeSuiteMethod != null) {
            try {
                beforeSuiteMethod.invoke(testClass.getDeclaredConstructor().newInstance());
                correctTests++;
                countTests++;
            } catch (Exception e) {
                System.out.println("Ошибка в BeforeSuite методе: " + e.getMessage());
                countTests++;
            }
        }

        Map<Integer, Method> sortTests = new TreeMap<>(testsMap);
        for (Method method : sortTests.values()) {
            try {
                method.invoke(testClass.getDeclaredConstructor().newInstance());
                System.out.println(method.getName() + " прошёл успешно.");
                correctTests++;
                countTests++;
            } catch (Exception e) {
                System.out.println("Ошибка в тесте " + method.getName() + ". Тест провалился.");
                countTests++;
            }
        }

        if (afterSuiteMethod != null) {
            try {
                afterSuiteMethod.invoke(testClass.getDeclaredConstructor().newInstance());
                correctTests++;
                countTests++;
            } catch (Exception e) {
                System.out.println("Ошибка в AfterSuite методе: " + e.getMessage());
                countTests++;
            }
        }

        System.out.println("Тесты прошли " + correctTests + " из " + countTests);
    }
}
