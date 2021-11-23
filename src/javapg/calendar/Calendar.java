package javapg.calendar;

import java.util.Scanner;

public class Calendar {

    private static final int[] LAST_DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int month;
    private final int lastDay;

    public Calendar (int month) {
        this.month = month;
        this.lastDay = LAST_DAYS_OF_MONTH[month-1];
    }

    public int getMonth() {
        return month;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void printCalendar(int month) {

        System.out.printf("        %d월        \n", month);
        System.out.println("Su Mo Tu We Th Fr Sa");
        System.out.println("--------------------");
        for (int i = 1; i <= lastDay; i++) {
            System.out.printf("%2d ", i);
            if (i % 7 == 0 || i == lastDay) {
                System.out.print("\n");
            }
        }
    }

}
