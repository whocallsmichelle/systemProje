package Jobs;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Jobs {

    public static void calljobs(){
        System.out.println(flushSessionsJob.flushSessions());
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println(flushbooklesauthorsjob.flushAuthors());
        }, 0, 60, TimeUnit.SECONDS
        );

    }
    public static void main(String[] args) {
      Jobs.calljobs();
    }
}
