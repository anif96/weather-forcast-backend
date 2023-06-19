package tg.weatherforcastbackend.Response;

public class SevenDayForcastResponse {
    private  String time;// HourlyDto
    private float uvIndexAvg;
    private float windSpeedAvg;
    private float temperatureMax;
    private float  temperatureMin;
    private float humidityAvg;

    public SevenDayForcastResponse() {
    }

    public SevenDayForcastResponse(String time, float uvIndexAvg, float windSpeedAvg, float temperatureMax, float temperatureMin, float humidityAvg) {
        this.time = time;
        this.uvIndexAvg = uvIndexAvg;
        this.windSpeedAvg = windSpeedAvg;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.humidityAvg = humidityAvg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getUvIndexAvg() {
        return uvIndexAvg;
    }

    public void setUvIndexAvg(float uvIndexAvg) {
        this.uvIndexAvg = uvIndexAvg;
    }

    public float getWindSpeedAvg() {
        return windSpeedAvg;
    }

    public void setWindSpeedAvg(float windSpeedAvg) {
        this.windSpeedAvg = windSpeedAvg;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getHumidityAvg() {
        return humidityAvg;
    }

    public void setHumidityAvg(float humidityAvg) {
        this.humidityAvg = humidityAvg;
    }
}
