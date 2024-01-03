package nl.fontysS3_project.controllers;

import jakarta.annotation.security.RolesAllowed;
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

    @PostMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody @Valid CreateUserRequest request){
        System.out.println(request);
        User response = userManager.updateUser(ConverterUser.converttouser(request), userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        List<User> response = userManager.getUsers();
        return ResponseEntity.ok(GetAllUsersResponse.builder().users(response).build());
    }

    @DeleteMapping("{userId}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userManager.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @RolesAllowed({"NORMAL", "ADMIN"})
    public ResponseEntity<User> getUser(@PathVariable(value = "id") final long id) {
        final User user = userManager.getUser(id);
        return ResponseEntity.ok().body(user);
    }

}
