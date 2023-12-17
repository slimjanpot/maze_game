package nl.fontysS3_project.controllers;

import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.UserManager;
import nl.fontysS3_project.controllers.Request_Response.CreateMapScoreRequest;
import nl.fontysS3_project.controllers.Request_Response.CreateMapScoreResponse;
import nl.fontysS3_project.domain.MapScore;
import nl.fontysS3_project.domain.User;

@AllArgsConstructor
public class ConverterMapScore {

    public static MapScore convertToMapSore(CreateMapScoreRequest request, User user){
        return MapScore.builder()
                .map(request.getMap())
                .time(request.getTime())
                .user(user)
                .build();
    }

    public static CreateMapScoreResponse convertToResponse(MapScore mp){
        return CreateMapScoreResponse.builder()
                .mapid(mp.getId())
                .build();
    }
}
