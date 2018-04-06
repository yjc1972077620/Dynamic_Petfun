package cn.iprograms.petfun.client;

import android.util.Log;

/**
 * Created by JaCy on 2017/11/16.
 */

public class DataSet {

    static String START_LOGIN_DATA="DISCONNECT";
    static String START_REGISTER_DATA="DISCONNECT";
    public static String START_CODE_DATA="DISCONNECT";
    static String TOPIC_ISSUE_DATA ="DISCONNECT";
    static String TOPIC_REPLY_DATA="DISCONNECT";
    static String CARRIER_BINDQQ_DATA="DISCONNECT";
    static String DYNAMIC_CONTENT_DATA="DISCONNECT";
    static String DYNAMIC_COMMENT_DATA="DISCONNECT";
//    static String TOPIC_CONCERN_DATA="DISCONNECT";
//    static String TOPIC_USEFUL_DATA="DISCONNECT";
//    static String TOPIC_USELESS_DATA="DISCONNECT";

    static boolean LOGIN=false;
    static boolean REGISTER=false;
    static boolean CODE=false;
    static boolean ISSUE=false;
    static boolean REPLY=false;
    static boolean BINDQQ=false;
    static boolean DYNAMIC=false;
    static boolean COMMENT=false;
//    static boolean CONCERN=false;
//    static boolean USEFUL=false;
//    static boolean USELESS=false;




    static void setDataUtils(String DATA){
        String headOrder=DataSplit.getHeadtOrder(DATA);
        switch (headOrder){
            case "<login>":
                START_LOGIN_DATA=DATA;
                LOGIN=true;
                break;
            case "<register>":
                START_REGISTER_DATA=DATA;
                REGISTER=true;
                break;
            case "<code>":
                START_CODE_DATA=DATA;
                CODE=true;
                break;
            case "<topic>":
                TOPIC_ISSUE_DATA =DATA;
                ISSUE=true;
                break;
            case "<comment>":
                TOPIC_REPLY_DATA =DATA;
                REPLY=true;
                break;
            case "<update>":
                CARRIER_BINDQQ_DATA=DATA;
                BINDQQ=true;
                break;
            case "<dynamic>":
                DYNAMIC_CONTENT_DATA=DATA;
                DYNAMIC=true;
                break;
            case "<dynamiccomment>":
                DYNAMIC_COMMENT_DATA=DATA;
                COMMENT=true;
                break;
            case "<HEARTBEAT>":
                Log.d("TESTTEST", "<HEARTBEAT>-------------------------------"+DATA);
                break;
            default:
                Log.d("TESTTEST", "*DATAUTILS************************ GET WRONG DATA **************************DATAUTILS*");
                break;
        }
    }
    static void initDataUtils(){
        START_LOGIN_DATA="DISCONNECT";
        START_REGISTER_DATA="DISCONNECT";
        START_CODE_DATA="DISCONNECT";
        TOPIC_ISSUE_DATA ="DISCONNECT";
        TOPIC_REPLY_DATA="DISCONNECT";
        CARRIER_BINDQQ_DATA="DISCONNECT";
        DYNAMIC_CONTENT_DATA="DISCONNECT";
        DYNAMIC_COMMENT_DATA="DISCONNECT";

        LOGIN=false;
        REGISTER=false;
        CODE=false;
        ISSUE=false;
        REPLY=false;
        BINDQQ=false;
        DYNAMIC=false;
        COMMENT=false;
    }
    static void setDataFlag(){
        LOGIN=true;
        REGISTER=true;
        CODE=true;
        ISSUE=true;
        REPLY=true;
        BINDQQ=true;
        DYNAMIC=true;
        COMMENT=true;
    }
}
