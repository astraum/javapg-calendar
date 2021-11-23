package javapg.calendar;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        // 입력: 키보드로 두 수의 입력을 받는다.
        // 출력: 화면에 두 수의 합을 출력한다.
        int a, b;
        Scanner scanner = new Scanner(System.in);
        System.out.println("첫번째 수 입력: ");
        a = Integer.parseInt(scanner.nextLine());
        System.out.println("두번째 수 입력: ");
        b = Integer.parseInt(scanner.nextLine());
        System.out.printf("합계: %d", a+b);
//        scanner.close();
    }
}
