import java.util.Date;

public class Timer {
  public static long startTime;

  public static void start(){
    startTime = System.currentTimeMillis();
  }

  public static Long stop(){
    long time = System.currentTimeMillis() - startTime;

    startTime = 0;
    return time;
  }
}
