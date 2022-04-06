package com.gpsmicroservice;

import gpsUtil.GpsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public GpsUtil getGpsUtil(){return new GpsUtil();}
}
