package cfb.com.dailydevelopment.example5.parcable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cfb.com.dailydevelopment.MainActivity;
import cfb.com.dailydevelopment.R;

public class ParcelableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcalbe);

        Bundle b = this.getIntent().getExtras();
        HashMap<String, Object> hashMap = (HashMap<String, Object>) b.getSerializable("KEY_MAP");
        List<MainActivity.InnerType> infoList = (ArrayList) hashMap.get("keyList");
        Log.e("ParcelableActivity", (String)hashMap.get("key1"));
        Log.e("ParcelableActivity", (String)hashMap.get("key2"));
        Log.e("ParcelableActivity", (String)hashMap.get("key3"));
        Log.e("ParcelableActivity", infoList.size()+"");
        Log.e("ParcelableActivity", infoList.get(0).key + ":" + infoList.get(0).value);
        Log.e("ParcelableActivity", infoList.get(1).key + ":" + infoList.get(1).value);
    }
}
