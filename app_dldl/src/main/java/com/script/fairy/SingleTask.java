

package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2018/3/13.
 */

public class SingleTask {
    public GamePublicFuntion gamePublicFuntion;
    public FindResult result;
    public AtFairyImpl mFairy;
    public boolean gw = false;
    public SingleTask(AtFairyImpl ypFairy) throws Exception {
        mFairy = ypFairy;
        gamePublicFuntion = new GamePublicFuntion(ypFairy);
    }

    public boolean t0,t1, t2, t3, t4, t5, t6, t7= false;

    public int activity(String str) throws Exception {
        int count = 0;
        int err = 0;
        while (mFairy.condit()) {

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {
                err = 0;
            } else {
                err++;
                if (err > 2) {
                    return -1;
                }
            }


            FindResult result1 = mFairy.findPic(35, 252, 184, 1048, str);
            if (result1.sim > 0.9f) {
                result = mFairy.findPic(result1.x + 342, result1.y - 50,
                        result1.x + 558, result1.y + 70, "ling.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "ι’ε", 1000);
                    count = 0;
                    continue;
                }

                result = mFairy.findPic(result1.x + 342, result1.y - 50,
                        result1.x + 558, result1.y + 70, "qian.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "εεΎ", 1000);
                    return 0;
                }


                result = mFairy.findPic(result1.x + 342, result1.y - 50,
                        result1.x + 558, result1.y + 70, "mai.png");
                if (result.sim > 0.8f) {
                    return 1;
                }

                result = mFairy.findPic(result1.x + 342, result1.y - 50,
                        result1.x + 558, result1.y + 70, "wan.png");
                if (result.sim > 0.8f) {
                    return 1;
                }
            }


            count++;

            if (count % 2 == 0) {
                mFairy.ranSwipe(144, 409, 186, 749, 2, (long)1000, 3000);
                LtLog.e(mFairy.getLineInfo("δ»»ε‘ζ²‘ζζΎε°,ζ»ε¨"));
            }

            if (count > 10) {
                return 1;
            }


        }
        return -1;
    }

    public void minit()throws Exception{
        result = mFairy.findPic("tui.png");
        mFairy.onTap(0.8f, result, "ιεΊ", 1000);

        result = mFairy.findPic("ok.png");
        mFairy.onTap(0.8f, result, "η‘?ε?", 1000);

        result = mFairy.findPic("c.png");
        mFairy.onTap(0.8f, result, 648,426,670,446,"ηΉθ?­", 1000);

        result = mFairy.findPic(489,2,717,392,"close.png");
        mFairy.onTap(0.8f, result, "ε³ι­", 1000);

        result = mFairy.findPic(522,195,717,479,"close1.png");
        mFairy.onTap(0.8f, result, "ε³ι­", 1000);

        result = mFairy.findPic(517,12,713,382,"close5.png");
        mFairy.onTap(0.8f, result, "ε³ι­", 1000);


        result = mFairy.findPic(530,8,719,339,"close4.png");
        mFairy.onTap(0.8f, result, "ε³ι­", 1000);
    }//init

    abstract class SingleTask_Context extends TaskContent {
        public String name;

        public SingleTask_Context(AtFairyImpl mFairy) throws Exception {
            super(mFairy);
        }

        @Override
        void create() throws Exception {
            if (AtFairyConfig.getOption("gw").equals("1")) {
                gw = true;
            }


        }


        @Override
        void init() throws Exception {


            minit();

            result = mFairy.findPic(611, 402, 716, 756, "rc.png");
            if (result.sim > 0.8f) {
                setTaskName(1);
            }

        }

