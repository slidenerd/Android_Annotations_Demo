package slidenerd.vivz.aademo;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sub)
@OptionsMenu(R.menu.menu_sub)
public class SubActivity extends BaseActivity {

    @ViewById(R.id.text_message)
    TextView message;

    @AfterViews
    void setMessage() {
        message.setText("Hey is this working? ");
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_sub;
    }
}
