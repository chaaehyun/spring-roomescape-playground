package roomescape.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.Member;

import java.util.List;

@Controller
public class BoardController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
