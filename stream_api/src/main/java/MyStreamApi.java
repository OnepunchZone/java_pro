import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStreamApi {
    private List<Task> taskList() {
        return Stream.of(
                Task.builder().id(1).name("Задача №1").status(Statuses.CLOSED.getVal()).build(),
                Task.builder().id(2).name("Задача №2").status(Statuses.IN_PROGRESS.getVal()).build(),
                Task.builder().id(3).name("Задача №3").status(Statuses.IN_PROGRESS.getVal()).build(),
                Task.builder().id(4).name("Задача №4").status(Statuses.CLOSED.getVal()).build(),
                Task.builder().id(5).name("Задача №5").status(Statuses.OPEN.getVal()).build(),
                Task.builder().id(6).name("Задача №6").status(Statuses.OPEN.getVal()).build(),
                Task.builder().id(7).name("Задача №7").status(Statuses.CLOSED.getVal()).build()
        ).collect(Collectors.toList());
    }

    public List<String> taskInProgress() {

        return taskList().stream()
                .filter(task -> "В работе".equals(task.getTaskStatus()))
                .map(task -> task.getTaskName())
                .toList();
    }

    public long countClosedTasks() {

        return taskList().stream()
                .filter(task -> "Закрыта".equals(task.getTaskStatus()))
                .count();
    }

    public List<Boolean> hasIdTowAndNoMatchByIdNinetyNine() {
        List<Boolean> result = new ArrayList<>();

        result.add(taskList().stream()
                .anyMatch(task -> task.getTaskId() == 2)
        );
        result.add(taskList().stream()
                .noneMatch(task -> task.getTaskId() == 99)
        );
        
        return result;        
    }

    public List<Task> sortTasksByStatus() {
        List<String> statusOrder = List.of(
                Statuses.OPEN.getVal(), Statuses.IN_PROGRESS.getVal(), Statuses.CLOSED.getVal()
        );

        return taskList().stream()
                .sorted(Comparator.comparing(task -> statusOrder.indexOf(task.getTaskStatus())))
                .collect(Collectors.toList());
    }
}
