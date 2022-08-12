package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;

/**
 * Created by user on 2019/2/15.
 */

public class GamePublicFuntion {
    public AtFairyImpl mFairy;
    public FindResult result;

    public GamePublicFuntion(AtFairyImpl ypFairy) {
        mFairy = ypFairy;
    }




    public void close(int count)throws Exception{

        for (int i = 0; i < count; i++) {

            result = mFairy.findPic(878,8,1275,289,"close1.png");
            mFairy.onTap(0.8f,result,"关闭",1500);


        }

    }

}
