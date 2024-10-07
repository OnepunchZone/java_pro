import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStreamApi {
    private List<Task> taskList() {
        return Stream.of(
                Task.builder().id(1).name("Задача №1").status(Status.CLOSED).build(),
                Task.builder().id(2).name("Задача №2").status(Status.IN_PROGRESS).build(),
                Task.builder().id(3).name("Задача №3").status(Status.IN_PROGRESS).build(),
                Task.builder().id(4).name("Задача №4").status(Status.CLOSED).build(),
                Task.builder().id(5).name("Задача №5").status(Status.OPEN).build(),
                Task.builder().id(6).name("Задача №6").status(Status.OPEN).build(),
                Task.builder().id(7).name("Задача №7").status(Status.CLOSED).build()
        ).collect(Collectors.toList());
    }

    public List<String> taskInProgress() {

        return taskList().stream()
                .filter(task -> task.getTaskStatus() == Status.IN_PROGRESS)
                .map(task -> task.getTaskName())
                .toList();
    }

    public long countClosedTasks() {

        return taskList().stream()
                .filter(task -> task.getTaskStatus() == Status.CLOSED)
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
        List<Status> statusOrder = List.of(
                Status.OPEN, Status.IN_PROGRESS, Status.CLOSED
        );

        return taskList().stream()
                .sorted(Comparator.comparing(task -> statusOrder.indexOf(task.getTaskStatus())))
                .collect(Collectors.toList());
    }
}
