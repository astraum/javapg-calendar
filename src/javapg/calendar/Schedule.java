package javapg.calendar;

public class Schedule {
    private int year;
    private int month;
    private int day;
    private String description;

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

}
