package javapg.calendar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Schedule> scheduleList = new ArrayList<>();

    public ArrayList<Schedule> getScheduleList() {
        return new ArrayList<>(scheduleList);
    }

    public void addSchedule(LocalDate date, String description) {
        Schedule schedule = new Schedule(date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                description);
        this.scheduleList.add(schedule);
    }

    public void printAll() {
        for (Schedule schedule : scheduleList) {
            printSchedule(schedule);
        }
    }

    public void printFilteredSchedules(LocalDate sinceThisDate, LocalDate untilThisDate) {
        ArrayList<Schedule> filteredList = filterByDate(sinceThisDate, untilThisDate);
        for (Schedule schedule : filteredList) {
            printSchedule(schedule);
        }
    }

    public void exportToJson(String filepath) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter(filepath);
        gson.toJson(scheduleList, writer);
        writer.close();
    }

    public void importFromJson(String filepath) throws FileNotFoundException {
        Type scheduleListType = new TypeToken<ArrayList<Schedule>>(){}.getType();
        Gson gson = new Gson();
        Reader reader = new FileReader(filepath);
        scheduleList = gson.fromJson(reader, scheduleListType);
    }

    private ArrayList<Schedule> filterByDate(LocalDate sinceThisDate, LocalDate untilThisDate) {
        ArrayList<Schedule> filteredList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
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
