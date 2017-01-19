package cfb.com.dailydevelopment.example8.lanuchmode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cfb.com.dailydevelopment.R;
import cfb.com.dailydevelopment.example9.bestactivity.ActivityCollector;
import cfb.com.dailydevelopment.example9.bestactivity.BaseActivity;

public class ThirdActivity extends BaseActivity {

    private static final String TAG = "activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.e(TAG, "This is third Activity,Task id is :" + getTaskId());

        Button mButton = (Button) findViewById(R.id.third_activity_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAllActivity();
            }
        });
    }

    public static void actionStart(Context context,String data1,String data2) {
        Intent intent = new Intent(context,ThirdActivity.class);
        intent.putExtra("parameter1",data1);
        intent.putExtra("parameter2",data2);
        context.startActivity(intent);
    }
}
