package todoservice.model;

import java.util.Date;

public class TaskBuilder {
    private TaskDto task;

    public TaskBuilder() {
        this.task = new TaskDto();
    }

    public TaskBuilder setId(Long id) {
        this.task.id = id;
        return this;
    }
    public TaskBuilder setName(String name) {
        this.task.name = name;
        return this;
    }
    public TaskBuilder setStartDate(Date startDate) {
        this.task.startDate = startDate;
        return this;
    }
    public TaskBuilder setDeadline(Date deadline) {
        this.task.deadline = deadline;
        return this;
    }
    public TaskBuilder setDateOfCompletion(Date dateOfCompletion) {
        this.task.dateOfCompletion = dateOfCompletion;
        return this;
    }
    public TaskBuilder setPriority(String priority) {
        this.task.priority = priority;
        return this;
    }
    public TaskBuilder setStatus(String status) {
        this.task.status = status;
        return this;
    }

    public TaskDto build() {
        return this.task;
    }
}
