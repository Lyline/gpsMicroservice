package com.gpsmicroservice;

import com.gpsmicroservice.model.VisitedLocation;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GpsServiceTest {

  private final gpsUtil.GpsUtil mockGps= mock(gpsUtil.GpsUtil.class);

  private final GpsService classUnderTest= new GpsService(mockGps);

  @Test
  void givenAUserWhenGetUserLocationThenUserVisitedLocationIsFound() {
    //Given
    gpsUtil.location.VisitedLocation userPosition=
        new gpsUtil.location.VisitedLocation(new UUID(1,1),
                                              new Location(20.,30.),new Date());

    when(mockGps.getUserLocation(any())).thenReturn(userPosition);

    //When
    VisitedLocation actual=classUnderTest.getUserLocation(new UUID(1,1));

    //Then
    assertThat(actual.location.latitude).isEqualTo(20);
    assertThat(actual.location.longitude).isEqualTo(30);

    verify(mockGps,times(1)).getUserLocation(new UUID(1,1));
  }

  @Test
  void givenThreeAttractionsWhenGetAttractionsThenListOfAttractionsIsFound() {
    //Given
    gpsUtil.location.Attraction attraction= new Attraction("Le Louvre","Paris","Paris",10.,10.);
    gpsUtil.location.Attraction attraction1= new Attraction("Le Futuroscope","Poitier","Vendée",2.2,2.8);
    gpsUtil.location.Attraction attraction2= new Attraction("Circuit de la Sarthe","Le Mans","Sarthe",0.4,8.5);

    when(mockGps.getAttractions()).thenReturn(Arrays.asList(attraction,attraction1,attraction2));

    //When
    List<com.gpsmicroservice.model.Attraction> actual= classUnderTest.getAttractions();

    //Then
    assertThat(actual.size()).isEqualTo(3);
  }
}