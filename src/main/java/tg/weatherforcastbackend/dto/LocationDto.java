package tg.weatherforcastbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private BigDecimal lat;
    private BigDecimal lon;
    private String name;
    private String type;
}
