package nl.fontysS3_project.controllers;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.*;
import nl.fontysS3_project.controllers.Request_Response.CreateUserRequest;
import nl.fontysS3_project.controllers.Request_Response.CreateUserResponse;
import nl.fontysS3_project.controllers.Request_Response.GetAllUsersResponse;
import nl.fontysS3_project.domain.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4173", "http://localhost:5173" })
@AllArgsConstructor
public class UserController {
private final UserManager userManager;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        User response = userManager.createUser(ConverterUser.converttouser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ConverterUser.converttoresponse(response));
    }
    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        List<User> response = userManager.getUsers();
        return ResponseEntity.ok(GetAllUsersResponse.builder().users(response).build());
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userManager.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final int id) {
        final Optional<User> userOptional = userManager.getUser(id);
        return userOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
