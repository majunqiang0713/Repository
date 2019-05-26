package com.mjq.drawboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.aidl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawingBoardActivity extends AppCompatActivity {


    @BindView(R.id.iv_huahua)
    ImageView ivHuahua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_board);
        ButterKnife.bind(this);

        /**
         * 实现android画画板
         * 1、由于原图不能修改，需要创建一张原图的副本
         * 2、在副本上修改原图
         *
         */
        Bitmap srcbitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.huahua);

        //创建原图的副本
        final Bitmap copybitmap = Bitmap.createBitmap(srcbitmap.getWidth(), srcbitmap.getHeight(), srcbitmap.getConfig());
        //ivHuahua.setImageBitmap(copybitmap);

        final Paint paint = new Paint();

        final Canvas canvas = new Canvas(copybitmap);
        Matrix matrix = new Matrix();
        canvas.drawBitmap(srcbitmap, matrix, paint);
        ivHuahua.setImageBitmap(copybitmap);
       // paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        ivHuahua.setOnTouchListener(new View.OnTouchListener() {
            private int mStopX;
            private int mStopY;
            int startX;
            int startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        mStopX = (int) event.getX();
                        mStopY = (int) event.getY();
                        canvas.drawLine(startX, startY, mStopX, mStopY, paint);
                        startX = mStopX;
                        startY = mStopY;
                        ivHuahua.setImageBitmap(copybitmap);
                        //更新开始的坐标

                        break;
                }
                return true;
            }
        });


    }
}
