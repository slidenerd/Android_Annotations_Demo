package slidenerd.vivz.aademo;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

/**
 * Created by vivz on 14/09/15.
 */
@EActivity(R.layout.activity_sub)
public abstract class BaseActivity extends AppCompatActivity {
    @ViewById(R.id.content_frame)
    FrameLayout root;
    @ViewById(R.id.text_message)
    TextView message;
    @SystemService
    LayoutInflater inflater;

    @AfterViews
    void onMessageReady() {

        View view = inflater.inflate(getActivityLayout(), root, false);
        root.addView(view);
        message.setText("I am now ready my boy");
    }

    public abstract int getActivityLayout();
}
