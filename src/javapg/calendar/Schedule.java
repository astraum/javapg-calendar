package javapg.calendar;

import java.time.LocalDate;

public class Schedule {
    private static int counter = 0;
    private final int id;
    private LocalDate date;
    private String description;

    public Schedule (LocalDate date, String description) {
        this.id = counter;
        counter++;
        this.date = date;
        this.description = description;
    }

    public int getId() {return this.id;}
    public LocalDate getDate() {return date;}
    public String getDescription() {return description;}

    public void setDate(LocalDate date) {this.date = date;}
    public void setDescription(String description) {this.description = description;}
}
