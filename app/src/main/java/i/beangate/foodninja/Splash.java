package i.beangate.foodninja;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Splash screen timer
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    Log.d("@@@@@@@@@", "Welcome");
                    finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
