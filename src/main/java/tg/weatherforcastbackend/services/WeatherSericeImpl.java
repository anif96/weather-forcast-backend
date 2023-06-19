package tg.weatherforcastbackend.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.function.numeric.Min;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tg.weatherforcastbackend.Response.SevenDayForcastResponse;
import tg.weatherforcastbackend.Response.TodayForcastResponse;
import tg.weatherforcastbackend.Response.TomorrowForcastResponse;
import tg.weatherforcastbackend.config.JacksonConfiguration;
import tg.weatherforcastbackend.dto.*;
import tg.weatherforcastbackend.utility.WeatherConstant;

import java.io.DataInput;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WeatherSericeImpl implements WeatherService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper objectMapper = new ObjectMapper();
    private JacksonConfiguration jacksonConfiguration;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${tomorrow.api.baseUrl}")
    private String BaseUrl;
    @Value("${tomorrow.api.secret}")
    private String apiKey;

    public WeatherSericeImpl() {
    }

    @Override
    public WeatherDto getWeatherForcast(String location, String timesteps, String units) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = UriComponentsBuilder.fromHttpUrl(BaseUrl)
                .queryParam(WeatherConstant.TOMORROW_API_LOCATION, location)
                .queryParam(WeatherConstant.TOMORROW_API_Timesteps, timesteps)
                .queryParam(WeatherConstant.Weather_Metric, units)
                .queryParam(WeatherConstant.TOMORROW_API_AUTHORIZATION_KEY, apiKey)
                .build().encode().toString();
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        Map<String, String> result = JsonPath.read(responseEntity.getBody(), "$");
        JsonNode timelines = objectMapper.readTree(responseEntity.getBody()).get("timelines");
        ArrayNode hourlyList = (ArrayNode)timelines.get("hourly");
        ObjectReader readerHourly =objectMapper.readerFor(new TypeReference<List<HourlyDto>>() {
        });
        List<HourlyDto> hourlyDtoList =readerHourly.readValue(hourlyList);
        ArrayNode dailyList = (ArrayNode)timelines.get("daily");
        ObjectReader readerDaily =objectMapper.readerFor(new TypeReference<List<DailyDto>>() {
        });
        List<DailyDto> dailyDtoList =readerDaily.readValue(dailyList);
        JsonNode Location = objectMapper.readTree(responseEntity.getBody()).get("location");
        WeatherDto weatherDto = new WeatherDto();
        LocationDto locationDto = objectMapper.treeToValue(Location,LocationDto.class);
        TimeLineDTo timeLineDTo = new TimeLineDTo();
        timeLineDTo.setHourly(hourlyDtoList);
        timeLineDTo.setDaily(dailyDtoList);
        weatherDto.setLocationDto(locationDto);
        weatherDto.setTimelines(timeLineDTo);
        return  weatherDto;
    }

    @Override
    public WeatherDto getWeatherRecentHistory(String location, String timesteps, String units) throws IOException {
        return null;
    }

    @Override
    public TodayForcastResponse convertToTodayForcastResponse(WeatherDto weatherDto) {
        TodayForcastResponse todayForcastResponse = new TodayForcastResponse();
        todayForcastResponse.setLocation(weatherDto.getLocationDto().getName());
        todayForcastResponse.setTime(weatherDto.getTimelines().getDaily().get(0).getTime());
        todayForcastResponse.setTemperatureApparent(weatherDto.getTimelines().getDaily().get(0).getValues().getTemperatureApparentAvg());
        todayForcastResponse.setTemperature(weatherDto.getTimelines().getDaily().get(0).getValues().getTemperatureAvg());
        todayForcastResponse.setSunsetTime(weatherDto.getTimelines().getDaily().get(0).getValues().getSunsetTime());

        ZonedDateTime d = ZonedDateTime.parse(todayForcastResponse.getTime());
        List<HourlyDto> hourlyDtoList = weatherDto.getTimelines().getHourly();

        for(HourlyDto hourlyDto : hourlyDtoList){
            ZonedDateTime time = ZonedDateTime.parse(hourlyDto.getTime());
            int hour=   time.getHour();
        }
        List<DailyDto> dailyDtoList   = weatherDto.getTimelines().getDaily();

        return todayForcastResponse;
    }

    @Override
    public TomorrowForcastResponse convertToTomorrowForcastResponse(WeatherDto weatherDto) {
        TomorrowForcastResponse tomorrowForcastResponse = new TomorrowForcastResponse();
        tomorrowForcastResponse .setLocation(weatherDto.getLocationDto().getName());
        tomorrowForcastResponse .setTime(weatherDto.getTimelines().getDaily().get(1).getTime());
        tomorrowForcastResponse .setTemperatureApparent(weatherDto.getTimelines().getDaily().get(1).getValues().getTemperatureApparentAvg());
        tomorrowForcastResponse .setTemperature(weatherDto.getTimelines().getDaily().get(1).getValues().getTemperatureAvg());
        tomorrowForcastResponse .setSunsetTime(weatherDto.getTimelines().getDaily().get(1).getValues().getSunsetTime());
        List<HourlyDto> hourlyDtoList = weatherDto.getTimelines().getHourly();
        ZonedDateTime tomorrowTime = ZonedDateTime.parse(weatherDto.getTimelines().getDaily().get(1).getTime());
        Instant instantTomorrow = tomorrowTime.toInstant();
        Date dateTomorrow =  Date.from(instantTomorrow);
        List<HourlyDto> hourlyDtoResponse = new ArrayList<>();
        return tomorrowForcastResponse;
    }

    @Override
    public List<SevenDayForcastResponse>  convertToSevenDayForcastResponse(WeatherDto weatherDto) {
        List<SevenDayForcastResponse> sevenDayForcastResponse = new ArrayList<>();
         List<DailyDto> dailyDtoList = weatherDto.getTimelines().getDaily();
        for (DailyDto dailyDto : dailyDtoList) {
            SevenDayForcastResponse sevenDayForcastResponse1 = new SevenDayForcastResponse();
            sevenDayForcastResponse1.setTime(dailyDto.getTime());
            sevenDayForcastResponse1.setHumidityAvg(dailyDto.getValues().getHumidityAvg());
            sevenDayForcastResponse1.setTemperatureMax(dailyDto.getValues().getTemperatureMax());
            sevenDayForcastResponse1.setTemperatureMin(dailyDto.getValues().getTemperatureMin());
            sevenDayForcastResponse1.setUvIndexAvg(dailyDto.getValues().getUvIndexAvg());
            sevenDayForcastResponse1.setWindSpeedAvg(dailyDto.getValues().getWindSpeedAvg());
            sevenDayForcastResponse.add(sevenDayForcastResponse1);
        }
        return sevenDayForcastResponse;
    }
}
