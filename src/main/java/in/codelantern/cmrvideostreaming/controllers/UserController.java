package in.codelantern.cmrvideostreaming.controllers;


import in.codelantern.cmrvideostreaming.models.User;
import in.codelantern.cmrvideostreaming.models.UserData;
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
@CrossOrigin
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


    @PostMapping("/get-user-by-id/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> attemptLogin(@RequestBody User user){
        var email = user.getEmail();
        var password = user.getPassword();
        HashMap<String,String> responseMap = new HashMap<>();
        Optional<User> userObject = userRepository.findByEmail(email);
        if(userObject.isPresent()){
            if(userObject.get().getPassword().equals(password)){
                responseMap.put("isAuthenticated","true");
                var userID = userObject.get().getId();
                responseMap.put("userID",userID+"");
                return new ResponseEntity<>(responseMap,HttpStatusCode.valueOf(200));
            }else{
                responseMap.put("isAuthenticated","false");
                responseMap.put("reason","Password does not match");
                return new ResponseEntity<>(responseMap,HttpStatusCode.valueOf(200));
            }
        }else{
            responseMap.put("isAuthenticated","false");
            responseMap.put("reason","email is incorrect");
            return new ResponseEntity<>(responseMap,HttpStatus.valueOf(200));
        }
    }


    @PostMapping("/save-user-data/{id}")
    public ResponseEntity<?> saveUserData(@RequestBody UserData userData, @PathVariable Long id){
        return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
    }


}

