package javapg.calendar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

// TODO: divide ScheduleList into smaller unit (e.g. by month) for faster read/write
public class ScheduleList {
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public void add(Schedule schedule) {
        schedules.add(schedule);
    }

    // TODO: implement remove method
    // TODO: implement sort by date method

    public void printAll() {
        schedules.forEach(Schedule::print);
    }

    public ScheduleList filterByDate(LocalDate sinceThisDate, LocalDate untilThisDate) {

        ScheduleList filteredList = new ScheduleList();

        for (Schedule s : schedules) {
            LocalDate date = LocalDate.of(s.getYear(), s.getMonth(), s.getDay());
            if (!date.isBefore(sinceThisDate) && !date.isAfter(untilThisDate)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    public boolean hasScheduleOn(int year, int month, int day) {
        for (Schedule s : schedules) {
            if (year == s.getYear() && month == s.getMonth() && day == s.getDay()) {
                return true;
            }
        }
        return false;
    }

    // TODO: make Gson methods work with LocalDate fields

    public void exportToJson(String filepath) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter(filepath);
        gson.toJson(schedules, writer);
        writer.close();
    }

    public void importFromJson(String filepath) throws FileNotFoundException {
        Type scheduleListType = new TypeToken<ArrayList<Schedule>>(){}.getType();
        Gson gson = new Gson();
        Reader reader = new FileReader(filepath);
        schedules = gson.fromJson(reader, scheduleListType);
    }
}
