package com.gpsmicroservice;

import com.gpsmicroservice.model.Attraction;
import com.gpsmicroservice.model.Location;
import com.gpsmicroservice.model.VisitedLocation;
import gpsUtil.GpsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class GpsService {

  private final gpsUtil.GpsUtil gps;

  public GpsService(GpsUtil gps) {
    this.gps=gps;
  }

  public VisitedLocation getUserLocation(UUID userId){
    Locale.setDefault(Locale.US);
    gpsUtil.location.VisitedLocation location= gps.getUserLocation(userId);

    return new VisitedLocation(userId, new Location(location.location.latitude,location.location.longitude),
                              location.timeVisited);
  }

  public List<Attraction> getAttractions(){
    List<gpsUtil.location.Attraction> gpsAttractions= gps.getAttractions();
    List<Attraction> attractions= new ArrayList<>();

    for (gpsUtil.location.Attraction attract:gpsAttractions){
      Attraction attraction= new Attraction(attract.attractionName, attract.city, attract.state,
                                            attract.latitude,attract.longitude);
      attractions.add(attraction);
    }

    return attractions;
  }
}
