package slidenerd.vivz.aademo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;


public class ActivityWithExtra extends AppCompatActivity {

    public static final String MY_STRING_EXTRA = "myStringExtra";
    public static final String MY_DATE_EXTRA = "myDateExtra";
    public static final String MY_INT_EXTRA = "myIntExtra";

    TextView extraTextView;

    String myMessage;
    Date myDate;


    /**
     * The logs will output a classcast exception, but the program flow won't be interrupted
     */
    int myInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_extra);

        extraTextView = (TextView) findViewById(R.id.extraTextView);
        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            myMessage = extras.getString(MY_STRING_EXTRA);
            myDate = new Date(extras.getLong(MY_DATE_EXTRA));
            myInt = extras.getInt(MY_INT_EXTRA);
        }
        init();
    }

    protected void init() {
        extraTextView.setText(myMessage + " " + myDate + " " + myInt);
    }

}