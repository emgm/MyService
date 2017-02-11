package tupan.myservice;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    @Override
    public void onResume(){


        stopService(new Intent(MainActivity.this, MyService.class));

        super.onResume();

    }




    @Override
    public void onStop(){


        startService(new Intent(MainActivity.this, MyService.class));

        super.onStop();

    }



}
