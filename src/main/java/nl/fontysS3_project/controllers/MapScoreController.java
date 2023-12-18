package nl.fontysS3_project.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.MapScoreManager;
import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.controllers.Request_Response.CreateMapScoreRequest;
import nl.fontysS3_project.controllers.Request_Response.CreateMapScoreResponse;
import nl.fontysS3_project.domain.MapScore;
import nl.fontysS3_project.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
@RequestMapping("/map")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4173", "http://localhost:5173" })
@AllArgsConstructor
public class MapScoreController {

    private final MapScoreManager mapScoreManager;
    private final UserManager userManager;

    @PostMapping()
    public ResponseEntity<CreateMapScoreResponse> createMapScore(@RequestBody @Valid CreateMapScoreRequest request){
        System.out.println("got from FE: "+request);
        User user = userManager.getUser(request.getUserid());
        MapScore mapScore = mapScoreManager.createMapScore(ConverterMapScore.convertToMapSore(request,user));
        System.out.println("onverted one: "+ConverterMapScore.convertToMapSore(request,user));
        System.out.println("From manager: "+mapScore);
        return ResponseEntity.status(HttpStatus.CREATED).body(ConverterMapScore.convertToResponse(mapScore));
    }
}
