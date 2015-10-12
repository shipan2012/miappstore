package example.xfsp.miappstore.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;
import android.widget.TextView;

import example.xfsp.miappstore.R;
import example.xfsp.miappstore.bean.AppDetail;
import example.xfsp.miappstore.utils.UiUtils;

/**
 * Created by Administrator on 2015/9/29.
 */
public class AppIntroHolder extends BaseHolder<AppDetail> {

    private TextView tv_content;
    private View fl_more;
    private ScrollView scrollView;

    @Override
    public View initView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.holder_intro, null);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        fl_more = view.findViewById(R.id.fl_more);
        return view;
    }

    @Override
    public void refreshView(AppDetail appDetail, String hostUrl, int position) {
        tv_content.setText(appDetail.getIntroduction());
        fl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击之后，展开
                expand();
            }
        });

        //tv_content设置起始高度
        ViewGroup.LayoutParams layoutParams = tv_content.getLayoutParams();
        layoutParams.height = getShortMeasureHeight();
        //设置修改高度后的参数
        tv_content.setLayoutParams(layoutParams);
    }

    //展开剩余部分
    private void expand() {
        scrollView = getScrollView(tv_content);
        final ViewGroup.LayoutParams layoutParams = tv_content.getLayoutParams();
        int shortMeasureHeight = getShortMeasureHeight();
        final int longMeasureHeight = getLongMeasureHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(shortMeasureHeight, longMeasureHeight);
        //设置动画值变化监
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                layoutParams.height = animatedValue;
                tv_content.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(50);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束后，Scroll滑动到最下面
                scrollView.scrollTo(0, longMeasureHeight);
                fl_more.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    /**
     * 获取10行文本的高度
     * @return
     */
    public int getShortMeasureHeight() {
        TextView textView = new TextView(UiUtils.getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
        textView.setLines(10);//表示有十行数据
        //开始宽度
        int width = tv_content.getMeasuredWidth();
        //指定测量规则
        int measureWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measureHeight = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);
        //开始测量
        textView.measure(measureWidth, measureHeight);
        return textView.getMeasuredHeight();
    }

    public int getLongMeasureHeight(){
        //开始高度
        int width = tv_content.getMeasuredWidth();
        tv_content.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //指定测量规则
        int measureWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measureHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv_content.measure(measureWidth, measureHeight);
        int measuredHeight = tv_content.getMeasuredHeight();
        return measuredHeight;
    }

    //获取界面的ScrollerView
    public ScrollView getScrollView(View view){
        ViewParent parent = view.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup group = (ViewGroup) parent;
            if(group instanceof ScrollView){
                return (ScrollView) group;
            }else{
                return getScrollView(group);
            }
        }else{
            return null;
        }
    }
}
