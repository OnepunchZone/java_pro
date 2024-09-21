import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public static void runTests(Class<?> testClass) throws NoSuchMethodException, InstantiationException,
            InvocationTargetException, IllegalAccessException {
        int num1 = 3;
        int num2 = 7;
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        Map<Integer, Method> testsMap = new HashMap<>();

        for (Method method : testClass.getDeclaredMethods()) {

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod != null) {
                    throw new RuntimeException("Метод BeforeSuite может быть только один.");
                }
                beforeSuiteMethod = method;
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod != null) {
                    throw new RuntimeException("Метод AfterSuite может быть только один.");
                }
                afterSuiteMethod = method;
            }

            if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).order();
                testsMap.put(priority, method);
            }
        }

        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(testClass.getDeclaredConstructor().newInstance());
        }

        Map<Integer, Method> sortTests = new TreeMap<>(testsMap);
        for (Method method : sortTests.values()) {
            method.invoke(testClass.getDeclaredConstructor(int.class, int.class).newInstance(num1, num2));
        }

        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(testClass.getDeclaredConstructor().newInstance());
        }
    }
}
