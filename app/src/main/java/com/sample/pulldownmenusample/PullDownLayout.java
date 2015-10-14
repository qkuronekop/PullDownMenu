package com.sample.pulldownmenusample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanakasatomi on 2015/10/14.
 */
public class PullDownLayout extends LinearLayout {

  public interface PullDownLayoutListener {
    void onClickMenu(View view);
  }

  private PullDownLayoutListener listener;

  private List<String> menuNames;
  private View view;
  private Context context;
  private int width;
  private int height;
  private LinearLayout layout;
  private RelativeLayout relativeLayout;
  private int x;
  private int y;
  private int position;

  public PullDownLayout(Context context, AttributeSet attrs, PullDownLayoutListener listener) {
    super(context, attrs);
    this.context = context;
    menuNames = new ArrayList<String>();
    this.listener = listener;
  }

  /**
   * メニュー名を追加
   */
  public void setMenu(final String menuName) {
    menuNames.add(menuName);
  }

  /**
   * タッチする対象のViewを設定する
   */
  public void setTouchView(View view) {
    this.view = view;
  }

  /**
   * レイアウトの幅を設定
   */
  public void setWidth(final int width) {
    this.width = width;
  }

  /**
   * レイアウトの高さを設定
   */
  public void setHeight(final int height) {
    this.height = height;
  }

  public void setPosition(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * 表示する
   *
   * @param parentView 表示対象のRelativeLayout
   */
  public View show(RelativeLayout parentView) {
    if (layout != null) {
      relativeLayout.removeView(layout);
    }
    relativeLayout = parentView;
    layout = createView();
    relativeLayout.addView(layout);
    Animation animation = AnimationUtils.loadAnimation(context, R.anim.display_menu_anim);
    layout.startAnimation(animation);
    return layout;
  }

  /**
   * 削除する
   */
  public void delete() {
    relativeLayout.removeView(layout);
    layout = null;
    position = 0;
  }

  private LinearLayout createView() {
    LinearLayout layout = new LinearLayout(context);
    LinearLayout.LayoutParams params =
        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    layout.setLayoutParams(params);
    layout.setOrientation(LinearLayout.VERTICAL);
    for (String name : menuNames) {
      layout.addView(createTextView(name));
    }
    int location[] = new int[2];
    view.getLocationInWindow(location);
    layout.setX(location[0] - width - x);
    layout.setY(location[1] - height - y);
    return layout;
  }

  private TextView createTextView(final String menuName) {
    TextView textView = new TextView(context);
    textView.setBackground(getResources().getDrawable(R.drawable.menu_selector));
    textView.setGravity(Gravity.CENTER);
    RelativeLayout.LayoutParams params =
        new RelativeLayout.LayoutParams(width == 0 ? LayoutParams.WRAP_CONTENT : width,
            height == 0 ? LayoutParams.WRAP_CONTENT : height);
    textView.setLayoutParams(params);
    textView.setText(menuName);
    textView.setTag(position);
    textView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        listener.onClickMenu(v);
      }
    });
    position++;
    return textView;
  }
}
