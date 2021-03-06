# DailyDevelopment

该项目主要记录平时学习过程中的一些零碎知识点(2017年1月相关)，具体目录如下：

## 1.使用反射机制实现ButterKnife的效果

&emsp;&emsp;通过反射的方式，实现了@OnClick,@ContentView,@ViewInject,三个注解，并使用ViewInjectUtil进行注解的解析，实现类似ButterKnife的相关效果。

大致效果如下：

``` java
@ContentView(R.layout.activity_test_annoattion)
public class TestAnnotationActivity extends AppCompatActivity {

    @ViewInject(R.id.testButton)
    private Button mButton;

    @OnClick(R.id.testButton)
    private void onClick(View view) {
        mButton.setText("我是click后的文字内容");
        Toast.makeText(this, "按钮被点击了", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        mButton.setText("我是click前的Button内容");
    }
}
```

详细实现细节主要看ViewInjectUtils类文件中的inject方法。


## 2.利用apt技术实现ButterKnife的效果

实现技术主要借鉴文章->[ANNOTATION PROCESSING](http://hannesdorfmann.com/annotation-processing/annotationprocessing101)中的内容，原作者基于apt技术实现了一个实现工厂模式的注解，内容很详细，是学习apt技术很好的资料。
前两个tips给单独的抽取到了项目[分别基于反射和apt技术实现模拟ButterKnife的效果](https://github.com/caofengbin/SimulationButterKnife)中。

## 3.Activity生命周期测试

本节内容主要基于《Android开发艺术探索》第一章的内容进行，内容虽然简单，但是在日常开发中很关键，需要定期的review其中的内容。

[原图的链接](https://github.com/xxv/android-lifecycle)

<center>
![一个很好的生命周期示意图](https://raw.githubusercontent.com/xxv/android-lifecycle/master/complete_android_fragment_lifecycle.png)
</center>

## 4.Fragment生命周期测试

本部分内容主要基于《第一行代码》一书中第四章相关内容的学习，感觉比较重要的知识点包括：

> * **FramentManager相关的一系列流程的整体使用**，在很多场景都用的到；
> * FragmentManager.addToBackStack这一方法模拟返回栈的效果；
> * 4.2.4节，**Fragment与Activity之间的通信方式**，很重要；
> * 限定符相关的技术；

## 5.Serializable与Parcelable的区别

演示两个Activity之间传递自定义对象的两种方式，一种是通过Serializable，一种是通过Parcelable。
接收的方法依次是：

``` java

// 获取序列化对象类型
Person tempPerson = (Person) getIntent().getSerializableExtra("serializableObject");
// 获取Parcelable对象的类型
Person2 tempPerson2 = getIntent().getParcelableExtra("ParcelableObject");

```

**实现Parcelable接口的一个比较标准的方式**可以参见如下代码：

``` java

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

```

## 6.一个简易版的新闻应用

&emsp;&emsp;该部分的内容主要基于《第一行代码》一书中的2.4.5节内容--“Fragment的最佳实践，一个简易版的新闻应用”为蓝本进行实现的。实现了一个同一套代码支持手机和平板的不同UI显示的目的。主要的几个技术实现细节包括：

> * 使用最小宽度限定符技术--创建一个layout-sw600dp的文件夹，为主Activity写了两个不同的xml布局文件，但同名均为activity_main，前者只有一个Framgent，后者xml文件上引用了两个Fragment；
> * 设定isTwoPane变量来标示是单页模式还是双页模式，具体的区分通过判断主Activity加载的那个activity_main文件来判断，而后根据isTwoPane的不同设置不同的监听跳转，实现手机与平板的兼容。

本书4.4.1节有关**限定符**的使用实现动态加载布局的效果值得学习回味。

## 7.TextChangerListener的简单使用

主要作用就是用于验证TextView中输入的内容是否合法，做参数校验。本demo中给的不全，实际使用的时候再根据文档来看，感觉是很实用的功能。

## 8.Activity的各个LaunchMode测试

&emsp;&emsp;通过指定android:launchMode属性，可以为standard,singleTop,singleTask,singleInstance四种属性。

> * standard：每次启动一个新的Activity，就会创建一个新的实例，并入栈，处于栈顶的位置；
> * singleTop：在启动Activity时如果发现栈顶的Activity已经是需要启动的Activity，则直接使用它；
> * singleTask：每次启动Activity时系统首先检查在任务栈中是否存在该Activity实例，如果存在则直接使用该实例，并把该Activity之上的所有Activity统统出栈；
> * singleInstance：会启动一个新的任务栈来管理该Activity（实际上如果**singleTask指定不同的taskAffinity，也会启动一个新的任务栈**）。

比较有用的几个结论：

> * (1)taskAffinity必须和lanuchMode为singleTask的结合起来使用，**其他launchMode下指定taskAffinity是没有任何意义的**。
> * (2)四个模式分为两大类，“standard”和“singleTop”为一类，“singleTask”和“singleInstance”为另一类。
> * (3)“singleTask”和“singleInstance”模式只在一个方面有差异： “singleTask”Activity 允许其他 Activity 成为其任务的组成部分。 它始终位于其任务的根位置，但其他 Activity（必然是“standard”和“singleTop”Activity）可以启动到该任务中。 相反，“singleInstance”Activity 则不允许其他 Activity 成为其任务的组成部分。它是任务中唯一的 Activity。 如果它启动另一个 Activity，系统会将该 Activity 分配给其他任务 — 就好像 Intent 中包含 FLAG_ACTIVITY_NEW_TASK 一样。

关于launchMode使用的一个详细博客可以参见这篇，非常的详细：[Android中Activity四种启动模式和taskAffinity属性详解](http://blog.csdn.net/zhangjg_blog/article/details/10923643)

## 9.Activity的最佳实践

郭霖《第二行代码》一书中的Activigy一章最后的小结，三个tips感觉都很赞，

> * 构造一个BaseActivity，用于管理整个项目中的Activity，实际开发中用处很大，有很多的发挥空间；
> * 启动Activity的最佳实践；

``` java
	public static void actionStart(Context context,String data1,String data2) {
        Intent intent = new Intent(context,ThirdActivity.class);
        intent.putExtra("parameter1",data1);
        intent.putExtra("parameter2",data2);
        context.startActivity(intent);
    }
```

给待启动的Activity中增加一个静态方法，用于启动该Activity的外界调用。