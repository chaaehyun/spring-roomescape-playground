package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Member {
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Member(Long id, String name, LocalDate date, LocalTime time) {
    }

    public Member(Long id, String name, String dateString, String timeString) {
        this.id = id;
        this.name = name;
        this.date = LocalDate.parse(dateString, DATE_FORMATTER);
        this.time = LocalTime.parse(timeString, TIME_FORMATTER);
    }

    public Member(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {return time; }

    public static Member toEntity(Member member, Long id) {
        return new Member(id, member.name, member.date, member.time);
    }

    public void update(Member newMember) {
        this.name = newMember.name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
