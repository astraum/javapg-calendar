package javapg.calendar;

public class Calendar {

    private static final int[] LAST_DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int FIRST_UNIX_YEAR = 1970;
    private static final int FIRST_WEEKDAY_OF_UNIX_YEAR = 4;
    private final int year;
    private final int month;
    private final int lastDay;

    public Calendar (int year, int month) {
        this.year = year;
        this.month = month;
        this.lastDay = getLastDayOfMonth(year, month);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getLastDay() {
        return lastDay;
    }

    public static int getLastDayOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) {
            return LAST_DAYS_OF_MONTH[month - 1] + 1;
        } else {
            return LAST_DAYS_OF_MONTH[month - 1];
        }
    }

    public boolean isLeapYear() {
        if (year % 400 == 0) {return true;}
        else if (year % 100 == 0) {return false;}
        else return year % 4 == 0;
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {return true;}
        else if (year % 100 == 0) {return false;}
        else return year % 4 == 0;
    }

    private int getFirstWeekDayOfYear() {
        // 1970년 1월 1일 목요일을 기준으로 특정 연도 1월 1일의 요일을 계산하는 메소드
        // 일요일..토요일은 정수 0..6에 대응
        int firstWeekDayOfYear = FIRST_WEEKDAY_OF_UNIX_YEAR;

        // 1970년 이후의 달력
        for (int y = FIRST_UNIX_YEAR; y < year; y++) {
            firstWeekDayOfYear = firstWeekDayOfYear + ((isLeapYear(y)) ? 2 : 1);
            firstWeekDayOfYear = firstWeekDayOfYear % 7;
        }

        // 1970년 이전의 달력. 결과가 음수일 경우 양수로 변환한다. eg) 일요일 하루 전(-1) => 일요일 6일 후(6)
        for (int y = FIRST_UNIX_YEAR; y > year; y--) {
            firstWeekDayOfYear = firstWeekDayOfYear - ((isLeapYear(y-1)) ? 2 : 1);
            firstWeekDayOfYear = (firstWeekDayOfYear % 7 + 7) % 7;
        }

        return firstWeekDayOfYear;

    }

    private int getFirstWeekDayOfMonth() {
        // 1970년 1월 1일 목요일을 기준으로 특정 연월 1일의 요일을 계산하는 메소드
        int firstWeekDayOfMonth = getFirstWeekDayOfYear();
        for (int m = 1; m < month; m++) {
            firstWeekDayOfMonth += getLastDayOfMonth(year, m);
        }
        return firstWeekDayOfMonth % 7;
    }

    public void printCalendar() {
        int firstWeekDay = getFirstWeekDayOfMonth();
        System.out.printf("     %4d년%3d월    \n", year, month);
        System.out.println("Su Mo Tu We Th Fr Sa");
        System.out.println("--------------------");
        for (int i = 0; i < firstWeekDay; i++) {
            System.out.print("   ");
        }
        for (int i = 1; i <= lastDay; i++) {
            System.out.printf("%2d ", i);
            if ((i + firstWeekDay) % 7 == 0 || i == lastDay) {
                System.out.println();
            }
        }
        System.out.println();
    }

}
