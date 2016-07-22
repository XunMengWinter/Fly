# Fly
A set of simple animation. / 5个简单动效。

### Document
[动起来－几个Android常用动效](http://www.jianshu.com/p/c5cfa22834e0)

### 要点
* 文字滚动。
* 视图自带的动画。
* 抛物位移。
* 按压水面波纹效果。
* 共享元素动画。

### 效果图

[Fly (点击查看)](https://github.com/XunMengWinter/Fly)

一如既往的霍尔首页^^：
![fly.gif](http://upload-images.jianshu.io/upload_images/2066824-4e48afd6def85cac.gif?imageMogr2/auto-orient/strip)

### 要点讲解
* 文字滚动：
把下面这段代码放入Layout即可。
```
<TextView    
android:layout_width="match_parent"    
android:layout_height="wrap_content"    
android:ellipsize="marquee"    
android:focusable="true"    
android:focusableInTouchMode="true"    
android:marqueeRepeatLimit="marquee_forever"    
android:padding="@dimen/text_margin"    
android:scrollHorizontally="true"    
android:singleLine="true"    
android:text="滚动的 TextView ^ ^    
abcdefghijklmnopqrstuvwxyz    1234567890    
one two three four five six seven eight nine ten    "/>
```
* 使用View自带动画：
下面这段代码，如果hasShowingAnimation，那么将mSelfAnimationTv复原，否则将mSelfAnimationTv顺时针转90度、透明度渐变为0.5、下移300像素、放大1.3倍。
```
boolean hasShowingAnimation;
/*View 自身动画*/
private void showSelfAnimation() {
    if (hasShowingAnimation)
        mSelfAnimationTv.animate()
                .rotation(0)
                .alpha(1f)
                .translationY(0)
                .scaleX(1f)
                .scaleY(1f);
    else
        mSelfAnimationTv.animate()
                .rotation(90)
                .alpha(0.5f)
                .translationY(300)
                .scaleX(1.3f)
                .scaleY(1.3f);
    hasShowingAnimation = !hasShowingAnimation;
}
```
* 抛物位移：
[请看源码](https://github.com/XunMengWinter/Fly/blob/master/app/src/main/java/top/wefor/fly/ParabolaActivity.java)
* 按压水面波纹效果：
非常简单，在res-drawable文件夹添加一个bg_btn.xml文件，代码如下，然后将其作为Button、TextView等可点击控件的背景即可。
```
<?xml version="1.0" encoding="utf-8"?>
<ripple xmlns:android="http://schemas.android.com/apk/res/android"
        android:color="#dfa">
    <item>
        <selector>
            <item
                android:drawable="@android:color/transparent"
                android:state_pressed="true">
            </item>
            <item android:drawable="@android:color/white"/>
        </selector>
    </item>
</ripple>
```
* 共享元素动画
[查看源码](https://github.com/XunMengWinter/Fly/blob/master/app/src/main/java/top/wefor/fly/SharedElementsActivity.java)
要点请看源码，实现起来很简单、很神奇，关键是这个方法ActivityOptionsCompat.makeSceneTransitionAnimation()。
使用该Transition需要注意如下几点：
1.转场两边的控件内容需一致(比如图片文字需相同);
2.如果图片通过网络加载，请保证图片加载库一致，另外使用[Fresco](https://github.com/facebook/fresco)加载图片会有点问题，这里有[解决方案](https://github.com/facebook/fresco/issues/22)(但是我没能看懂，所以我又compile了[Picasso](https://github.com/square/picasso))。

### 相关
本文Demo: https://github.com/XunMengWinter/Fly
Material Design初露锋芒: http://www.jianshu.com/p/e64a4e08f57a
