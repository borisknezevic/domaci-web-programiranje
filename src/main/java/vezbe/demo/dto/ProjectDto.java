package vezbe.demo.dto;

import java.util.Date;

public class ProjectDto {
    private Long id;

    private String name;

    private Date deadline;

    public ProjectDto() {
    }

    public ProjectDto(Long id, String name, Date deadline) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
