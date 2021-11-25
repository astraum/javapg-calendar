package javapg.calendar;

import java.time.LocalDate;

public class Schedule {
    private LocalDate date;
    private String description;

    public Schedule (LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {return date;}
    public String getDescription() {return description;}

    public void setDate(LocalDate date) {this.date = date;}
    public void setDescription(String description) {this.description = description;}
}
