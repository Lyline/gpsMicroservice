package com.gpsmicroservice.controller;

import com.gpsmicroservice.model.Attraction;
import com.gpsmicroservice.model.VisitedLocation;
import com.gpsmicroservice.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsController {
  @Autowired
  private GpsService service;

  @GetMapping("/userLocation/{userId}")
  public VisitedLocation getUserLocation(@PathVariable UUID userId){
    VisitedLocation location= service.getUserLocation(userId);

    return location;
  }

  @GetMapping("/attractions")
  public List<Attraction> getAttractions(){
    return service.getAttractions();
  }
}
