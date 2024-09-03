package hr.tis.academy.controller;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.HelloResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("HELLO");
    }

    @GetMapping("/hello-json")
    public HelloResponse helloJson(@RequestParam(required = false) String helloString){
        return new HelloResponse(helloString);
    }

    @GetMapping("days-of-week")
    public ResponseEntity<DaysOfWeekResponse> daysOfWeek() {
        return new ResponseEntity<>(helloService.daysOfWeek(), HttpStatus.OK);
    }

    @GetMapping("names")
    public ResponseEntity<String> greeter(@RequestParam(required = false) List<String> namesList){
        return namesList != null ? new ResponseEntity<>(helloService.greet(namesList), HttpStatus.OK)
                : ResponseEntity.ok("NEMA IMENA");
    }

}
