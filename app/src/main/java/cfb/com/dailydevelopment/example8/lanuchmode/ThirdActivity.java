package cfb.com.dailydevelopment.example8.lanuchmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cfb.com.dailydevelopment.R;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.e(TAG, "This is third Activity,Task id is :" + getTaskId());
    }
}
