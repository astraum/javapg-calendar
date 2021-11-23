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

        Scanner scanner = new Scanner(System.in);
        System.out.println("반복횟수를 입력하세요.");
        int repeat = scanner.nextInt();

        for (int i = 0; i < repeat; i++) {
            System.out.println("달을 입력하세요.");
            int month = scanner.nextInt();
            int lastDay = lastDayOfMonth(month);

            System.out.printf("%d월은 %d일까지 있습니다.\n", month, lastDay);

        }
        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }

}
