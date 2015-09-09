package slidenerd.vivz.aademo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyActivity extends Activity implements View.OnLongClickListener, View.OnClickListener, View.OnTouchListener {

    EditText myEditText;
    TextView textView;
    String helloFormat;
    int androidColor;
    Button startExtraActivity;
    Button startListActivity;
    NotificationManager notificationManager;
    WindowManager windowManager;


    /**
     * AndroidAnnotations gracefully handles support for onBackPressed, whether you use ECLAIR (2.0), or pre ECLAIR android version.
     */
    public void onBackPressed() {
        Toast.makeText(this, "Back key pressed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.my_activity);


        myEditText = (EditText) findViewById(R.id.myEditText);
        textView = (TextView) findViewById(R.id.myTextView);
        helloFormat = getString(R.string.hello);
        androidColor = ContextCompat.getColor(this, R.color.android_color);
        startExtraActivity = (Button) findViewById(R.id.startExtraActivity);
        startListActivity = (Button) findViewById(R.id.startListActivity);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        textView.setOnTouchListener(this);
        startExtraActivity.setOnLongClickListener(this);
        startListActivity.setOnClickListener(this);
        // windowManager should not be null
        windowManager.getDefaultDisplay();

    }

    public void myButtonClicked(View view) {
        String name = myEditText.getText().toString();
        setProgressBarIndeterminateVisibility(true);
        new MyTask(name, 5).execute();
    }

    void updateUi(String message, int color) {
        setProgressBarIndeterminateVisibility(false);
        textView.setText(message);
        textView.setTextColor(color);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void showNotificationsDelayed() {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Hello !")
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .build();
        notificationManager.notify(1, notification);
    }

    void startExtraActivity() {
        Intent intent = new Intent(this, ActivityWithExtra.class);
        intent.putExtra(ActivityWithExtra.MY_DATE_EXTRA, new Date().getTime());
        intent.putExtra(ActivityWithExtra.MY_STRING_EXTRA, "hello !");
        intent.putExtra(ActivityWithExtra.MY_INT_EXTRA, 42);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        startExtraActivity();
        return true;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MyListActivity.class));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("MyActivity", "myTextView was touched!");
        return true;
    }


    public class MyTask extends AsyncTask<Void, Void, String> {

        String name;
        long timeToDoSomeLongComputation;

        public MyTask(String name, long timeToDoSomeLongComputation) {
            this.name = name;
            this.timeToDoSomeLongComputation = timeToDoSomeLongComputation;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(timeToDoSomeLongComputation);
            } catch (InterruptedException e) {
            }
            String message = String.format(helloFormat, name);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            updateUi(s, androidColor);
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showNotificationsDelayed();
        }
    }

}