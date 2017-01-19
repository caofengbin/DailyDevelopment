package cfb.com.dailydevelopment.example8.lanuchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cfb.com.dailydevelopment.R;
import cfb.com.dailydevelopment.example9.bestactivity.BaseActivity;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.e(TAG, "This is second Activity,Task id is :" + getTaskId());

        Button mButton = (Button) findViewById(R.id.second_activity_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
