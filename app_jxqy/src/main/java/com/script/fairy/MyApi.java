package com.script.fairy;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.opencvapi.AtFairy2;

import java.nio.ByteBuffer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.hardware.display.VirtualDisplay;
import android.hardware.display.VirtualDisplay.Callback;
import android.media.Image;
import android.media.ImageReader;
import android.media.Image.Plane;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.script.opencvapi.LtLog;
import com.script.opencvapi.ScreenInfo;
import com.script.opencvapi.AtFairy2.RawScreenInfo;
import java.nio.ByteBuffer;

public class MyApi {
    private Context mContext;
    private Intent mIntentResult;
    private int mWindowWidth = 0;
    private int mWindowHeight = 0;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mScreenDensity = 0;
    private ImageReader mImageReader = null;
    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection = null;
    private VirtualDisplay mVirtualDisplay = null;
    private boolean mEnableCapture = true;

    public MyApi(Context context, Intent resultIntent) {
        this.mContext = context;
        this.mIntentResult = resultIntent;
        this.createVirtual();
    }

    private void createVirtual() {
        WindowManager windowManager = (WindowManager)this.mContext.getSystemService(mContext.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(metrics);
        this.mScreenDensity = metrics.densityDpi;
        this.mWindowWidth = metrics.widthPixels;
        this.mWindowHeight = metrics.heightPixels;
        this.mScreenWidth = this.mWindowWidth;
        this.mScreenHeight = this.mWindowHeight;
        this.mImageReader = ImageReader.newInstance(this.mWindowWidth, this.mWindowHeight, 1, 3);
        this.setUpMediaProjection();
        this.virtualDisplay();
    }

    private void setUpMediaProjection() {
        this.mMediaProjectionManager = (MediaProjectionManager)this.mContext.getSystemService(mContext.MEDIA_PROJECTION_SERVICE);
        if(this.mMediaProjection == null) {
            this.mMediaProjection = this.mMediaProjectionManager.getMediaProjection(-1, this.mIntentResult);
        }

    }

    private void virtualDisplay() {
        this.mVirtualDisplay = this.mMediaProjection.createVirtualDisplay("screen-mirror", this.mWindowWidth, this.mWindowHeight, this.mScreenDensity, 16, this.mImageReader.getSurface(), (VirtualDisplay.Callback)null, (Handler)null);
    }

    private void recreate() {
        this.mVirtualDisplay.release();
        this.mImageReader.close();
        this.createVirtual();
    }

    public ScreenInfo captureRawScreen(ScreenInfo screenInfo) {
        Image image = this.mImageReader.acquireLatestImage();
        if(image == null) {
            return null;
        } else {
            int width = image.getWidth();
            int height = image.getHeight();
            if(width != screenInfo.width || height != screenInfo.height) {
                LtLog.i("width:" + width + " screeninfo width:" + screenInfo.width);
                this.recreate();
                this.captureRawScreen(screenInfo);
            }
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            width = rowStride / pixelStride;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            int bytecount = bitmap.getByteCount();
            ByteBuffer rawBuffer = ByteBuffer.allocate(bytecount);
            bitmap.copyPixelsToBuffer(rawBuffer);
            bitmap.recycle();
            screenInfo.raw = rawBuffer.array();
            screenInfo.width = width;
            screenInfo.height = height;
            image.close();
            return screenInfo;
        }
    }

    public AtFairy2.RawScreenInfo captureRaw(AtFairy2.RawScreenInfo screenInfo) {
        Image image = this.mImageReader.acquireLatestImage();
        if(image == null) {
            return null;
        } else {
            int width = image.getWidth();
            int height = image.getHeight();
            if(width != screenInfo.width || height != screenInfo.height) {
                LtLog.i("width:" + width + " screeninfo width:" + screenInfo.width);
                this.recreate();
                this.captureRaw(screenInfo);
            }
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            width = rowStride / pixelStride;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            int bytecount = bitmap.getByteCount();
            ByteBuffer rawBuffer = ByteBuffer.allocate(bytecount);
            bitmap.copyPixelsToBuffer(rawBuffer);
            bitmap.recycle();
            screenInfo.raw = rawBuffer.array();
            screenInfo.width = width;
            screenInfo.height = height;
            return screenInfo;
        }
    }
}
