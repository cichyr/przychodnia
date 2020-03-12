package pl.clinic.hello_world.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.clinic.hello_world.controller.dto.HelloMessageResource;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<HelloMessageResource> getHelloMessage(){
        return ResponseEntity.ok(new HelloMessageResource("Hello World!"));
    }
}
