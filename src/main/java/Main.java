import java.text.ParseException;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static long calculateDaysToNextBirthday() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите дату в формате dd-MM-yyyy");
        String strDate = sc.nextLine();
//        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = s.parse(strDate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(("dd-MM-yyyy"));
        LocalDate localDate = LocalDate.parse(strDate, dateTimeFormatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        sc.close();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month + 1, day);
        LocalDate thisYearsBirthday = birthday.with(Year.now());
        //String dateOfBorn = s.format(date);
        if (year < 1890 || year > 2021) {
            throw new UnsupportedOperationException("Неверные диапазоны года рождения");
        } else {
            return ChronoUnit.DAYS.between(today, thisYearsBirthday);
        }
    }


    public static void main(String[] args) {
        System.out.println("Количество дней до следующего дня рождения: " + calculateDaysToNextBirthday());
    }
}
