# XScrollItemLayout
这是一个可以左滑或者右滑的控件，可用于listview的item中，例如邮件app会需要左滑或者右滑item进行atchive等操作<br /> 
使用方法：<br />  maven { url "https://jitpack.io" }<br /> 
compile 'com.github.FatWhite:XScrollItemLayout:1.0'<br /> 
``iLayout= (XItemScrollLayout) findViewById(R.id.itemLayout);``<br /> 
`` View viewL= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_left,null);``<br /> 
``View viewR= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_right,null);``<br /> 
``iLayout.addLeftView(viewL);``<br /> 
``iLayout.addRightView(viewR);``<br /> 
<br /> 
`` <TextView <br /> 
          android:layout_width="match_parent"
          android:layout_height="90dp"
          android:text="I'm Item"
          android:gravity="center"
          android:background="#cfccfc"
          android:tag="scrollitem"/>``
          <br /> <br /> 
注意在滑动item的布局中增加android:tag="scrollitem"<br /> 
![](https://github.com/FatWhite/XScrollItemLayout/blob/master/1.png)
![](https://github.com/FatWhite/XScrollItemLayout/blob/master/2.png)
