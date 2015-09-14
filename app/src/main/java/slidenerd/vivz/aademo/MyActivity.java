package slidenerd.vivz.aademo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@EActivity(R.layout.my_activity)
@OptionsMenu(R.menu.menu_main)
public class MyActivity extends AppCompatActivity {
    @ViewById
    EditText myEditText;
    @ViewById(R.id.myTextView)
    TextView textView;
    @StringRes(R.string.hello)
    String helloFormat;
    @ColorRes(R.color.android_color)
    int androidColor;
    @ViewById
    Button startExtraActivity;
    @ViewById
    Button startListActivity;
    @SystemService
    NotificationManager notificationManager;
    @SystemService
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
        // windowManager should not be null
        windowManager.getDefaultDisplay();
    }

    @OptionsItem(R.id.item2)
    public void handleItem2() {
        Toast.makeText(this, "Item 2 was selected", Toast.LENGTH_SHORT).show();
    }

    @OptionsItem(R.id.item1)
    public void handleItem1() {
        SubActivity_.intent(this).start();
    }

    @Click(R.id.myButton)
    public void myButtonClicked() {
        String name = myEditText.getText().toString();
        runInBackground(name, 5);
    }

    @Background
    void runInBackground(String name, long timeToDoSomeLongComputation) {
        try {
            TimeUnit.SECONDS.sleep(timeToDoSomeLongComputation);
        } catch (InterruptedException e) {
        }
        String message = String.format(helloFormat, name);
        updateUi(message, androidColor);
    }

    @UiThread(delay = 2000)
    void updateUi(String message, int color) {
        textView.setText(message);
        textView.setTextColor(color);
        showNotificationsDelayed();
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
        ActivityWithExtra_.intent(this)
                .extra(ActivityWithExtra.MY_STRING_EXTRA, "hello")
                .extra(ActivityWithExtra.MY_DATE_EXTRA, new Date().getTime())
                .extra(ActivityWithExtra.MY_INT_EXTRA, 100)
                .start();

    }

    @LongClick(R.id.startExtraActivity)
    public void buttonStartExtraActivityLongClicked() {
        startExtraActivity();
    }

    @Click(R.id.startListActivity)
    public void buttonStartListActivityClick() {
        MyListActivity_.intent(this).start();
    }

    @Touch(R.id.myTextView)
    public void textViewTouch(MotionEvent event) {
        Toast.makeText(this, "TextView was touched", Toast.LENGTH_SHORT).show();
    }

}