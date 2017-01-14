package cfb.com.dailydevelopment.example7.textchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import cfb.com.dailydevelopment.R;

public class TestChangeActivity extends AppCompatActivity {

    @Bind(R.id.trade_text_view)
    EditText mTextView;

    private static final int MAX_VALUE = 1000;

    private static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_change);

        ButterKnife.bind(this);

        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged: " );
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged: " );
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(TAG, "afterTextChanged: " );
                if (s != null && s.length() != 0) {
                    try {
                        // 设置监听的上限
                        double totalPrice = Double.valueOf(s.toString());
                        if (totalPrice > MAX_VALUE) {
                            Toast.makeText(TestChangeActivity.this,"输入价格超过上限:" + MAX_VALUE,Toast.LENGTH_SHORT).show();
                            s.clear();
                            s.append(String.valueOf(MAX_VALUE));
                        }

                        // 限制price最大输入3位数
                        BigDecimal price = new BigDecimal(s.toString());
                        int scale = price.scale();
                        if (scale > 3) {
                            s.delete(s.length() - 1, s.length());
                        }
                    } catch (Exception e) {
                        // 首次输入小数点修正
                        String point = s.toString();
                        if (point.equals(".")) {
                            mTextView.setText("0.");
                            s.clear();
                            s.append("0.");
                            mTextView.setSelection(mTextView.getText().toString().length());
                        }
                    }
                }
            }
        });

    }
}
