package javapg.calendar;

/**
 * 특정 년월의 달력을 콘솔에 출력한다.
 * 일정이 있는 날짜는 색깔로 표시해 준다.
 * 연습 삼아 기본 라이브러리의 Date, Calendar, LocalDate 등의 기능은 사용하지 않았음.
 */
public class CalendarPrinter {

    private static final int[] LAST_DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int FIRST_UNIX_YEAR = 1970;
    private static final int FIRST_WEEKDAY_OF_UNIX_YEAR = 4; // Thursday
    private static final String COLOR_CODE = "\u001b[33m";
    private static final String RESET_CODE = "\u001b[0m";
    private final int year;
    private final int month;
    private final int lastDayOfMonth;

    public CalendarPrinter(int year, int month) {
        this.year = year;
        this.month = month;
        this.lastDayOfMonth = getLastDayOfMonth(year, month);
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth;
    }

    public void printCalendar(ScheduleList scheduleList) {
        int firstWeekDay = getFirstWeekDayOfMonth();

        System.out.printf("     %4d년%3d월    \n", year, month);
        System.out.println("Su Mo Tu We Th Fr Sa");
        System.out.println("--------------------");

        for (int i = 0; i < firstWeekDay; i++) {
            System.out.print("   ");
        }

        for (int i = 1; i <= lastDayOfMonth; i++) {
            if (scheduleList.hasScheduleOn(year, month, i)) {
                System.out.printf("%s%2d %s", COLOR_CODE, i, RESET_CODE);
            } else {
                System.out.printf("%2d ", i);
            }
            if ((i + firstWeekDay) % 7 == 0 || i == lastDayOfMonth) {
                System.out.println();
            }
        }

        System.out.println();
    }

    // 윤년 여부 판단
    private boolean isLeapYear(int year) {
        if (year % 400 == 0) {return true;}
        else if (year % 100 == 0) {return false;}
        else return year % 4 == 0;
    }

    // 특정 달의 마지막 날(=일수) 계산
    private int getLastDayOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) {
            return LAST_DAYS_OF_MONTH[month - 1] + 1;
        } else {
            return LAST_DAYS_OF_MONTH[month - 1];
        }
    }

    /**
     * 1970년 1월 1일을 기준으로 달력이 표시할 연도의 첫번째 요일을 계산한다.
     * 일요일..토요일은 정수 0..6에 대응한다.
     */
    private int getFirstWeekDayOfYear() {
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

    /**
     * 1970년 1월 1일을 기준으로 달력이 표시할 달의 첫번째 요일을 계산한다.
     * 일요일..토요일은 정수 0..6에 대응한다.
     */
    private int getFirstWeekDayOfMonth() {
        int firstWeekDayOfMonth = getFirstWeekDayOfYear();

        for (int m = 1; m < month; m++) {
            firstWeekDayOfMonth += getLastDayOfMonth(year, m);
        }
        return firstWeekDayOfMonth % 7;
    }

}
