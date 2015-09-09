package slidenerd.vivz.aademo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

// The layout is not set : we use the default layout set in ListActivity
public class MyListActivity extends ListActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    String[] bestFoods;

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bestFoods = getResources().getStringArray(R.array.bestFoods);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bestFoods);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
        setListAdapter(adapter);
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