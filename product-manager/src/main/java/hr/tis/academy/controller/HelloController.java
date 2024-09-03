package hr.tis.academy.controller;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.HelloResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/hello-json")
    public HelloResponse helloJson() {
        return new HelloResponse("HELLO");
    }

    @GetMapping("/days-of-week")
    public ResponseEntity<DaysOfWeekResponse> daysOfWeek() {
        return new ResponseEntity<>(helloService.daysOfWeek(), HttpStatus.OK);
    }
}
