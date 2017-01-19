package cfb.com.dailydevelopment.example9.bestactivity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理Activity的工具类
 * Created by caofengbin on 2017/1/19.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAllActivity() {
        for(Activity activity : activities) {
            if(!activity.isFinishing())
                activity.finish();
        }
    }
}
