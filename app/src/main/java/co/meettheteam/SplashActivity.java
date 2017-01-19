package co.meettheteam;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by dharma on 11/16/16.
 */
public class SplashActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread splashThread = new Thread() {
           @Override
           public void run() {
              try {
                 sleep(1200);
              } catch (InterruptedException e) {
                 // do nothing
              } finally {
                 finish();
                 Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.right_slide_in, R.anim.stay);
              }
           }
        };
        splashThread.start();
    }
 }
