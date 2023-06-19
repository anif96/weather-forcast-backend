package tg.weatherforcastbackend.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.weatherforcastbackend.Response.SevenDayForcastResponse;
import tg.weatherforcastbackend.Response.TodayForcastResponse;
import tg.weatherforcastbackend.Response.TomorrowForcastResponse;
import tg.weatherforcastbackend.dto.WeatherDto;
import tg.weatherforcastbackend.services.WeatherService;
import tg.weatherforcastbackend.utility.WeatherConstant;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/weather/")
public class WeatherController {

   @Autowired
    WeatherService weatherService;
    @GetMapping("forcast-today")
    public ResponseEntity<TodayForcastResponse> actionGetWeatherForcastToday(@RequestParam("location") String location ) throws IOException {
        WeatherDto weatherDto = weatherService.getWeatherForcast(location,WeatherConstant.Weather_timestep_value,WeatherConstant.Weather_units_value);
        TodayForcastResponse todayForcastResponse = weatherService.convertToTodayForcastResponse(weatherDto);
        return new ResponseEntity<>(todayForcastResponse, HttpStatus.OK);
    }

    @GetMapping("forcast-tomorrow")
    public ResponseEntity<TomorrowForcastResponse> actionGetWeatherForcastTomorrow(@RequestParam("location") String location) throws IOException {
        WeatherDto weatherDto = weatherService.getWeatherForcast(location,WeatherConstant.Weather_timestep_value,WeatherConstant.Weather_units_value);
        TomorrowForcastResponse tomorrowForcastResponse = weatherService.convertToTomorrowForcastResponse(weatherDto);
        return new ResponseEntity<>(tomorrowForcastResponse, HttpStatus.OK);
    }

    @GetMapping("forcast-seven-day")
    public ResponseEntity<List<SevenDayForcastResponse>> actionGetWeatherForcastNextSevenDays(@RequestParam("location") String location ) throws IOException {
        WeatherDto weatherDto = weatherService.getWeatherForcast(location,WeatherConstant.Weather_timestep_value,WeatherConstant.Weather_units_value);
        List<SevenDayForcastResponse> sevenDayForcastResponse = weatherService.convertToSevenDayForcastResponse(weatherDto);
        return new ResponseEntity<>(sevenDayForcastResponse, HttpStatus.OK);
    }
}
