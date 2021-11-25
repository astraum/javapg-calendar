package javapg.calendar;

import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public void addSchedule(LocalDate date, String description) {
        Schedule schedule = new Schedule(date, description);
        this.schedules.add(schedule);
    }

    public void printSchedulesAll() {
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
            if (!schedule.getDate().isBefore(sinceThisDate) && !schedule.getDate().isAfter(untilThisDate)) {
                filteredList.add(schedule);
            }
        }
        return filteredList;
    }

    private void printSchedule(Schedule schedule) {
        LocalDate date = schedule.getDate();
        String desc = schedule.getDescription();
        System.out.printf("%s: %s%n", date, desc);
    }
}
