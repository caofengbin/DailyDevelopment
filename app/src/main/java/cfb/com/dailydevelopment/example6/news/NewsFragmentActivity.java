package cfb.com.dailydevelopment.example6.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cfb.com.dailydevelopment.R;

/**
 * 新闻页面的主Activity
 * 本例子的核心界面
 * 提供了两个activity_news_fragment布局文件
 * 会根据系统分辨率的大小进行动态的加载
 */
public class NewsFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_fragment);
    }
}
