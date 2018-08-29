package todoservice.model;

import java.util.Date;

public class TaskBuilder {
    private TaskDto task;

    public TaskBuilder() {
        this.task = new TaskDto();
    }

    public TaskBuilder setId(Long id) {
        this.task.setId(id);
        return this;
    }
    public TaskBuilder setName(String name) {
        this.task.setName(name);
        return this;
    }
    public TaskBuilder setStartDate(Date startDate) {
        this.task.setStartDate(startDate);
        return this;
    }
    public TaskBuilder setDeadline(Date deadline) {
        this.task.setDeadline(deadline);
        return this;
    }
    public TaskBuilder setDateOfCompletion(Date dateOfCompletion) {
        this.task.setDateOfCompletion(dateOfCompletion);
        return this;
    }
    public TaskBuilder setPriority(String priority) {
        this.task.setPriority(priority);
        return this;
    }
    public TaskBuilder setStatus(String status) {
        this.task.setStatus(status);
        return this;
    }

    public TaskDto build() {
        return this.task;
    }
}
