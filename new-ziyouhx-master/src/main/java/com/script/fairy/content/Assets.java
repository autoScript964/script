package com.script.fairy.content;

import android.util.LruCache;


import com.script.opencvapi.utils.AtOpencvUtils;
import com.script.opencvapi.utils.TemplateInfo;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by daiepngfei on 2020-07-22
 */
public class Assets {

    private AssetsCacher mAssetsCacher = new AssetsCacher(1);
    private AtOpencvUtils mOpencvUtils = new AtOpencvUtils();
    private ConcurrentHashMap<String, Entity> mAssetBeans = new ConcurrentHashMap<>();


    Assets() {
    }

    /*private Bitmap getImageFromAssetsFile(String fileName) {
        Bitmap image = null;
        AssetManager am = mFairy.mContext.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }*/

    public Entity entity(String assetsName) {
        if (!mAssetBeans.containsKey(assetsName)) {
            Entity bean = new Entity();
            bean.setInfo(mOpencvUtils.getTemplateInfo(assetsName));
            bean.setMat(getEntityMat(assetsName));
            if (bean.isValid()) {
                mAssetBeans.put(assetsName, bean);
            }
        }
        return mAssetBeans.get(assetsName);
    }

    public Mat mat(String assetsName) {
        return mat(assetsName, null);
    }

    public Mat mat(String assetsName, MatOpt opt) {
        Entity entity = entity(assetsName);
        if (entity != null) {
            if (opt == null || opt.method == MatOpt.Method.DEFAULT) {
                return entity.mat;
            } else {
                final String optKey = assetsName + opt.asKey();
                if (!mAssetBeans.containsKey(optKey)) {
                    Entity bean = new Entity();
                    bean.setInfo(entity.info);
                    try {
                        bean.setMat(MatFactory.createCvtMat(entity.mat, opt));
                        mAssetBeans.put(optKey, bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return mAssetBeans.get(optKey).mat;
            }
        }
        return null;
    }


    public TemplateInfo info(String assetsName) {
        Entity entity = entity(assetsName);
        if (entity != null) {
            return entity.info;
        }
        return null;
    }


    private Mat getEntityMat(String assetsName) {
        Mat mat = mAssetsCacher.get(assetsName);
        if (mat == null || mat.empty()) {
            mAssetsCacher.remove(assetsName);
            mat = mOpencvUtils.getTemplateMat(assetsName);
            if (mat != null && !mat.empty()) {
                if(mat.channels() == 4) {
                    Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGRA2BGR);
                }
                mAssetsCacher.put(assetsName, mat);
            }
        }
        return mat;
    }


    /**
     * Created by daiepngfei on 2020-07-22
     */
    public static class Entity {
        private TemplateInfo info;
        private Mat mat;

        public boolean isValid() {
            return mat != null && !mat.empty() && info != null;
        }

        public TemplateInfo getInfo() {
            return info;
        }

        public void setInfo(TemplateInfo info) {
            this.info = info;
        }

        public Mat getMat() {
            return mat;
        }

        public void setMat(Mat mat) {
            this.mat = mat;
        }
    }

    /**
     * Created by daiepngfei on 2020-07-22
     */
    private static class AssetsCacher extends LruCache<String, Mat> {

        private int mMaxMemory;

        /**
         * @param maxSize
         *         for caches that do not override {@link #sizeOf}, this is the maximum number of
         *         entries in the cache. For all other caches, this is the maximum sum of the sizes
         *         of the entries in this cache.
         */
        private AssetsCacher(int maxSize) {
            super(maxSize);
            resizeWithCache();
        }

        @Override
        protected int sizeOf(String key, Mat value) {
            return value.rows() * value.cols() / 1024;
        }

        private void resizeWithCache() {
            final int maxMemory = (int) (Runtime.getRuntime().totalMemory() / 1024);
            if (Math.abs(maxMemory - mMaxMemory) > 10240) {
                mMaxMemory = maxMemory;
                final int cacheSize = maxMemory / 8;
                resize(cacheSize);
                System.out.println("AssetsCacher#: maxMemory -> " + maxMemory + ", cacheSize -> " + cacheSize);
            }
        }
    }
}