        @Override
        void content_01() throws Exception {
            overtime(10, 0);

            result = mFairy.findPic("rc.png");
            mFairy.onTap(0.8f, result, "ζ₯εΈΈ", 1000);

            result = mFairy.findPic("activity.png");
            if (result.sim > 0.8f) {


                for (int i = 0; i < 5; i++) {
                    result = mFairy.findPic(461, 264, 667, 1025, "ling.png");
                    if (result.sim > 0.8f) {
                        mFairy.onTap(0.8f, result, "ι’ε", 2000);
                        i = 0;
                    }
                }
                if(err==1) {
                    for (int i = 0; i < 5; i++) {
                        mFairy.onTap(240 + (i * 100), 149, 242 + (i * 100), 150, "", 500);
                    }
                    Thread.sleep(2000);
                }


                result = mFairy.findPic("close3.png");
                mFairy.onTap(0.8f, result, "ε³", 1000);

                result = mFairy.findPic("activity.png");
                if (result.sim > 0.8f) {
                    int num = activity(name);
                    if (num == -1) {
                        setTaskName(0);
                        return;
                    }

                    if (num == 1) {
                        setTaskEnd();
                        return;
                    }
                    if (num == 0) {
                        setTaskName(2);
                        return;
                    }
                }
            }
        }
    }

    public void wx() throws Exception {
        if (AtFairyConfig.getOption("t0").equals("1")) {
            t0 = true;
        }
        if (AtFairyConfig.getOption("t1").equals("1")) {
            t1 = true;
        }
        if (AtFairyConfig.getOption("t2").equals("1")) {
            t2 = true;
        }
        if (AtFairyConfig.getOption("t3").equals("1")) {
            t3 = true;
        }
        if (AtFairyConfig.getOption("t4").equals("1")) {
            t4 = true;
        }
        if (AtFairyConfig.getOption("t5").equals("1")) {
            t5 = true;
        }
        if (AtFairyConfig.getOption("t6").equals("1")) {
            t6 = true;
        }
        if (AtFairyConfig.getOption("t7").equals("1")) {
            t7 = true;
        }
        int err=0;
        while (mFairy.condit()) {
            result = mFairy.findPic("battle.png");
            mFairy.onTap(0.8f, result, "ζζ", 1000);

            result = mFairy.findPic("rc.png");
            if(result.sim>0.8f){
                err=0;
            }
            err++;
            if(err>30){
                minit();
                err=0;
            }

            if(gamePublicFuntion.timeJudge(600000)){
                if(t0){t0();}
                if(t1){t1();}
                if(t2){t2();}
                if(t3){t3();}
                if(t4){t4();}
                if(t5){t5();}
                if(t6){t6();}
                if(t7){t7();}
                minit();
            }
        }
    }//ζ ιδ»»ε‘

    public void gw() throws Exception {
        result = mFairy.findPic("x5.png");
        if (result.sim > 0.8f) {
            result = mFairy.findPic("x3.png");
            if (result.sim > 0.8f) {
                for (int i = 0; i < 5; i++) {
                    mFairy.onTap(486, 726, 533, 747, "ιΌθ", 1000);
                }
                mFairy.onTap(618, 329, 641, 350, "ε³ι­", 2000);
            } else {
                result = mFairy.findPic("x6.png");
                mFairy.onTap(0.8f, result, "ιεΈιΌθ", 1000);
            }
        }


    }//ιΌθ

    public int n=1;

