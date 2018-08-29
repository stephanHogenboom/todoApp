package todoservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskDto {
    public Long id;
    public String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date startDate;
    public String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date deadline;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date dateOfCompletion;
    public String priority;
}
