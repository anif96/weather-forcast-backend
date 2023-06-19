package tg.weatherforcastbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForcastDto {
    private float cloudBase;
    private float cloudCeiling;
    private float cloudCover;
    private float  dewPoint;
    private float  evapotranspiration;
    private float  freezingRainIntensity;
    private int    humidity;
    private int    iceAccumulation;
    private int    iceAccumulationLwe;
    private float  precipitationProbability;
    private float  pressureSurfaceLevel;
    private float  rainAccumulation;
    private float  rainAccumulationLwe;
    private float  rainIntensity;
    private float  sleetAccumulation;
    private float  sleetAccumulationLwe;
    private float  sleetIntensity;
    private float  snowAccumulation;
    private float  snowAccumulationLwe;
    private float  snowIntensity;
    private float  temperature;
    private float  temperatureApparent;
    private float  uvHealthConcern;
    private float  uvIndex;
    private float  visibility;
    private float  weatherCode;
    private float  windDirection;
    private float  windGust;
    private float  windSpeed;
}
