public class MainApp {
    public static void main(String[] args) {
        MyStreamApi msa = new MyStreamApi();
        System.out.println("Задачи в работе: " + msa.taskInProgress());
        System.out.println("Колличество закрытых задач: " + msa.countClosedTasks());
        System.out.println("[Наличие задачи с id 2, отсутствие задачи с id 99] = "
                + msa.hasIdTowAndNoMatchByIdNinetyNine());
        msa.sortTasksByStatus().forEach(System.out::println);
    }
}
