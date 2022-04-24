package com.example;

import android.graphics.Bitmap;
import android.util.Base64;

import com.example.publicfunctionlibrary.FunctionClass;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Answer {

    public AtFairyImpl mFairy;
    public FindResult result;

    public Answer(AtFairyImpl atFairy){
        mFairy = atFairy;

    }

    public String haoai(int x,int y,int width, int height,String ID ) throws Exception {
        Thread.sleep(500);

        String host, token1;
        String answerhui = null;

        Mat mat3, mat2;
        mat3 = mFairy.getScreenMat(0, 0, 1280, 720, 1, 0, 0, 1);

        //将图片存入路径
        //Mat转byte[]
        Imgcodecs.imwrite("/sdcard/screen.png", mat3);

        FunctionClass functionClass = new FunctionClass(mFairy, mFairy.getContext());

        token1 = functionClass.getToken();
        //开始截图
        mat2 = mFairy.getScreenMat(x, y, width, height, 1, 0, 0, 1);

        //将图片存入路径
        //Mat转byte[]
        Imgcodecs.imwrite("/sdcard/111.png", mat2);

        //这里获取好爱HOST
        LtLog.i(mFairy.getLineInfo("开始获取好爱的HOST"));
        try {
            host = getHtml("http://3.haoi23.net/svlist.html");
            if (host == null) {
                host = getHtml("http://3.haoi23.net/svlist.html");
            }
            String str = host;
            host = str.substring(3, 23);
            LtLog.i(mFairy.getLineInfo("host===" + host));
            //System.out.println(host);
            LtLog.i(mFairy.getLineInfo("获取完成"));
            LtLog.i(mFairy.getLineInfo("请求数据"));

            String a = String.valueOf((int) (1 + Math.random() * 9));
            String b = String.valueOf((int) (1 + Math.random() * 9));
            String c = String.valueOf((int) (1 + Math.random() * 9));
            String d = String.valueOf((int) (1 + Math.random() * 9));
            String e = String.valueOf((int) (1 + Math.random() * 9));
            String f = String.valueOf((int) (1 + Math.random() * 9));
            String g = String.valueOf((int) (1 + Math.random() * 9));
            String h = String.valueOf((int) (1 + Math.random() * 9));
            String ii = String.valueOf((int) (1 + Math.random() * 9));
            String j = String.valueOf((int) (1 + Math.random() * 9));
            String suiji = a + b + c + d + e + f + g + h + ii + j;
            LtLog.i(mFairy.getLineInfo("得到的随机数" + suiji));

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("userstr", "yunpai|ACMXGAHOAZNDCEED")
                    .addFormDataPart("gameid", ID)
                    .addFormDataPart("timeout", "60")
                    .addFormDataPart("rebate", "3739|6A1962CC9E02B5B9")
                    .addFormDataPart("daiLi", "haoi")
                    .addFormDataPart("kou", "0")
                    .addFormDataPart("beizhu", "2222")
                    .addFormDataPart("ver", "web2")
                    .addFormDataPart("key", suiji)
                    .addFormDataPart("img", GetImageStr("/sdcard/111.png"))
                    .build();
            Request request = new Request.Builder()
                    .url("http://" + host + "/UploadBase64.aspx")
                    .post(requestBody)
                    .build();
                Response response = client.newCall(request).execute();

            String fanhui = response.body().string();

            LtLog.i(mFairy.getLineInfo("请求完成,开始请求TID,TID为" + fanhui));
            a = String.valueOf((int) (1 + Math.random() * 9));
            b = String.valueOf((int) (1 + Math.random() * 9));
            c = String.valueOf((int) (1 + Math.random() * 9));
            d = String.valueOf((int) (1 + Math.random() * 9));
            e = String.valueOf((int) (1 + Math.random() * 9));
            f = String.valueOf((int) (1 + Math.random() * 9));
            g = String.valueOf((int) (1 + Math.random() * 9));
            h = String.valueOf((int) (1 + Math.random() * 9));
            ii = String.valueOf((int) (1 + Math.random() * 9));
            j = String.valueOf((int) (1 + Math.random() * 9));
            suiji = a + b + c + d + e + f + g + h + ii + j;

            for (int i = 0; i < 15; i++) {
                answerhui = TIDhttpPost(host, fanhui, suiji);
                Thread.sleep(100);
                if (answerhui == "#编号不存在" || answerhui == "#超时") {
                    return null;
                }
                if (!answerhui.equals("")) {

                    LtLog.e("结果为：" + answerhui);

                    String[] aa = answerhui.split("\\|");
                    if (aa.length > 1) {
                        /*String s = sendTongTask(token1, answerhui);
                        LtLog.e("七牛云：" + s);*/
                    }

                    return answerhui;
                }

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public String sendTongTask(String token, String imageName) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd|HHmmss");
        String date = sDateFormat.format(new java.util.Date());
        LtLog.e("-----------------....." + date.split("\\|"));
        String[] dataTime = date.split("\\|");

        String keyStr = dataTime[0] + "_" + AtFairyConfig.getGameID() + "_" + imageName + "_" + dataTime[1] + ".png";
        LtLog.e("-----------------....." + keyStr);
        String filePath = "/sdcard/screen.png";

        String s = httpPost(filePath, keyStr, token);

        fileDelete(filePath);
        return s;
    }

    public static String httpGet(String url) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String httpPost(String filePath, String key, String mToken) throws Exception {
        LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------httpPost....." + mToken);

        try {
            JSONObject UrlJson = new JSONObject(mToken);
            LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------httpPost....." + UrlJson.optString("data"));
            OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);


            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", "head_image", body)
                    .addFormDataPart("token", UrlJson.optString("data"))
                    .addFormDataPart("key", key)
                    .build();
            Request request = new Request.Builder()
                    .url("http://up-z2.qiniu.com/")
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                LtLog.i(mFairy.getLineInfo("") + "-------" + "-------------response....." + response.body().string());
                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public boolean fileDelete(String strFile) {
        try {
            File f = new File(strFile);
            if (f.exists()) {
                f.delete();
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private String GetImageStr(String imgpath) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgpath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码

        // LtLog.i(publicFunction.getLineInfo() + "-------" + new String(android.util.Base64.encode(data, android.util.Base64.DEFAULT)) + "-------------response.....");
        return new String(android.util.Base64.encode(data, android.util.Base64.DEFAULT));//返回Base64编码过的字节数组字符串
    }

    private static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            String html = new String(data, "UTF-8");
            return html;
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    private String TIDhttpPost(String host, String TID, String suiji) {
        System.out.println(TID);
        String answer1 = null;
        int js = 0;
        js++;
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", TID)
                .addFormDataPart("r", suiji)
                .build();
        Request request = new Request.Builder()
                .url("http://" + host + "/GetAnswer.aspx")
                .post(requestBody)
                .build();
        try {
            //for (int i = 0; i <10 ; i++) {
            Response response = client.newCall(request).execute();
            //LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response=" + response.body().string() );
            String result = response.body().string();

            answer1 = response.toString();
            try {
                //  LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response=" + result);
                Thread.sleep(3000);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //}
//            return response.toString();

        } catch (IOException e) {
            // LtLog.i(publicFunction.getLineInfo() + "-------" + "-------------response.....");
            e.printStackTrace();
        }
        return "error";

    }


    /**
     * 智能答题
     */
    public void wendaAIAnswer() throws Exception {
        List<String> answerStrABCD = new ArrayList();
        String mStr = getPictureText(361, 121, 710, 77);//题目范围x,y,w,h
        LtLog.e(this.mFairy.getLineInfo("题目是=" + mStr));
        if (mStr == null || mStr.equals("")) {
            this.mFairy.onTap(393, 248, 434, 260, "没有识别到题目,默认选A", 1000);
        } else {
            answerStrABCD.add(getPictureText(257, 231, 157, 49));//A范围x,y,w,h
            answerStrABCD.add(getPictureText(254, 322, 157, 54));//B范围x,y,w,h

            String c = getPictureText(257, 421, 157, 50);
            if (!c.equals("")) {
                answerStrABCD.add(c);//C范围x,y,w,h
            }
            String d = getPictureText(255, 516, 157, 49);
            if (!d.equals("")) {
                answerStrABCD.add(d);//C范围x,y,w,h
            }

            String[] answer = this.findAnswer(mStr, AtFairyConfig.getGameID());
            if (answer != null) {
                LtLog.e(mFairy.getLineInfo("开始匹配答案："));
                for (int j = 0; j < answerStrABCD.size(); ++j) {
                    LtLog.e(j + " : " + answerStrABCD.get(j));

                    for (int i = 0; i < answer.length; ++i) {
                        if (answerStrABCD.get(j).equals(answer[i])) {
                            switch (j) {
                                case 0:
                                    mFairy.onTap(383, 246, 413, 263, "匹配到正确答案A", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(349, 343, 382, 358, "匹配到正确答案B", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(400, 438, 429, 455, "匹配到正确答案C", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(375, 532, 408, 552, "匹配到正确答案D", 1000);
                                    break;
                            }
                            return;
                        }
                    }
                }
            }

            LtLog.e(this.mFairy.getLineInfo("没有匹配到,开始上传"));
            LtLog.i(this.mFairy.getLineInfo("----------------------------upDown>"));
            String answerStr = "";
            this.mFairy.onTap(424, 252, 446, 263, "A", 1000);
            for (int i = 0; i < 10; ++i) {
                result = this.mFairy.findPic(656, 212, 770, 304, "wenda2.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(0);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---A---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(654, 312, 776, 396, "wenda2.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(1);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---B---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(663, 403, 771, 495, "wenda2.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(2);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---C---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(661, 499, 778, 587, "wenda2.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(3);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---D---" + answerStr));
                    break;
                }
                Thread.sleep(200);
            }

            if (answerStr != "") {
                this.UpAnswerAndTitle(mStr, answerStr, AtFairyConfig.getGameID());
            }
        }
    }

    public void srAIAnswer() throws Exception {
        List<String> answerStrABCD = new ArrayList();

        String mStr = getPictureText(310, 165, 644, 75);//题目范围x,y,w,h
        LtLog.e(this.mFairy.getLineInfo("题目是=" + mStr));
        if (mStr == null || mStr.equals("")) {
            this.mFairy.onTap(352, 302, 357, 316, "没有识别到题目,默认选A", 1000);
            mFairy.onTap(895, 579, 935, 588, "", 3000);
        } else {
            answerStrABCD.add(getPictureText(380, 293, 276, 31));//A范围x,y,w,h
            answerStrABCD.add(getPictureText(380, 343, 237, 45));//B范围x,y,w,h

            String c = getPictureText(380, 403, 244, 38);
            if (!c.equals("")) {
                answerStrABCD.add(c);//C范围x,y,w,h
            }
            String d = getPictureText(380, 459, 237, 36);
            if (!d.equals("")) {
                answerStrABCD.add(d);//C范围x,y,w,h
            }

            String[] answer = this.findAnswer(mStr, AtFairyConfig.getGameID());
            if (answer != null) {
                LtLog.e(mFairy.getLineInfo("开始匹配答案："));
                for (int j = 0; j < answerStrABCD.size(); ++j) {
                    LtLog.e(j + " : " + answerStrABCD.get(j));

                    for (int i = 0; i < answer.length; ++i) {
                        if (answerStrABCD.get(j).equals(answer[i])) {
                            switch (j) {
                                case 0:
                                    mFairy.onTap(349, 307, 355, 310, "匹配到正确答案A", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(350, 362, 355, 367, "匹配到正确答案B", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(352, 416, 357, 425, "匹配到正确答案C", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(351, 475, 355, 480, "匹配到正确答案D", 1000);
                                    break;
                            }
                            mFairy.onTap(895, 579, 935, 588, "", 3000);
                            return;
                        }
                    }
                }
            }

            LtLog.e(this.mFairy.getLineInfo("没有匹配到,开始上传"));
            LtLog.i(this.mFairy.getLineInfo("----------------------------upDown>"));
            String answerStr = "";
            this.mFairy.onTap(349, 307, 355, 310, "A", 1000);
            mFairy.onTap(895, 579, 935, 588, "", 1000);
            for (int i = 0; i < 10; ++i) {
                result = this.mFairy.findPic(305, 269, 407, 344, "sr1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(0);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---A---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(303, 326, 405, 402, "sr1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(1);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---B---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(301, 384, 403, 455, "sr1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(2);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---C---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(302, 436, 405, 519, "sr1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(3);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---D---" + answerStr));
                    break;
                }
                Thread.sleep(200);
            }

            if (answerStr != "") {
                this.UpAnswerAndTitle(mStr, answerStr, AtFairyConfig.getGameID());
            }
        }
    }

    public String bitmapToBase64(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String trWebOCR(String url, Bitmap bitmap) {
        String result = null;
        String imgBase64 = bitmapToBase64(bitmap, 50);
        try {
            FormBody.Builder builder = new FormBody.Builder();

            builder.add("img", imgBase64);
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getPictureText(int x, int y, int width, int height) {

        Mat mat = mFairy.getScreenMat(x, y, width, height, 1, 0, 0, 1);

        Bitmap bmpCanny = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);

        org.opencv.android.Utils.matToBitmap(mat, bmpCanny);

        String str = trWebOCR("http://192.168.1.254:8089/api/tr-run/", bmpCanny);

        LtLog.e("str:"+str);

        if (str == null || str.equals("")) {
            return "";
        }

        LtLog.e("trWebOCR :" + str);

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) new JSONObject(new JSONObject(str).get("data").toString()).get("raw_out");
            String mStr = new JSONArray(jsonArray.get(0).toString()).get(1).toString();
            return mStr;
        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "";
    }

    private String[] findAnswer(String title, String game_id) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String resultStr = null;
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/FindTheAnswer?title=" + title + "&game_id=" + game_id).get().build();
        Response response = client.newCall(request).execute();
        resultStr = response.body().string();
        resultStr = (new JSONTokener(resultStr)).nextValue().toString();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(resultStr);
        }catch (Exception e){
            LtLog.e(e.getMessage());
            return null;
        }

        if (jsonObject.getString("data").equals("false")) {
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------not title"));
            return null;
        } else {
            String arr = jsonObject.getString("data").replaceAll("\\[", "");
            arr = arr.replaceAll("\\]", "");
            arr = arr.replaceAll("\"", "");
            String[] array = arr.split(",");
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------array=" + array));
            return array;
        }
    }

    private void UpAnswerAndTitle(String title, String answer, String game_id) throws InterruptedException {
        String resultStr = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "title=" + title + "&game_id=" + game_id + "&answer=" + answer);
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/AddTitle").post(body).build();

        try {
            Response response = client.newCall(request).execute();
            resultStr = response.body().string();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Thread.sleep(100L);
    }

    private void upDown(String mAswer, List<String> answerStrABCD) throws Exception {
        LtLog.i(this.mFairy.getLineInfo("----------------------------upDown>"));
        String answerStr = "";
        this.mFairy.onTap(424, 252, 446, 263, "A", 1000);
        for (int i = 0; i < 10; ++i) {
            result = this.mFairy.findPic(656, 212, 770, 304, "wenda2.png");
            if (result.sim > 0.8f) {
                answerStr = answerStrABCD.get(0);
                LtLog.e(this.mFairy.getLineInfo("正确答案---A---" + answerStr));
                break;
            }

            result = this.mFairy.findPic(654, 312, 776, 396, "wenda2.png");
            if (result.sim > 0.8f) {
                answerStr = answerStrABCD.get(1);
                LtLog.e(this.mFairy.getLineInfo("正确答案---B---" + answerStr));
                break;
            }

            result = this.mFairy.findPic(663, 403, 771, 495, "wenda2.png");
            if (result.sim > 0.8f) {
                answerStr = answerStrABCD.get(2);
                LtLog.e(this.mFairy.getLineInfo("正确答案---C---" + answerStr));
                break;
            }

            result = this.mFairy.findPic(661, 499, 778, 587, "wenda2.png");
            if (result.sim > 0.8f) {
                answerStr = answerStrABCD.get(3);
                LtLog.e(this.mFairy.getLineInfo("正确答案---D---" + answerStr));
                break;
            }
            Thread.sleep(200);
        }

        if (answerStr != "") {
            this.UpAnswerAndTitle(mAswer, answerStr, AtFairyConfig.getGameID());
        }
    }



    public void AIAnswerWGJX() throws Exception {
        List<String> answerStrABCD = new ArrayList();
        String mStr = getPictureText(335, 168, 801, 107);//题目范围x,y,w,h
        LtLog.e(this.mFairy.getLineInfo("题目是=" + mStr));
        if (mStr==null || mStr.equals("")) {
            this.mFairy.onTap(460, 311, 492, 329, "没有识别到题目,默认选A", 1000);
        } else {
            answerStrABCD.add(getPictureText(321, 286, 100, 68));//A范围x,y,w,h
            answerStrABCD.add(getPictureText(759, 286, 100, 68));//B范围x,y,w,h
            answerStrABCD.add(getPictureText(320, 382, 100, 68));//C范围x,y,w,h
            answerStrABCD.add(getPictureText(761, 380, 100, 68));//D范围x,y,w,h

            String[] answer = this.findAnswer(mStr, AtFairyConfig.getGameID());
            if (answer == null) {
                LtLog.e(this.mFairy.getLineInfo("识别到了题目但没有答案,开始上传"));
                this.upDown(mStr, answerStrABCD);
            } else {
                for (int j = 0; j < answerStrABCD.size(); ++j) {

                    LtLog.e(j+" : "+answerStrABCD.get(j));

                    for (int i = 0; i < answer.length; ++i) {
                        if(answerStrABCD.get(j).equals(answer[i])){
                            switch (j) {
                                case 0:
                                    mFairy.onTap(347, 316, 389, 327, "匹配到正确答案A", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(823, 311, 878, 329, "匹配到正确答案B", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(366, 412, 405, 432, "匹配到正确答案C", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(818, 409, 870, 429, "匹配到正确答案D", 1000);
                                    break;
                            }
                            return;
                        }
                    }
                }
                this.upDown(mStr, answerStrABCD);
            }
        }
    }



}
