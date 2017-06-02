package jm.org.itemlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 独立滑动item
 * Created by jimbai/FatWhite on 2017/6/1.
 */

public class XItemScrollLayout extends RelativeLayout {

    private float lastX=0f;//上次屏幕上到X距离

    private float lastY=0f;

    private LinearLayout leftViewGroup;//左侧菜单

    private LinearLayout rightViewGroup;//右侧菜单

    private View scrollViewItem;//中间滑动布局

    private String SCROLLITEM_TAG="scrollitem";//滑动布局tag

    private float X1,X2;//判断滑动方向

    private float width=0f;//滑动布局宽度


    public XItemScrollLayout(Context context) {
        super(context);
        initView(context);
    }

    public XItemScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XItemScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //初始化左右选项栏
    private void initView(Context  ctx){
        leftViewGroup=new LinearLayout(ctx);
        LayoutParams paramsLeft=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftViewGroup.setLayoutParams(paramsLeft);

        rightViewGroup=new LinearLayout(ctx);
        LayoutParams paramsRight=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        paramsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightViewGroup.setLayoutParams(paramsRight);
        addView(leftViewGroup);
        addView(rightViewGroup);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        width=getWidth();
        if (scrollViewItem == null ){
            scrollViewItem=findViewWithTag(SCROLLITEM_TAG);
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X1=event.getX();
                lastX=event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                X2=event.getX();//右侧滑动为正数差
                onMoveEvent(scrollViewItem,event,(X2-X1) > 0);
                X1=X2;
                break;
            case MotionEvent.ACTION_UP:
                if (scrollViewItem.getLeft() > 20){//此时应该显示的是左侧菜单
                     if (scrollViewItem.getLeft() >leftViewGroup.getWidth() /2){//显示出来
                         scrollViewItem.layout(leftViewGroup.getWidth(),scrollViewItem.getTop(),
                                 (int) (leftViewGroup.getWidth()+width),scrollViewItem.getBottom());
                     }
                    else {//隐藏
                         scrollViewItem.layout(0,scrollViewItem.getTop(), (int) width,scrollViewItem.getBottom());
                     }
                }
                if (scrollViewItem.getRight() <width-20){//此时应该显示的是右侧侧菜单
                    if (scrollViewItem.getRight() < width -rightViewGroup.getWidth()/2){//显示出来
                        scrollViewItem.layout(-rightViewGroup.getWidth(),scrollViewItem.getTop(),
                                (int) (width-rightViewGroup.getWidth()),scrollViewItem.getBottom());
                    }else{//隐藏
                        scrollViewItem.layout(0,scrollViewItem.getTop(), (int) width,scrollViewItem.getBottom());
                    }

                }

                break;
        }
        return true;
    }

    //滑动时处理函数
    private void onMoveEvent(View v,MotionEvent event,boolean isRightScroll){
        int x= (int) (event.getRawX()-lastX);
        int top =v.getTop();
        int bottom =v.getBottom();
        int left =v.getLeft() +x;
        int right =v.getRight() +x;
        if (isRightScroll){
            if (left > leftViewGroup.getWidth()){
                left=leftViewGroup.getWidth();
                right = (int) (left+width);
            }
        }else{
            if (right <width -rightViewGroup.getWidth()){
                right= (int) (width -rightViewGroup.getWidth());
                left=-rightViewGroup.getWidth();
            }
        }
//        Log.e("lastX",event.getRawX()+"------>");
        v.layout(left,top,right,bottom);
        lastX=event.getRawX();
    }

    //添加左侧菜单
    public void addLeftView(View leftView){
        leftViewGroup.addView(leftView);
    }

    //添加右侧菜单
    public void addRightView(View rightView){
        rightViewGroup.addView(rightView);
    }
}
