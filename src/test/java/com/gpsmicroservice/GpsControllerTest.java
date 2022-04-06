package com.gpsmicroservice;

import com.gpsmicroservice.model.Attraction;
import com.gpsmicroservice.model.Location;
import com.gpsmicroservice.model.VisitedLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GpsController.class)
class GpsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GpsService service;

  @Test
  void givenAUserWhenGetUserLocationThenUserVisitedLocationIsFoundWithStatus200() throws Exception {
    //Given
    VisitedLocation userPosition=
        new VisitedLocation(new UUID(1,1),
            new Location(20.,30.), new Date(2020-1900, 4,6));
    when(service.getUserLocation(any())).thenReturn(userPosition);

    //When
    mockMvc.perform(get("/userLocation/a76732e2-1d69-4386-b8c8-9e833d49adb2"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("{" +
            "\"userId\":\"00000000-0000-0001-0000-000000000001\"," +
            "\"location\":{\"longitude\":30.0,\"latitude\":20.0}," +
            "\"timeVisited\":\"2020-05-05T22:00:00.000+00:00\"" +
            "}"));
    //Then
  }

  @Test
  void givenThreeAttractionsWhenGetAttractionsThenListOfAttractionsWithStatus200() throws Exception {
    //Given
    Attraction attraction= new Attraction(new UUID(1,1),"Le Louvre","Paris","Paris",10.,10.);
    Attraction attraction1= new Attraction(new UUID(2,2),"Le Futuroscope","Poitier","Vendée",2.2,2.8);
    Attraction attraction2= new Attraction(new UUID(3,3),"Circuit de la Sarthe","Le Mans","Sarthe",0.4,8.5);

    when(service.getAttractions()).thenReturn(Arrays.asList(attraction,attraction1,attraction2));

    //When
    mockMvc.perform(get("/attractions"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("[" +
            "{\"longitude\":10.0," +
            "\"latitude\":10.0," +
            "\"attractionName\":\"Le Louvre\"," +
            "\"city\":\"Paris\"," +
            "\"state\":\"Paris\"," +
            "\"attractionId\":\"00000000-0000-0001-0000-000000000001\"" +
            "}," +
            "{\"longitude\":2.8," +
            "\"latitude\":2.2," +
            "\"attractionName\":\"Le Futuroscope\"," +
            "\"city\":\"Poitier\"," +
            "\"state\":\"Vendée\"," +
            "\"attractionId\":\"00000000-0000-0002-0000-000000000002\"" +
            "}," +
            "{\"longitude\":8.5," +
            "\"latitude\":0.4," +
            "\"attractionName\":\"Circuit de la Sarthe\"," +
            "\"city\":\"Le Mans\"," +
            "\"state\":\"Sarthe\"," +
            "\"attractionId\":\"00000000-0000-0003-0000-000000000003\"" +
            "}]"));
    //Then
  }
}