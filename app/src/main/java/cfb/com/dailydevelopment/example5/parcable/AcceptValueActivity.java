package cfb.com.dailydevelopment.example5.parcable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import cfb.com.dailydevelopment.R;

public class AcceptValueActivity extends AppCompatActivity {

    private static final String TAG = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_value);

        // 获取基本类型数据的信息
        String tempString = getIntent().getStringExtra("string_data");
        int tempInt = getIntent().getIntExtra("int_data",0);

        // 获取序列化对象类型
        Person tempPerson = (Person) getIntent().getSerializableExtra("serializableObject");
        ArrayList<Person> tempPersonList = (ArrayList<Person>) getIntent().getSerializableExtra("list_data");

        // 获取Parcelable对象的类型
        Person2 tempPerson2 = getIntent().getParcelableExtra("ParcelableObject");

        Log.e(TAG, "tempPerson2:name:" + tempPerson2.getName());
        Log.e(TAG, "tempPerson2:age:" + String.valueOf(tempPerson2.getAge()));
    }
}
