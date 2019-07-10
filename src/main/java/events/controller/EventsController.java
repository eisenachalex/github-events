package events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@Controller
public class EventsController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/")
    public String landingPage() {
        return "index";
    }




}