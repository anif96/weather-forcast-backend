package tg.weatherforcastbackend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import tg.weatherforcastbackend.Response.SevenDayForcastResponse;
import tg.weatherforcastbackend.Response.TodayForcastResponse;
import tg.weatherforcastbackend.Response.TomorrowForcastResponse;
import tg.weatherforcastbackend.dto.WeatherDto;

import java.io.IOException;
import java.util.List;

public interface WeatherService {
    WeatherDto getWeatherForcast(String location , String timesteps , String units) throws IOException;
    WeatherDto getWeatherRecentHistory(String location , String timesteps , String units) throws IOException;
    TodayForcastResponse convertToTodayForcastResponse(WeatherDto weatherDto);
    TomorrowForcastResponse convertToTomorrowForcastResponse(WeatherDto weatherDto);
   List<SevenDayForcastResponse> convertToSevenDayForcastResponse (WeatherDto weatherDto);
}
