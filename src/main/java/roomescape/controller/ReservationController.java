package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import roomescape.Reservation;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(1);

//    public ReservationController() {
//        reservations.add(new Reservation(1L, "Brown", "2024-01-01", "10:00"));
//        reservations.add(new Reservation(2L, "Brown", "2024-02-02", "12:00"));
//        reservations.add(new Reservation(3L, "Brown", "2024-03-03", "15:00"));
//    }

    @GetMapping("/reservation")
    public String Reservation(Model model) {
        model.addAttribute("reservations", reservations);
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getMember() {
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> create(@RequestBody Reservation request) {
        if(request.getName() == null || request.getName().isBlank() ||
            request.getDate() == null || request.getTime() == null){
            throw new IllegalArgumentException("데이터가 비었습니다.");
        }

        System.out.printf("dddd");
                    
        final Reservation newReservation = new Reservation(index.getAndIncrement(), request.getName(), request.getDate(), request.getTime());
        reservations.add(newReservation); // 데이터베이스에 저장
        return ResponseEntity.created(URI.create("/reservations/"+newReservation.getId())).body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean flag = false;
        Reservation reservation = reservations.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        flag=true;

        reservations.remove(reservation);
        
        if(!flag){
            throw new IllegalArgumentException("예약이 없습니다.");
        }

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}


