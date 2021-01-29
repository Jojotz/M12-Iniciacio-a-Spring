package springBootInitialDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import springBootInitialDemo.dto.UserResponseDto;
import springBootInitialDemo.service.IUserService;

@RestController
@RequestMapping("/v1")
public class InitialController {

    private final IUserService userService;

    @Autowired
    public InitialController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public String helloGradle() {
        return "Hello Gradle!";
    }
           
    @GetMapping("/")
    public String helloUser(@RequestParam(value = "name", defaultValue = "World") String name) {
    	return String.format("Hello %s!", name); 
    	//Petició GET a Postman: localhost:8080/v1/?name=Joan
	}
    
    //FASE 3 
    /* Local -> IP, value: localhost:{{port}}/v1/
     * 			port, value: 8080
     *
    */
    //@PutMapping(value ="", consumes = {"application/json"})
    @GetMapping("/user/{uuid}")
    public ResponseEntity<UserResponseDto> updatePrescription(
            @PathVariable(name="uuid") String user) throws Exception {


        UserResponseDto userResponseDto = userService.getUser("pp");

        System.out.println(userResponseDto.getName());
        System.out.println(userResponseDto.getSurname());
        System.out.println(userResponseDto.getGender());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }


}
