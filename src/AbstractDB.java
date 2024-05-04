import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface AbstractDB {
  public  void storeFiveDayData(String city, JSONObject fiveDayForecast);
  public  JSONArray getFiveDaysData(String city);
  public  void storeThreeHourly(String city, JSONObject threeHourly);
  public  JSONArray getThreeHourlyData(String city);
  public  void storeGasesComponent(String city, JSONObject gasesComp);
  public  JSONArray getGasesComponent(String city);
  public  void storeAQIComponent(String city, long AQI);
  public  double getAQIForCity(String city);
}