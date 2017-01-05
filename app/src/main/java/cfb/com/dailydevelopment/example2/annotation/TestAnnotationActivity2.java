package cfb.com.dailydevelopment.example2.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.cfb.myannotation.BindView;
import com.cfb.myannotation.OnClick;
import com.cfb.myapi.MyButterKnife;

import cfb.com.dailydevelopment.R;

public class TestAnnotationActivity2 extends AppCompatActivity {

    @BindView(R.id.button1)
    Button mButton1;

    @BindView(R.id.button2)
    Button mButton2;

    @OnClick({R.id.button1})
    public void click() {
        Toast.makeText(this, "点击了第一个按钮", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.button2})
    public void click2() {
        Toast.makeText(this, "点击了第二个按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_annotation2);
        MyButterKnife.bind(this);
    }
}
