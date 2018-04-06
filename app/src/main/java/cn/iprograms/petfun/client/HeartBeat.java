package cn.iprograms.petfun.client;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JaCy on 2018/1/3.
 */

class HeartBeat {
    private static boolean heartBeatFlag=false;
    private static HeartbeatTask heartBeatTask;
    private static Timer timer;
    static void Start(){
        heartBeatTask=new HeartbeatTask();
        timer = new Timer();
        timer.schedule(heartBeatTask,0,8000);

    }
    static void Stop() {
        heartBeatFlag=false;
        timer.cancel();
        timer.purge();
        heartBeatTask=null;
        timer=null;
    }
    static boolean isBeating(){
        return heartBeatFlag;
    }
    private static class HeartbeatTask extends TimerTask{
        @Override
        public void run() {
            new Thread(new Send(Client.getSocket(),"<HEARTBEAT><divide><HEARTBEAT>")).start();
            heartBeatFlag=true;
        }
    }
}
