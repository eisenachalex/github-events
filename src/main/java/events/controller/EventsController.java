package events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
public class EventsController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/")
    public String landingPage() {
        return "index";
    }


    @RequestMapping(value = "/events", produces = "application/json")
    @ResponseBody
    public String index(
            @RequestParam String username,
            @RequestParam String repo,
            @RequestParam String event
    ){
        String url = buildUrl(username, repo);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET , entity, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            List events = mapper.readValue(responseEntity.getBody(), List.class);
            Object[] eventArray= events
                    .stream()
                    .filter(student -> ((Map)student).get("type").equals(event))
                    .toArray();
        } catch(Exception e){
           System.out.println("error making request");
        }


        return responseEntity.getBody();
    }

    private String buildUrl(String username, String repo){
        return "https://api.github.com/repos/" + username + "/" + repo + "/events" ;
    }




}