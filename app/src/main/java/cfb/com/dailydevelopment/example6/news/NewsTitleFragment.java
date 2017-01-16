package cfb.com.dailydevelopment.example6.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cfb.com.dailydevelopment.R;

/**
 * 新闻标题的Fragment
 * Created by Administrator on 2017/1/16.
 */

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_title_frag,container,false);
        RecyclerView newsTitleRecycler = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecycler.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for(int i = 1 ; i < 50 ; i++) {
            News news = new News();
            news.setTitle("This is news title" + i);
            news.setContent(getRandomLengthContent("This is news content" + i));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random rand = new Random(System.currentTimeMillis());
        int length = rand.nextInt(20) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < length ; i++) {
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTextView.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPane) {
                        // 双页模式
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().
                                findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    } else {
                        // 单页模式
                        NewsContentActivity.actionStart(getContext(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTextView;

            public ViewHolder(View view) {
                super(view);
                newsTextView = (TextView) view.findViewById(R.id.news_title);
            }
        }
    }
}
