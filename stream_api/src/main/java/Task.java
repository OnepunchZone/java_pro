public class Task {
    private int taskId;
    private String taskName;
    private Status taskStatus;

    public Task(Builder builder) {
        this.taskId = builder.id;
        this.taskName = builder.name;
        this.taskStatus = builder.status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private Status status;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskStatus='" + taskStatus.getVal() + '\'' +
                '}';
    }
}
