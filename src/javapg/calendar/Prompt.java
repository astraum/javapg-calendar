package javapg.calendar;

import java.time.LocalDate;
import java.util.Scanner;

public class Prompt {

    public void printMenu() {
        System.out.print(
                """
                        +-----------------------+
                        |  1. 일정 등록
                        |  2. 일정 검색
                        |  3. 달력 보기
                        |  h. 도움말 / q. 종료
                        +-----------------------+
                        """);
    }

    public void runPrompt() {
        Scheduler scheduler = new Scheduler();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            printMenu();
            System.out.println("입력 (1, 2, 3, h, q): ");
            String cmd = scanner.nextLine();

            if (cmd.equals("q")) {break;}

            switch (cmd) {
                case "1" -> cmdRegister(scheduler, scanner);
                case "2" -> cmdView(scheduler, scanner);
                case "3" -> cmdShowCalendar(scanner);
                case "h" -> printMenu();
            }

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

    private void cmdRegister(Scheduler scheduler, Scanner scanner) {
        System.out.println("일정을 입력하십시오.");
        System.out.print("날짜 (yyyy-MM-dd)> ");
        LocalDate inputDate = LocalDate.parse(scanner.nextLine());
        System.out.print("일정 메모> ");
        String inputDesc = scanner.nextLine();
        scheduler.addSchedule(inputDate, inputDesc);
    }

    private void cmdView(Scheduler scheduler, Scanner scanner) {
        System.out.print("""
                +-----------------------+
                |  1. 모든 일정 표시
                |  2. 날짜로 검색
                +-----------------------+
                """);
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "1" -> scheduler.printSchedulesAll();
            case "2" -> cmdSearch(scheduler, scanner);
        }
    }

    private void cmdSearch(Scheduler scheduler, Scanner scanner) {
        System.out.println("표시할 일정의 범위를 입력하십시오.");
        System.out.print("시작 날짜 (yyyy-MM-dd)> ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("마지막 날짜 (yyyy-MM-dd)> ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        scheduler.printFilteredSchedules(startDate, endDate);
    }

    private void cmdShowCalendar(Scanner scanner) {

        System.out.println("년도를 입력하세요.");
        System.out.print("YEAR> ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("달을 입력하세요.");
        System.out.print("MONTH> ");
        int month = scanner.nextInt();
        scanner.nextLine();

        if (year < 1) {System.out.println("잘못된 입력입니다."); return;}
        if (month == 0 || month < -1 || month > 12) {System.out.println("잘못된 입력입니다."); return;}

        Calendar calendar = new Calendar(year, month);

        calendar.printCalendar();
    }

    public static void main(String[] args) {

        Prompt p = new Prompt();
        p.runPrompt();
    }
}
