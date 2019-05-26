package com.mjq.zoompitcure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.aidl.R;

public class ZoomingActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.image)
    protected ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zooming);
        butterknife.ButterKnife.bind(this);
        /**android加载大图片
         1、计算图片的宽和高
         2、计算屏幕的宽和高
         3、计算图片和屏幕的宽高比例
         4、按照计算出来的比例取宽高比例的最大值，显示图片
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bigpicrure);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i("ZoomingActivity ", "图片的宽" + width + "" + "图片的高" + height + "");

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        int windowHeight = windowManager.getDefaultDisplay().getHeight();
        int windowWidth = windowManager.getDefaultDisplay().getWidth();

        float resultScale = 0;
        float scaleH = width / windowHeight;
        float scaleW = width / windowWidth;
        if (scaleH > scaleW) {
            resultScale = scaleH;
        } else if (scaleH < scaleW){
            resultScale = scaleW;
        }else{
            resultScale = scaleH;
        }
        int newWidth = (int) (width / resultScale);
        int newHight = (int) (height / resultScale);
        Log.i("ZoomingActivity ", "修改后图片的宽~~~~~" + newWidth + "" + "修改后图片的高~~~~~" + newHight + "");

        //创建新的bitmap
        Bitmap newbitmap = imageScale(bitmap, width,height );
        //Bitmap newbitmap  =  Bitmap.createBitmap(newWidth, newHight, bitmap.getConfig());
        image.setImageBitmap(newbitmap);


    }

    /**
     * 调整图片大小
     *
     * @param bitmap
     *            源
     * @param dst_w
     *            输出宽度
     * @param dst_h
     *            输出高度
     * @return
     */
    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
        return dstbmp;
    }

}
