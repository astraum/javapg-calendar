package javapg.calendar;

import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public void addSchedule(LocalDate date, String description) {
        Schedule schedule = new Schedule(date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                description);
        this.schedules.add(schedule);
    }

    public void printAll() {
        for (Schedule schedule : schedules) {
            printSchedule(schedule);
        }
    }

    public void printFilteredSchedules(LocalDate sinceThisDate, LocalDate untilThisDate) {
        ArrayList<Schedule> filteredList = filterByDate(sinceThisDate, untilThisDate);
        for (Schedule schedule : filteredList) {
            printSchedule(schedule);
        }
    }

    private ArrayList<Schedule> filterByDate(LocalDate sinceThisDate, LocalDate untilThisDate) {
        ArrayList<Schedule> filteredList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            LocalDate date = LocalDate.of(schedule.getYear(), schedule.getMonth(), schedule.getDay());
            if (!date.isBefore(sinceThisDate) && !date.isAfter(untilThisDate)) {
                filteredList.add(schedule);
            }
        }
        return filteredList;
    }

    private void printSchedule(Schedule schedule) {
        System.out.printf("%4s-%2s-%2s: %s%n",
                schedule.getYear(),
                schedule.getMonth(),
                schedule.getDay(),
                schedule.getDescription());
    }
}
