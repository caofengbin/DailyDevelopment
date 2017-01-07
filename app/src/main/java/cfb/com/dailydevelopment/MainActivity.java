package cfb.com.dailydevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cfb.com.dailydevelopment.example1.annotation.TestAnnotationActivity;
import cfb.com.dailydevelopment.example2.annotation.TestAnnotationActivity2;
import cfb.com.dailydevelopment.example3.lifecycle.LifeCycleActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String[] mainItems;
    private ListView mMainListView;
    private ArrayAdapter<String> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainItems = getResources().getStringArray(R.array.main_view_string_array);
        mMainListView = (ListView) findViewById(R.id.main_activity_listView);
        itemAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mainItems);

        mMainListView.setAdapter(itemAdapter);
        mMainListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                startIntent(TestAnnotationActivity.class);
                break;
            case 1:
                startIntent(TestAnnotationActivity2.class);
                break;
            case 2:
                startIntent(LifeCycleActivity.class);
                break;
        }
    }

    private void startIntent(Class class1){
        Intent intent = new Intent(MainActivity.this,class1);
        startActivity(intent);
    }
}
