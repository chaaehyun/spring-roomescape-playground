package roomescape.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import roomescape.Member;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ReservationController {
    private List<Member> reservations = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public ReservationController() {
        reservations.add(new Member(1L, "Brown", "2024-01-01", "10:00"));
        reservations.add(new Member(2L, "Brown", "2024-02-02", "12:00"));
        reservations.add(new Member(3L, "Brown", "2024-03-03", "15:00"));
    }

    @GetMapping("/reservation")
    public String Reservation(Model model) {
        model.addAttribute("reservations", reservations);
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public List<Member> getMember() {
        return reservations;
    }

}

