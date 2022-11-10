package task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable {
    private int id;
    private String title;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private static int idGenerator = 0;

    public Task(String title, String description, Type type, LocalDateTime taskDateTime) {
        this.id = idGenerator++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = taskDateTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }



    @Override
    public boolean isAvailable(LocalDate inputDate) {
        return inputDate.isEqual(getDateTime().toLocalDate());
    }

    @Override
    public String toString() {
        return "Задача{" +
                "id=" + id +
                ", Заголовок='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Тип=" + type +
                ", Дата=" + dateTime +
                '}';
    }
}
