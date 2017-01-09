package cfb.com.dailydevelopment.example4.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cfb.com.dailydevelopment.R;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AnotherFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.anthor_right_fragment,container,false);
        return view;
    }
}
