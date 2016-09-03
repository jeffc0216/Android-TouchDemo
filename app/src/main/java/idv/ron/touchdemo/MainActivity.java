package idv.ron.touchdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
    }

    private void findViews() {
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        class MyListener implements View.OnTouchListener {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        }

        MyListener listener = new MyListener();
        relativeLayout.setOnTouchListener(listener);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("first pointer: (%.1f,%.1f), ",
                        event.getX(), event.getY()));
                sb.append("touch state: ");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        sb.append("ACTION_DOWN\n");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sb.append("ACTION_MOVE\n");
                        break;
                    case MotionEvent.ACTION_UP:
                        sb.append("ACTION_UP\n");
                        break;
                    default:
                        sb.append("\n");
                        break;
                }

                int pointerCount = event.getPointerCount();
                sb.append(String.format("pointer count: %d %n", pointerCount));
                for (int i = 0; i < pointerCount; i++) {
                    sb.append(String.format("pointer %d: (%.1f,%.1f) %n",
                            event.getPointerId(i), event.getX(i), event.getY(i)));
                }
                tvMessage.setText(sb);
                return true;
            }
        });
    }
}
