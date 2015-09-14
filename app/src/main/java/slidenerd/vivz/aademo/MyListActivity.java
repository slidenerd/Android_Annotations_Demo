package slidenerd.vivz.aademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

// The layout is not set : we use the default layout set in ListActivity
@EActivity(R.layout.my_list_activity)
public class MyListActivity extends AppCompatActivity{

    @StringArrayRes(R.array.bestFoods)
    String[] bestFoods;

    private ListAdapter adapter;
    @ViewById(R.id.my_list)
    ListView listView;

    @AfterViews
    public void afterReady(){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bestFoods);
        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.my_list)
    void listItemClicked(String food) {
        Toast.makeText(this, "click: " + food, Toast.LENGTH_SHORT).show();
    }

    @ItemLongClick(R.id.my_list)
    void listItemLongClicked(String food) {
        Toast.makeText(this, "long click: " + food, Toast.LENGTH_SHORT).show();
    }

}