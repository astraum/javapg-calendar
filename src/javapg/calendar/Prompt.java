package javapg.calendar;

import java.util.Scanner;

public class Prompt {

    public void runPrompt() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("년도를 입력하세요. (exit on -1)");
            System.out.print("YEAR> ");
            int year = scanner.nextInt();
            if (year == -1) {break;}

            System.out.println("달을 입력하세요. (exit on -1)");
            System.out.print("MONTH> ");
            int month = scanner.nextInt();
            if (month == -1) {break;}

            if (year < 1) {System.out.println("잘못된 입력입니다."); continue;}
            if (month == 0 || month < -1 || month > 12) {System.out.println("잘못된 입력입니다."); continue;}

            Calendar calendar = new Calendar(year, month);

            calendar.printCalendar();

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

    public static void main(String[] args) {

        Prompt p = new Prompt();
        p.runPrompt();
    }
}