    public void t0() throws Exception{
        new SingleTask_Context(mFairy) {
            @Override
            void content_01() throws Exception {
                setTaskName(2);
                t0=false;
            }

            @Override
            void content_02() throws Exception {
                overtime(30,0);

                result = mFairy.findPic("d1.png");
                mFairy.onTap(0.8f, result, "ζη½δΉθ·―", 1000);

                result = mFairy.findPic("d3.png");
                mFairy.onTap(0.8f, result, "ζζ", 9000);

                result = mFairy.findPic(0,340,709,1224,"d6.png");
                if(result.sim>0.8f) {
                    err=0;
                    mFairy.onTap(0.8f, result, "θε€©", 500);
                }

                result = mFairy.findPic(0,340,709,1224,"d6.png");
                if(result.sim>0.8f) {
                    err=0;
                    mFairy.onTap(0.8f, result, "θε€©", 500);
                }

                result = mFairy.findPic(194,974,610,1078,"err.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result,633,172,661,200, "η»ζ", 500);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("d2.png");
                if(result.sim>0.8f) {
                    err=0;
                    switch (n){
                        case 1:
                            mFairy.onTap(450,1180,486,1213,"ζ?ι",2000);
                            break;
                        case 2:
                            mFairy.onTap(585,1167,625,1195,"ε°ιΎ",2000);
                            break;
                            default:
                                setTaskEnd();
                            return;
                    }

                    result = mFairy.findPic(16,160,669,1095,"xin.png");
                    mFairy.onTap(0.8f, result, "ζ°", 1000);

                }

                result = mFairy.findPic("d4.png");
                if(result.sim>0.8f) {
                    err=0;
                }

                result = mFairy.findPic("d5.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result, 441,847,487,867,"η‘?θ?€", 2000);
                    err=0;
                }

                result = mFairy.findPic("d7.png");
                if(result.sim>0.8f) {
                    mFairy.onTap(0.8f, result, 442,848,492,874,"ζζε€±θ΄₯", 2000);
                    n++;
                }

            }
        };


    }//ζη½δΉθ·―

    public void t1() throws Exception {
        new SingleTask_Context(mFairy) {


            @Override
            void content_01() throws Exception {
                name = "t1.png";
                t1 = false;
                super.content_01();
            }

            @Override
            void content_02() throws Exception {
                overtime(20, 99);

                gw();

                result = mFairy.findPic(211, 80, 513, 985, "x1.png");
                mFairy.onTap(0.8f, result, "ζζ", 1000);

                result = mFairy.findPic("x2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gw) {
                        mFairy.onTap(648, 392, 677, 421, "ιΌθ", 1000);
                        gw = false;
                    }
                }



                result = mFairy.findPic("x4.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 337, 840, 383, 870, "η‘?ε?", 1000);
                    setTaskName(0);
                    return;
                }

            }
        };


    }//ε­¦ι’θ―ηΌ

