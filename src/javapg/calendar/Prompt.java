package javapg.calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Prompt {

    public void printMenu() {
        System.out.print("""
               +-----------------------+
               |  1. 일정 등록
               |  2. 일정 검색
               |  3. 일정 저장
               |  4. 달력 보기
               |  h. 도움말 / q. 종료
               +-----------------------+
                        """);
    }

    public void runPrompt() throws IOException {

        String jsonFilePath = "schedules.json";

        ScheduleList scheduleList = new ScheduleList();

        try {
            scheduleList.importFromJson(jsonFilePath);
            System.out.println("저장된 일정이 로드되었습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("저장된 일정이 존재하지 않습니다.");
        }

        Scanner scanner = new Scanner(System.in);

        while(true) {
            printMenu();
            System.out.println("입력 (1, 2, 3, 4, h, q): ");
            String cmd = scanner.nextLine();

            if (cmd.equals("q")) {break;}

            switch (cmd) {
                case "1" -> cmdRegister(scheduleList, scanner);
                case "2" -> cmdView(scheduleList, scanner);
                case "3" -> scheduleList.exportToJson(jsonFilePath);
                case "4" -> cmdShowCalendar(scheduleList, scanner);
                case "h" -> printMenu();
            }

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

    private void cmdRegister(ScheduleList scheduler, Scanner scanner) {
        System.out.println("일정을 입력하십시오.");
        System.out.print("날짜 (yyyy-MM-dd)> ");
        LocalDate inputDate = LocalDate.parse(scanner.nextLine());
        System.out.print("일정 메모> ");
        String inputDesc = scanner.nextLine();
        scheduler.add(new Schedule(inputDate, inputDesc));
    }

    private void cmdView(ScheduleList scheduler, Scanner scanner) {
        System.out.print("""
                +-----------------------+
                |  1. 모든 일정 표시
                |  2. 날짜로 검색
                +-----------------------+
                """);
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "1" -> scheduler.printAll();
            case "2" -> cmdSearch(scheduler, scanner);
        }
    }

    private void cmdSearch(ScheduleList scheduler, Scanner scanner) {
        System.out.println("표시할 일정의 범위를 입력하십시오.");
        System.out.print("시작 날짜 (yyyy-MM-dd)> ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("마지막 날짜 (yyyy-MM-dd)> ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        scheduler.filterByDate(startDate, endDate).printAll();
    }

    private void cmdShowCalendar(ScheduleList scheduler, Scanner scanner) {

        System.out.println("년도를 입력하세요.");
        System.out.print("YEAR> ");
        int year = scanner.nextInt();
        scanner.nextLine();

        if (year < 1) {System.out.println("잘못된 입력입니다."); return;}

        System.out.println("달을 입력하세요.");
        System.out.print("MONTH> ");
        int month = scanner.nextInt();
        scanner.nextLine();

        if (month == 0 || month < -1 || month > 12) {System.out.println("잘못된 입력입니다."); return;}

        CalendarPrinter calendarPrinter = new CalendarPrinter(year, month);

        calendarPrinter.printCalendar(scheduler);

        System.out.printf("%d년 %2d월에는 다음과 같은 일정이 있습니다...\n", year, month);
        LocalDate sinceThisDate = LocalDate.of(year, month, 1);
        LocalDate untilThisDate = LocalDate.of(year, month, calendarPrinter.getLastDayOfMonth());
        scheduler.filterByDate(sinceThisDate, untilThisDate).printAll();
        System.out.println();
        System.out.print("엔터로 돌아가기 ");
        scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {

        Prompt p = new Prompt();
        p.runPrompt();
    }
}
