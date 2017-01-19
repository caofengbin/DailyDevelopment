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
import cfb.com.dailydevelopment.example4.fragment.UseFragmentActivity;
import cfb.com.dailydevelopment.example5.parcable.ParcelableActivity;
import cfb.com.dailydevelopment.example6.news.NewsFragmentActivity;
import cfb.com.dailydevelopment.example7.textchange.TestChangeActivity;
import cfb.com.dailydevelopment.example8.lanuchmode.FirstActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
            case 3:
                startIntent(UseFragmentActivity.class);
                break;
            case 4:
                // 测试Serializable与Parcelable的区别的Activity
                startIntent(ParcelableActivity.class);
                break;
            case 5:
                startIntent(NewsFragmentActivity.class);
                break;
            case 6:
                startIntent(TestChangeActivity.class);
                break;
            case 7:
                startIntent(FirstActivity.class);
                break;
            case 8:
                startIntent(FirstActivity.class);
                break;
        }
    }

    private void startIntent(Class class1){
        Intent intent = new Intent(MainActivity.this,class1);
        startActivity(intent);
    }
}
