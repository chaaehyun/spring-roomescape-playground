package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import roomescape.Reservation;
import roomescape.repository.QueryingDAO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {

    private final QueryingDAO queryingDAO;

    public ReservationController(QueryingDAO queryingDAO){
        this.queryingDAO=queryingDAO;
    }

    @GetMapping("/reservation")
    public String Reservation(Model model) {
        model.addAttribute("reservations", queryingDAO.findAll());
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(queryingDAO.findAll());
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> create(@RequestBody Reservation request) {
        if(request.getName() == null || request.getName().isBlank() ||
                request.getDate() == null || request.getTime() == null){
            throw new IllegalArgumentException("데이터가 비었습니다.");
        }

        final Reservation newReservation = queryingDAO.insert(request);
        return ResponseEntity.created(URI.create("/reservations/"+newReservation.getId())).body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean flag = queryingDAO.delete(id);

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
