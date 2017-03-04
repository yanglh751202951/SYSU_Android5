# SYSU_Android5
我的中山大学手机平台应用开发2016课程项目代码

此次项目中，主要掌握了静态广播、动态广播两种改变 widget 内容的方法，即掌握 AppWidget 编程基础、掌握 Broadcast 编程基础、掌握动态注册 Broadcast 和静态注册 Broadcast的方式。

Widget 是在桌面上的一块显示信息的东西，也通过单击 Widget 跳转到一个程序里面。而系统自带的程序，典型的 Widget 是 music，这个 Android 内置的音乐播放小程序。这个是典型的 Widget+app 应用。就是一个程序既可以通过Widget 启动，也可以通过 App 启动。Widget 就是一个 AppWidgetProvider+一个UI 界面显示（预先绑定了好多 Intent），界面上的信息可以通过程序控制而改变，单击 Widget，上的控件只能激发发送一个 Intent，或发出一个 Service 的启动通知。而 AppWidgetProvider 可以拦截这个 Intent，而进行相应的处理（比如显示新的信息）。

具体实现详细过程及实现内容的相关知识见个人博客：http://www.cnblogs.com/yanglh6-jyx/p/Android_Widget.html
