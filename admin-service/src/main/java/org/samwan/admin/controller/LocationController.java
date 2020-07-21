package org.samwan.admin.controller;

import org.samwan.admin.exceptions.LocationNotFoundException;
import org.samwan.admin.models.Location;
import org.samwan.admin.payload.request.LocationRequest;
import org.samwan.admin.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/")
    public ResponseEntity<?> getLocations(){

        return ResponseEntity.ok(locationRepository.findAll());
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Long id){
        return locationRepository.findById(id)
                .orElseThrow(() ->new LocationNotFoundException(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody LocationRequest request){

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());

        Location location = new Location();
        location.setName(request.getName());
        location.setDescription(request.getDescription());
        //location.setCreatedAt(zonedDateTime.toLocalDateTime());
        location.setActive(true);

        return new ResponseEntity<>(locationRepository.save(location), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public Location update(@RequestBody Location request, @PathVariable Long id){

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());

        return locationRepository.findById(id)
                .map(location -> {
                    location.setName(request.getName());
                    location.setDescription(request.getDescription());
                    //location.setModifiedAt(zonedDateTime.toLocalDateTime());
                    return locationRepository.save(location);
                })
                .orElseGet(() -> {
                    Location location = new Location();

                    location.setId(id);
                    location.setName(request.getName());
                    location.setDescription(request.getDescription());
                    return locationRepository.save(location);
                });
    }
}
