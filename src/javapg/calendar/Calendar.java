package javapg.calendar;

public class Calendar {

    public static int a = 56;
    public static void main(String[] args) {
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
}