    public void t2() throws Exception {
        new SingleTask_Context(mFairy) {

            @Override
            void content_01() throws Exception {
                name = "t2.png";
                t2 = false;
                super.content_01();
            }

            @Override
            void content_02() throws Exception {
                overtime(20, 99);

                gw();

                result = mFairy.findPic(211, 80, 513, 985, "x1.png");
                mFairy.onTap(0.8f, result, "ζζ", 1000);

                result = mFairy.findPic("s1.png");
                mFairy.onTap(0.8f, result, 318, 732, 378, 754, "η‘?ε?", 1000);

                result = mFairy.findPic("s2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                    if (gw) {
                        mFairy.onTap(648, 392, 677, 421, "ιΌθ", 1000);
                        gw = false;
                    }
                }

                result = mFairy.findPic("s3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 321,846,390,868, "η‘?ε?", 1000);
                    setTaskName(0);
                    return;
                }

            }
        };


    }//ζε»δΉι½

    public void t3() throws Exception {
        new SingleTask_Context(mFairy) {

            @Override
            void content_01() throws Exception {
                name = "t3.png";
                super.content_01();
            }

            @Override
            void content_02() throws Exception {
                overtime(20, 99);
                t3=true;
                result = mFairy.findPic("j1.png");
                mFairy.onTap(0.8f, result, "εΉι", 1000);

                result = mFairy.findPic("j2.png");
                if (result.sim > 0.8f) {
                    err = 0;
                }

                result = mFairy.findPic("j4.png");
                if (result.sim > 0.8f) {
                    t3 = false;
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("jia.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 448,836,506,866, "ζζε€±θ΄₯", 1000);
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic("j3.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, 444,841,499,865, "η‘?ε?", 1000);
                    setTaskName(0);
                    return;
                }

            }
        };


    }//ζι­εΊ

    public void t4() throws Exception {
        new SingleTask_Context(mFairy) {

            @Override
            void content_01() throws Exception {
                name = "t4.png";
                super.content_01();
            }

            @Override
            void content_02() throws Exception {
                overtime(10, 99);

                result = mFairy.findPic("mian.png");
                mFairy.onTap(0.8f, result, "εθ΄Ή", 1000);

                result = mFairy.findPic("mian1.png");
                mFairy.onTap(0.8f, result, "η‘?ε?", 1000);

                result = mFairy.findPic("main2.png");
                if(result.sim>0.8f){
                    mFairy.onTap(630,241,653,259,"ε³ι­",1000);
                    setTaskEnd();
                    minit();
                    return;
                }
            }
        };


    }//ηΉθ?­

    public void t5() throws Exception {
        new SingleTask_Context(mFairy) {
        int er=0;
        @Override
        void content_01() throws Exception {
            name = "t5.png";
            t5 = false;
            super.content_01();
        }

        @Override
        void content_02() throws Exception {
            overtime(30, 99);

            result = mFairy.findPic("k2.png");
            mFairy.onTap(0.8f, result, "δΈδΈθ", 1000);

            result = mFairy.findPic("k6.png");
            mFairy.onTap(0.8f, result, "εδΊΊθΏε₯", 1000);

            result = mFairy.findPic("k7.png");
            mFairy.onTap(0.8f, result, "η‘?ε?", 1000);

            result = mFairy.findPic("k1.png");
            if (result.sim > 0.8f) {
                err = 0;
            }

            result = mFairy.findPic("k3.png");
            mFairy.onTap(0.8f, result, "ζη?‘", 1000);

            result = mFairy.findPic("k4.png");
            if(result.sim>0.8f) {
                er++;
                if(er>5){
                    mFairy.onTap(636,169,659,192,"ε³ι­",1000);
                    setTaskName(0);
                    return;
                }
            }else{
                er=0;
            }


            result = mFairy.findPic("k8.png");
            if (result.sim > 0.8f) {
                mFairy.onTap(0.8f, result, "η¦»εΌ", 1000);
                setTaskName(0);
                return;
            }

        }
    };


}//ζ΅·η₯δΉθ

    public void t6() throws Exception {
        new SingleTask_Context(mFairy) {
            @Override
            void content_01() throws Exception {
                name = "t6.png";
                t6 = false;
                super.content_01();
            }

            @Override
            void content_02() throws Exception {
                overtime(10, 99);
                result = mFairy.findPic("h1.png");
                if (result.sim > 0.8f) {
                    mFairy.onTap(0.8f, result, "η‘?ε?εθ§£", 3000);
                    mFairy.onTap(631,176,660,195,"ε³ι­εθ§£ηͺε£",1000);
                    setTaskEnd();
                    return;
                }

            }
        };


    }//ι­η―εθ§£

    public void t7()throws Exception{
        int err=0;
        minit();
        while (mFairy.condit()){
            err++;
            if(err>20){
                break;
            }
            result=mFairy.findPic(415,481,712,936,"z1.png");
            mFairy.onTap(0.8f,result,"ε?ι¨",1000);

            result=mFairy.findPic("z2.png");
            mFairy.onTap(0.8f,result,"ε?εΊ",1000);

            result=mFairy.findPic("z3.png");
            if(result.sim>0.8f){
                for(int i=0;i<10;i++){
                    result=mFairy.findPic(441,250,646,952,"z4.png");
                    if(result.sim>0.8f){
                        i=0;
                        mFairy.onTap(0.8f,result,"εΌε―",1000);
                    }

                    if(i%3==0 && i!=0){
                        mFairy.ranSwipe(275,442,318,783,2,(long)1000,2000);
                        LtLog.e(mFairy.getLineInfo("ζ»ε¨"));
                    }

                }
                LtLog.e(mFairy.getLineInfo("ε?η?±ε?ζ"));
                mFairy.onTap(633,168,660,198,"ε³ι­",1000);
                break;

            }
        }

    }//ε?ι¨ε?η?±
}

