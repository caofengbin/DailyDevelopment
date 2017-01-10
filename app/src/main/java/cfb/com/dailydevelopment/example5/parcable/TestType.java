package cfb.com.dailydevelopment.example5.parcable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/10.
 */

public class TestType implements Serializable {
    public String key;
    public String value;

    public TestType(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
