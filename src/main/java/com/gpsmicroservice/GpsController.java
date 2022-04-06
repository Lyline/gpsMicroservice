package com.gpsmicroservice;

import com.gpsmicroservice.model.Attraction;
import com.gpsmicroservice.model.VisitedLocation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsController {

  private final GpsService service;

  public GpsController(GpsService service) {
    this.service = service;
  }

  @GetMapping("/userLocation/{userId}")
  public VisitedLocation getUserLocation(@PathVariable UUID userId){
    return service.getUserLocation(userId);
  }

  @GetMapping("/attractions")
  public List<Attraction> getAttractions(){
    return service.getAttractions();
  }
}
