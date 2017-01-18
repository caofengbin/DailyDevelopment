# DailyDevelopment
This repository is used for my daily learn and keep some useful code in order to use latest.

## 1.使用反射机制实现ButterKnife的效果

&emsp;&emsp;通过反射的方式，实现了@OnClick,@ContentView,@ViewInject,三个注解，并使用ViewInjectUtil进行注解的解析，实现类似ButterKnife的相关效果。

## 2.利用apt技术实现ButterKnife的效果

## 3.Activity生命周期测试

## 4.Fragment生命周期测试

## 5.Serializable与Parcelable的区别

## 6.一个简易版的新闻应用

## 7.TextChangerListener的简单使用

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