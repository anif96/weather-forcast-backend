package tg.weatherforcastbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLineDTo {
  private List<HourlyDto> hourly;
  private List<DailyDto> daily;
}
