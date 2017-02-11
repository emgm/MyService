package tupan.myservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.bitmap;

public class MyService extends Service {

    public  Timer timer;

    public  SubTimer subTimer;

    public Context mContext;



    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {


        timer = new Timer();

        subTimer = new SubTimer();

        timer.scheduleAtFixedRate(subTimer, 0, 35000);

        mContext = this;



        super.onCreate();
        Toast.makeText(this, "Servicio creado!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Servicio en ejecucion!", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);



    }

    @Override
    public void onDestroy() {

        timer.cancel();

        timer.purge();


        super.onDestroy();
        Toast.makeText(this, "Servicio destruído!", Toast.LENGTH_SHORT).show();

    }

    class SubTimer extends TimerTask {



        @Override
        public void run() {


            somethingHappened(mContext);

        }


    }

    public void somethingHappened(final Context context)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(
                new Runnable()
                {
                    @Override
                    public void run()
                    {

                        Toast.makeText(context, "Something happened 2.", Toast.LENGTH_SHORT).show();

                        notification();
                    }
                }
        );
    }





    public void notification(){


            NotificationCompat.Builder mBuilder;
            NotificationManager mNotifyMgr = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);

            int icono = R.mipmap.ic_launcher;

            Intent i = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

            mBuilder = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(icono)
                    .setContentTitle("Pidelapp2")
                    .setContentText("msj")
                    .setVibrate(new long[]{100, 250, 100, 500})
                    .setAutoCancel(true);


            mNotifyMgr.notify(1, mBuilder.build());


    }

}

