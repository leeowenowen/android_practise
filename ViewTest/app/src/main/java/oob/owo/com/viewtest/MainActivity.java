package oob.owo.com.viewtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
只有removeView和addView的时候才会调用attach和detach, 设置可见性是不能调用相关函数的。
 */
public class MainActivity extends AppCompatActivity {

  private class TestView extends TextView {
    public TestView(Context context) {
      super(context);
      setText("I'm Test View !");
      setBackgroundColor(Color.RED);
      setTextColor(Color.YELLOW);
      getRootView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
        @Override
        public void onSystemUiVisibilityChange(int visibility) {
          if()
        }
      });
    }

    @Override
    protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      Log.e("xxx", "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      Log.e("xxx", "onDetachedFromWindow");
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Button btnAdd = new Button(this);
    btnAdd.setText("add");
    ;
    Button btnRemove = new Button(this);
    btnRemove.setText("remove");
    ;
    Button btnHide = new Button(this);
    btnHide.setText("hide");
    ;
    Button btnShow = new Button(this);
    btnShow.setText("show");
    ;

    final TestView testView = new TestView(this);
    final LinearLayout contentView = new LinearLayout(this);
    {
      contentView.setOrientation(LinearLayout.VERTICAL);
      contentView.addView(btnAdd);
      contentView.addView(btnRemove);
      contentView.addView(btnShow);
      contentView.addView(btnHide);
    }
    setContentView(contentView);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        contentView.addView(testView);
      }
    });
    btnRemove.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        contentView.removeView(testView);
      }
    });
    btnShow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        testView.setVisibility(View.VISIBLE);
      }
    });
    btnHide.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        testView.setVisibility(View.GONE);
      }
    });
  }
}
