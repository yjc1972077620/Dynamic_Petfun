package cn.iprograms.petfun.client;

import android.os.Handler;
import android.os.Message;

public class DataHandle {
    private static String DATA_SENT="";//已经发送了的消息
    private static LoginHandler staticLoginHandler;
    private static RegisterHandler staticRegisterHandler;
    private static CodeHandler staticCodeHandler;
    private static IssueHandler staticIssueHandler;
    private static ReplyHandler staticReplyHandler;
    private static BindQQHandler staticBindQQHandler;
    private static DynamicHandler staticDynamicHandler;
    private static CommentHandler staticCommentHandler;



    //回调执行
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1://登录
                    staticLoginHandler.listenerEnd();
                    switch (DataSet.START_LOGIN_DATA){
                        case DataStandard.START_LOGIN+"<divide>01":
                            staticLoginHandler.loginSuccess();
                            break;
                        case DataStandard.START_LOGIN+"<divide>10":
                            staticLoginHandler.usernameError();
                            break;
                        case DataStandard.START_LOGIN+"<divide>11":
                            staticLoginHandler.passwordError();
                            break;
                        default:
                            staticLoginHandler.netException();
                            break;
                    }
                    break;
                case 2://注册
                    staticRegisterHandler.listenerEnd();
                    switch (DataSet.START_REGISTER_DATA){
                        case DataStandard.START_REGISTER+"<divide>01":
                            staticRegisterHandler.registerSuccess();
                            break;
                        case DataStandard.START_REGISTER+"<divide>10":
                            staticRegisterHandler.usernameExit();
                            break;
                        default:
                            staticRegisterHandler.netException();
                            break;
                    }
                    break;
                case 3://验证码
                    staticCodeHandler.listenerEnd();
                    switch (DataSplit.getHeadtOrder(DataSet.START_CODE_DATA)){
                        case DataStandard.START_CODE:
                            staticCodeHandler.codeReceived();
                            break;
                        default:
                            staticCodeHandler.netException();
                            break;
                    }
                    break;
                case 4://话题
                    staticIssueHandler.listenerEnd();
                    switch (DataSet.TOPIC_ISSUE_DATA){
                        case DataStandard.TOPIC_ISSUE+"<divide>01":
                            staticIssueHandler.issueSuccess();
                            break;
                        default:
                            staticIssueHandler.netException();
                            break;
                    }
                    break;
                case 5://回复
                    staticReplyHandler.listenerEnd();
                    switch (DataSet.TOPIC_REPLY_DATA){
                        case DataStandard.TOPIC_REPLY+"<divide>01":
                            staticReplyHandler.replySuccess();
                            break;
                        default:
                            staticReplyHandler.netException();
                            break;
                    }
                    break;
                case 6://绑定QQ
                    staticBindQQHandler.listenerEnd();
                    switch (DataSet.CARRIER_BINDQQ_DATA){
                        case DataStandard.CARRIER_BINDQQ+"<divide>01":
                            staticBindQQHandler.bindSuccess();
                            break;
                        default:
                            staticBindQQHandler.netException();
                            break;
                    }
                    break;
                case 7:
                    staticDynamicHandler.listenerEnd();
                    switch (DataSet.DYNAMIC_CONTENT_DATA){
                        case DataStandard.DYNAMIC_CONTENT+"<divide>01":
                            staticDynamicHandler.sendSuccess();
                            break;
                        default:
                            staticDynamicHandler.netException();
                            break;
                    }
                    break;
                case 8:
                    staticCommentHandler.listenerEnd();
                    switch (DataSet.DYNAMIC_COMMENT_DATA){
                        case DataStandard.DYNAMIC_COMMENT+"<divide>01":
                            staticCommentHandler.sendSuccess();
                            break;
                        default:
                            staticCommentHandler.netException();
                            break;
                    }
            }
        }
    };
    //接收数据 监听
    public static void setOnReceiveListener(LoginHandler loginHandler){
        staticLoginHandler =loginHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.LOGIN){
                    if (!HeartBeat.isBeating()){
                        DataSet.LOGIN=true;
                    }
                }
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiveListener(RegisterHandler registerHandler){
        staticRegisterHandler =registerHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.REGISTER){
                    if (!HeartBeat.isBeating()){
                        DataSet.REGISTER=true;
                    }
                }
                Message message=new Message();
                message.what=2;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiveListener(CodeHandler codeHandler){
        staticCodeHandler=codeHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.CODE){
                    if (!HeartBeat.isBeating()){
                        DataSet.CODE=true;
                    }
                }
                Message message=new Message();
                message.what=3;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiveListener(IssueHandler issueHandler){
        staticIssueHandler=issueHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.ISSUE){
                    if (!HeartBeat.isBeating()){
                        DataSet.ISSUE=true;
                    }
                }
                Message message=new Message();
                message.what=4;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiveListener(ReplyHandler replyHandler){
        staticReplyHandler=replyHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.REPLY){
                    if (!HeartBeat.isBeating()){
                        DataSet.REPLY=true;
                    }
                }
                Message message=new Message();
                message.what=5;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiveListener(BindQQHandler bindQQHandler){
        staticBindQQHandler=bindQQHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.BINDQQ){
                    if (!HeartBeat.isBeating()){
                        DataSet.BINDQQ=true;
                    }
                }
                Message message=new Message();
                message.what=6;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiverListener(DynamicHandler dynamicHandler){
        staticDynamicHandler=dynamicHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.DYNAMIC){
                    if (!HeartBeat.isBeating()){
                        DataSet.DYNAMIC=true;
                    }
                }
                Message message=new Message();
                message.what=7;
                handler.sendMessage(message);
            }
        }).start();
    }
    public static void setOnReceiverListener(CommentHandler commentHandler){
        staticCommentHandler=commentHandler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!DataSet.COMMENT){
                    if (!HeartBeat.isBeating()){
                        DataSet.COMMENT=true;
                    }
                }
                Message message=new Message();
                message.what=8;
                handler.sendMessage(message);
            }
        }).start();
    }
    //发送数据
    public static void sendData(String data){
        DataSet.initDataUtils();
        new Thread(new Send(Client.getSocket(),data)).start();//启动发送数据线程，仅发送一下
        DATA_SENT=data;
    }
    //数据处理接口类
    public interface LoginHandler {
        void listenerEnd();
        void loginSuccess();
        void usernameError();
        void passwordError();
        void netException();
    }
    public interface RegisterHandler {
        void listenerEnd();
        void registerSuccess();
        void usernameExit();
        void netException();
    }
    public interface CodeHandler{
        void listenerEnd();
        void codeReceived();
        void netException();
    }
    public interface IssueHandler{
        void listenerEnd();
        void issueSuccess();
        void netException();
    }
    public interface ReplyHandler{
        void listenerEnd();
        void replySuccess();
        void netException();
    }
    public interface BindQQHandler{
        void listenerEnd();
        void bindSuccess();
        void netException();
    }
    public interface DynamicHandler{
        void listenerEnd();
        void sendSuccess();
        void netException();
    }
    public interface CommentHandler{
        void listenerEnd();
        void sendSuccess();
        void netException();
    }
}
