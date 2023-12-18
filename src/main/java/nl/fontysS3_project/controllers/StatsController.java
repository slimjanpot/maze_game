package nl.fontysS3_project.controllers;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import nl.fontysS3_project.business.StatsManager;
import nl.fontysS3_project.domain.MapStats;
import nl.fontysS3_project.domain.OverallStats;
import nl.fontysS3_project.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4173", "http://localhost:5173" })
@AllArgsConstructor
public class StatsController {
    private final StatsManager statsManager;

    @GetMapping("{id}")
    @RolesAllowed({"NORMAL", "ADMIN"})
    public ResponseEntity<OverallStats> getOverallStats(@PathVariable(value = "id") final long id) {
        final OverallStats overall = statsManager.getOverallStats(id);
        return ResponseEntity.ok().body(overall);
    }

    @GetMapping("{id}/{mapid}")
    @RolesAllowed({"NORMAL", "ADMIN"})
    public ResponseEntity<MapStats> getMapStats(@PathVariable(value = "id") final long id, @PathVariable(value = "mapid") final long mapid) {
        final MapStats mapstats = statsManager.getMapStats(id, mapid);
        return ResponseEntity.ok().body(mapstats);
    }

}
