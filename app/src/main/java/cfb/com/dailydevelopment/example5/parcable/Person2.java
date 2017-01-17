package cfb.com.dailydevelopment.example5.parcable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/1/17.
 */

public class Person2 implements Parcelable {

    private String name;
    private int age;

    public Person2() {

    }

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        // 该方法直接返回0
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public static final Parcelable.Creator<Person2> CREATOR
            = new Parcelable.Creator<Person2>() {

        @Override
        public Person2 createFromParcel(Parcel in) {
            Person2 person = new Person2();
            person.name = in.readString();
            person.age = in.readInt();
            return  person;
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };
}
