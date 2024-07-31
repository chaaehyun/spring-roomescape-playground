package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public Reservation() {
    }

    public Reservation (Long id, String name, LocalDate date, LocalTime time) {
        this.id = id;
        this.name= name;
        this.date = date;
        this.time =time;
    }

//    public Reservation(Long id, String name, String dateString, String timeString) {
//        this.id = id;
//        this.name = name;
//        this.date = LocalDate.parse(dateString, DATE_FORMATTER);
//        this.time = LocalTime.parse(timeString, TIME_FORMATTER);
//    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
