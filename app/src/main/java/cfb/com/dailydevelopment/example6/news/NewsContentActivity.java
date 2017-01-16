package cfb.com.dailydevelopment.example6.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cfb.com.dailydevelopment.R;

/**
 * 新闻内容的Activity
 * 单页模式的时候会使用他
 */
public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra("new_title");
        String newsContent = getIntent().getStringExtra("new_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.activity_news_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }

    public static void actionStart(Context context,String newsTitle,String newsContent) {
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("new_title",newsTitle);
        intent.putExtra("new_content",newsContent);
        context.startActivity(intent);
    }
}
