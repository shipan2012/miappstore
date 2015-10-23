##高仿大米应用市场
	一款高仿MIUI V5版本手机应用市场
	- 功能包括：
		- App列表的显示
		- app详情页
		- App下载，自动安装
		- 多线程断点下载
		- 通知栏显示
	- 主要技术点：
		- 工厂设计模式FragmentFractory
		- 线程池的回收使用
		- 三级缓存的使用（网络、本地、内存）
		- ListView优化，代码的抽取
		- 多线程多任务下载
		- 观察者模式监听下载任务的进度变化
		- 自定义控件的使用（感谢鸿洋的大神的博客）
		- 其他MaterialDesign控件的使用（Toolbar,DrawerLayout等等）
		
--------
##Simple English Version Introducation
- The main function:
	- Displays the app detail infos
	- Download apk&auto_install
	- Multithread download,Resume broken download
	- The Notifycation informs
- The Main skills:
	- Factory Method
	- ThreadPool 、Recycle and Resuse
	- Three level cache uses(net、local、ram)
	- ListView optimalize 
	- Observer Method
	- Multitask download
	
- 说明：
	- "高仿大米应用商店"是我的第一个开源项目，旨在在写代码的过程中学习知识，积累错误。目前V1.0实现了下载显示基本功能，以后会继续优化，
	- 待优化的功能，比如：下载记录的持久化，每个页面的APP显示.
	- 由于个人学艺不精，代码中难免会有bug，欢迎大家指出，互相学习进步，我的微博:@sp不会玩ps
	- 之前没有上传完整，现在补充完整，欢迎fork.2015/10/23



##简单演示
![演示](http://i.imgur.com/ZpYk48Q.gif)


##App截图:

- 主界面:

![主界面](http://i.imgur.com/kDYkAFt.png)

- 左侧菜单界面:

![左侧滑动菜单](http://i.imgur.com/Bbs6iVm.png)

- 下载界面:

![下载界面](http://i.imgur.com/Hz5bCzw.png)

- APP详情界面:

![APP详情界面](http://i.imgur.com/W04ywAs.png)

- 通知栏界面

![通知栏](http://i.imgur.com/ZhCZMoN.png)

- 下载中心界面:

![下载中心界面](http://i.imgur.com/iOwoBZJ.png)

##关于我
* **Email:**xfshipan@gmail.com
* **weibo:**sp不会玩ps

##License

This project is available under the MIT license.