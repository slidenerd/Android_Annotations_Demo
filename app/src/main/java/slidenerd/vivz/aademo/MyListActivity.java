package slidenerd.vivz.aademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

// The layout is not set : we use the default layout set in ListActivity
public class MyListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    String[] bestFoods;

    private ListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list_activity);
        bestFoods = getResources().getStringArray(R.array.bestFoods);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bestFoods);
        listView = (ListView) findViewById(R.id.my_list);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(adapter);
    }

    void listItemClicked(String food) {
        Toast.makeText(this, "click: " + food, Toast.LENGTH_SHORT).show();
    }

    void listItemLongClicked(String food) {
        Toast.makeText(this, "long click: " + food, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listItemClicked(bestFoods[position]);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        listItemLongClicked(bestFoods[position]);
        return true;
    }

}