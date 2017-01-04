package cfb.com.dailydevelopment.example1.annotation;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        Toast.makeText(this,"按钮被点击了",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        mButton.setText("我是click前的Button内容");
    }

    @NonNull
    private String testAnnotationMethod(@Nullable String inputString) {
        return inputString == null ? "MyString" : inputString;
    }

    public static final int FIRST_NUMBER = 1;
    public static final int SECOND_NUMBER = 2;
    public static final int THIRD_NUMBER = 3;

    @IntDef({FIRST_NUMBER,SECOND_NUMBER,THIRD_NUMBER})
    public @interface Flavour {

    }

    private int myValue;

    @Flavour
    public int getFlavour() {
        return myValue;
    }

    public void setFlavour(@Flavour int value) {
        this.myValue = value;
    }
}
