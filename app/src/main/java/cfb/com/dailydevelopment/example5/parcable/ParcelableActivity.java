package cfb.com.dailydevelopment.example5.parcable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cfb.com.dailydevelopment.R;

public class ParcelableActivity extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcalbe);

        mButton1 = (Button) findViewById(R.id.use_serializable_btn);
        mButton2 = (Button) findViewById(R.id.use_parcelable_btn);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParcelableActivity.this,AcceptValueActivity.class);

                // 传递基本类型数据的信息
                intent.putExtra("string_data","hello");
                intent.putExtra("int_data",100);

                // 传递序列化类型的数据
                ArrayList<Person> mList = new ArrayList<>();
                mList.add(new Person("cao",24));
                mList.add(new Person("yu",23));
                mList.add(new Person("yang",23));

                Person person = new Person("myName",55);
                intent.putExtra("list_data",mList);
                intent.putExtra("serializableObject",person);

                startActivity(intent);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParcelableActivity.this,AcceptValueActivity.class);

                Person2 person = new Person2("ParcelableName",50);
                intent.putExtra("ParcelableObject",person);

                startActivity(intent);
            }
        });

    }
}
