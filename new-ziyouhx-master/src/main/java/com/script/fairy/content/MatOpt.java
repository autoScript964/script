package com.script.fairy.content;

/**
 * Created by daiepngfei on 2020-08-12
 */
public class MatOpt {

    public String asKey() {
        StringBuilder sb = new StringBuilder("");
        if (method != Method.DEFAULT) {
            sb.append("-");
            sb.append(method.name());
            if(method == Method.BIN){
                sb.append("-");
                sb.append(threshold);
                sb.append("-");
                sb.append(binType);
                sb.append("-");
                sb.append(binMax);
            }
        }
        return sb.toString();
    }

    public enum Method {
        DEFAULT, HLS, HSV, GRAYS, BIN
    }
    Method method = Method.DEFAULT;
    int threshold = 0;
    int binType = 0;
    int binMax = 255;

    public static MatOpt newIns(){
        return new MatOpt();
    }

    public MatOpt method(Method m) {
        this.method = m;
        return this;
    }

    public MatOpt threshold(int threshold) {
        this.threshold = threshold;
        return this;
    }

    public MatOpt type(int type) {
        this.binType = type;
        return this;
    }

}
