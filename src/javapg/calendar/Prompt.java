package javapg.calendar;

import java.util.Scanner;

public class Prompt {

    private static final String PROMPT = "cal> ";

    public void runPrompt() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("달을 입력하세요.");
            System.out.print(PROMPT);
            int month = scanner.nextInt();

            if (month == -1) {break;}
            if (month == 0 || month < -1 || month > 12) {continue;}

            Calendar calendar = new Calendar(month);

            calendar.printCalendar(month);

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

    public static void main(String[] args) {

        Prompt p = new Prompt();
        p.runPrompt();
    }
}
