package cfb.com.dailydevelopment.example1.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cfb.com.dailydevelopment.R;

@ContentView(R.layout.activity_test_annoattion)
public class TestAnnotationActivity extends AppCompatActivity {

    @ViewInject(R.id.testButton)
    private Button mButton;

    @OnClick(R.id.testButton)
    private void onClick(View view) {
        mButton.setText("我是click后的文字内容");
        Toast.makeText(this, "按钮被点击了", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        mButton.setText("我是click前的Button内容");
    }

}
