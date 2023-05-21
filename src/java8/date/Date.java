package java8.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date {
    public static void main(String[] args) {
        // String 값 표현
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(LocalDateTime.of(2023,1,1,12,0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // 날짜 더하기
        LocalDateTime plusTenDay = LocalDateTime.now().plusDays(10L);

        // 날짜와 시간 간격을 나타내는
        // Duration, Period
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newYear = LocalDateTime.of(2023, 1, 1, 12, 0);

        // 시간 차이 비교
        Duration duration = Duration.between(now, newYear);
        System.out.println(duration.getSeconds());

        // 날짜 차이 비교
        Period period = Period.between(now.toLocalDate(), newYear.toLocalDate());
        System.out.println(period.getDays());

        // 날짜 포맷팅
        System.out.println(
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("오늘은 y-mm-dd일 (E)이고, 현재 시간은 a h:s분 입니다.")
                                .withLocale(Locale.KOREA))
        );
    }
}
