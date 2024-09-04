package hr.tis.academy.controller;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.HelloResponse;
import hr.tis.academy.dto.IsWeekendResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;
import java.util.List;

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

    @GetMapping("/days-of-week")
    public ResponseEntity<DaysOfWeekResponse> daysOfWeek() {
        return new ResponseEntity<>(helloService.daysOfWeek(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<String> greeter(@RequestParam(required = false) List<String> namesList){
        return namesList != null ? new ResponseEntity<>(helloService.greet(namesList), HttpStatus.OK)
                : ResponseEntity.ok("NEMA IMENA");
    }

    @GetMapping("/is-weekend")
    public ResponseEntity<IsWeekendResponse> isWeekend(@RequestParam(required = false) DayOfWeek day){
        return new ResponseEntity<>(helloService.isWeekend(day), HttpStatus.OK);
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam(defaultValue = "Hello TIS!") String text,
                                           @RequestParam int width,
                                           @RequestParam int height,
                                           @RequestParam int red,
                                           @RequestParam int green,
                                           @RequestParam int blue){

        try {
            byte[] imageBytes =  helloService.createImage(text, width, height, red, green, blue);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
