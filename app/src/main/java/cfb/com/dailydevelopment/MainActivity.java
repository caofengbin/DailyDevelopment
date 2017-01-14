package cfb.com.dailydevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cfb.com.dailydevelopment.example1.annotation.TestAnnotationActivity;
import cfb.com.dailydevelopment.example2.annotation.TestAnnotationActivity2;
import cfb.com.dailydevelopment.example3.lifecycle.LifeCycleActivity;
import cfb.com.dailydevelopment.example4.fragment.UseFragmentActivity;
import cfb.com.dailydevelopment.example5.parcable.ParcelableActivity;
import cfb.com.dailydevelopment.example7.textchange.TestChangeActivity;

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
                InnerType testType1 = new InnerType("a1","a1");
                InnerType testType2 = new InnerType("a2","a2");
                InnerType testType3 = new InnerType("a3","a3");
                List<InnerType> mList = new ArrayList<>();
                mList.add(testType1);
                mList.add(testType2);
                mList.add(testType3);

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("key1","第一个值");
                hashMap.put("key2","第二个值");
                hashMap.put("key3","第三个值");
                hashMap.put("keyList",mList);

                Bundle bundle = new Bundle();
                bundle.putSerializable("KEY_MAP", hashMap);
                Intent intent = new Intent(MainActivity.this, ParcelableActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 5:
                break;
            case 6:
                startIntent(TestChangeActivity.class);
                break;
        }
    }

    private void startIntent(Class class1){
        Intent intent = new Intent(MainActivity.this,class1);
        startActivity(intent);
    }

    public static class InnerType implements Serializable {
        public String key;
        public String value;

        public InnerType(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
