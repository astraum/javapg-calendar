package javapg.calendar;

import java.time.LocalDate;

// TODO: implement unique ID for each schedule item
public class Schedule {
    private int year;
    private int month;
    private int day;
    private String description;

    public Schedule (LocalDate date, String description) {
        this.year = date.getYear();
        this.month = date.getMonthValue();
        this.day = date.getDayOfMonth();
        this.description = description;
    }

    public Schedule (int year, int month, int day, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }

    public int getYear() {return year;}
    public int getMonth() {return month;}
    public int getDay() {return day;}
    public String getDescription() {return description;}

    public void print() {
        System.out.printf("%4s-%2s-%2s: %s%n", year, month, day, description);
    }

}
