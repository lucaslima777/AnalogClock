package analog.clock.lln;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SimpleAnalogClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = (SimpleAnalogClock) findViewById(R.id.clock);
        clock.setSecondTint(getResources().getColor(R.color.colorPrimary));

        runThread();

    }


    private void runThread() {
        new Thread() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        runOnUiThread(new Runnable() {
                            Date dt = new Date();
                            int hours = dt.getHours();
                            int minutes = dt.getMinutes();
                            int seconds = dt.getSeconds();

                            @Override
                            public void run() {

                                clock.setTime(hours, minutes, seconds);
                            }
                        });
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
