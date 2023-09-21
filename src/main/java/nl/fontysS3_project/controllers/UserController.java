package nl.fontysS3_project.controllers;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.*;
import nl.fontysS3_project.domain.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
private final CreateUser createUser;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUser.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
