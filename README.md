# DailyDevelopment
This repository is used for my daily learn and keep some useful code in order to use latest.

## 1.使用反射机制实现ButterKnife的效果

&emsp;&emsp;通过反射的方式，实现了@OnClick,@ContentView,@ViewInject,三个注解，并使用ViewInjectUtil进行注解的解析，实现类似ButterKnife的相关效果。

## 8.Activity的各个LaunchMode测试

&emsp;&emsp;通过指定android:launchMode属性，可以为standard,singleTop,singleTask,singleInstance四种属性。

> * standard：每次启动一个新的Activity，就会创建一个新的实例，并入栈，处于栈顶的位置；
> * singleTop：在启动Activity时如果发现栈顶的Activity已经是需要启动的Activity，则直接使用它；
> * singleTask：每次启动Activity时系统首先检查在任务栈中是否存在该Activity实例，如果存在则直接使用该实例，并把该Activity之上的所有Activity统统出栈；
> * singleInstance：会启动一个新的任务栈来管理该Activity（实际上如果**singleTask指定不同的taskAffinity，也会启动一个新的任务栈**）。