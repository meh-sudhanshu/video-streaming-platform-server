package in.codelantern.cmrvideostreaming.controllers;


import in.codelantern.cmrvideostreaming.models.User;
import in.codelantern.cmrvideostreaming.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            HashMap<String,String> errorMap = new HashMap<>();
            for(FieldError error : result.getFieldErrors()){
                var field = error.getField();
                var defaultMessage = error.getDefaultMessage();
                errorMap.put(field,defaultMessage);
            }

            return  new ResponseEntity<>(errorMap, HttpStatus.OK);
        }
        var savedUser =  userRepository.save(user);
        return  new ResponseEntity<>(savedUser,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/login")
    public ResponseEntity<?> attemptLogin(@RequestBody User user){
        var email = user.getEmail();
        var password = user.getPassword();
        Optional<User> userObject = userRepository.findByEmail(email);
        if(userObject.isPresent()){
            if(userObject.get().getPassword().equals(password)){
                return new ResponseEntity<>("login successful",HttpStatusCode.valueOf(200));
            }else{
                return new ResponseEntity<>("login denied",HttpStatusCode.valueOf(200));
            }
        }else{
            return new ResponseEntity<>("user with provided email does not exists",HttpStatus.valueOf(200));
        }
    }







}

