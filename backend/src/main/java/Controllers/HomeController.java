package Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {


    //first endpoint
    @GetMapping("/")
    public String home() {
        return "ENICAR Social Media Application Backend";
    }
}
