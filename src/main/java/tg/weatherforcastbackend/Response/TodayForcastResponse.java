package tg.weatherforcastbackend.Response;

public class TodayForcastResponse {
    private  String time;// HourlyDto
    private  float temperature;
    private  float temperatureApparent;
    private  String location;// LocationDto
    private  String sunsetTime;//DailyForcast
    private  float  Temp12Am;
    private  float  Temp2Pm;
    private  float  Temp4Pm;
    private  float  Temp6Pm;
    private  float  Temp8Pm;

    public TodayForcastResponse() {
        super();
    }

    public TodayForcastResponse(String time, float temperature, float temperatureApparent, String location, String sunsetTime, float temp12Am, float temp2Pm, float temp4Pm, float temp6Pm, float temp8Pm) {
        this.time = time;
        this.temperature = temperature;
        this.temperatureApparent = temperatureApparent;
        this.location = location;
        this.sunsetTime = sunsetTime;
        Temp12Am = temp12Am;
        Temp2Pm = temp2Pm;
        Temp4Pm = temp4Pm;
        Temp6Pm = temp6Pm;
        Temp8Pm = temp8Pm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperatureApparent() {
        return temperatureApparent;
    }

    public void setTemperatureApparent(float temperatureApparent) {
        this.temperatureApparent = temperatureApparent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public float getTemp12Am() {
        return Temp12Am;
    }

    public void setTemp12Am(float temp12Am) {
        Temp12Am = temp12Am;
    }

    public float getTemp2Pm() {
        return Temp2Pm;
    }

    public void setTemp2Pm(float temp2Pm) {
        Temp2Pm = temp2Pm;
    }

    public float getTemp4Pm() {
        return Temp4Pm;
    }

    public void setTemp4Pm(float temp4Pm) {
        Temp4Pm = temp4Pm;
    }

    public float getTemp6Pm() {
        return Temp6Pm;
    }

    public void setTemp6Pm(float temp6Pm) {
        Temp6Pm = temp6Pm;
    }

    public float getTemp8Pm() {
        return Temp8Pm;
    }

    public void setTemp8Pm(float temp8Pm) {
        Temp8Pm = temp8Pm;
    }
}
