package javapg.calendar;

import java.util.Scanner;

public class Calendar {

    private static final int[] lastDaysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static int lastDayOfMonth(int month) {
        return lastDaysOfMonth[month-1];
    }

    public static void printCalendar() {
        System.out.println(" 일 월  화 수  목 금  토");
        System.out.println("--------------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 7; j++) {
                int day = i * 7 + j;
                if (day < 10) {
                    System.out.print(" " + day + " ");
                } else {
                    System.out.print(day + " ");
                }
            }
            System.out.print("\n");
        }
    }

    public static int a = 56;
    public static void main(String[] args) {

        String PROMPT="cal> ";
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("달을 입력하세요.");
            System.out.print(PROMPT);
            int month = scanner.nextInt();

            if (month == -1) {break;}
            if (month == 0 || month < -1 || month > 12) {continue;}

            int lastDay = lastDayOfMonth(month);

            System.out.printf("%d월은 %d일까지 있습니다.\n", month, lastDay);

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

}
