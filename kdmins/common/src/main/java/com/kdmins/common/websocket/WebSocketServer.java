/*
package com.kdmins.common.websocket;
import com.alibaba.fastjson.JSON;
import com.kdmins.common.commonInterface.websocketDeal;
import com.kdmins.common.pojo.WebcocketMessage;
import com.kdmins.common.pojo.sendMessageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
@ServerEndpoint("/websocket/{userId}/{groupId}")
@Component
public class WebSocketServer implements ApplicationContextAware {
    static Log log= LogFactory.getLog(WebSocketServer.class);
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String,ConcurrentHashMap<String,CopyOnWriteArraySet<Session>>> userSession=new ConcurrentHashMap<>();
    private static HashMap<String,websocketDeal> websocketDeal=new HashMap<String,websocketDeal>();
    private Session session;
    private String userId="";
    private String groupId="";
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId,@PathParam("groupId") String groupId) throws IOException {
        this.session = session;
        ConcurrentHashMap<String,CopyOnWriteArraySet<Session>> ConcurrentHashMap=userSession.get(groupId);
        if(ConcurrentHashMap==null){
            ConcurrentHashMap=new ConcurrentHashMap<String,CopyOnWriteArraySet<Session>>();
            userSession.put(groupId,ConcurrentHashMap);
        }
        CopyOnWriteArraySet<Session> userSession=ConcurrentHashMap.get(userId);
        if(userSession==null){
            userSession=new CopyOnWriteArraySet<Session>();
            ConcurrentHashMap.put(userId,userSession);
        }
        userSession.add(session);
        this.userId=userId;
        this.groupId=groupId;
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+userId+",当前在线人数为" + getOnlineCount());
    }
    @OnClose
    public void onClose() {
        CopyOnWriteArraySet<Session> userSession= getUserSession( userId, groupId);
        userSession.remove(session);
        if(userSession.size()==0){
            setUserSessionNull( userId, groupId);
        }
        System.out.println(getUserSession( userId, groupId));
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    @OnMessage
    public <T> void onMessage(String message, Session session) throws IOException {
        WebcocketMessage<T> data = (WebcocketMessage<T>)JSON.parse(message);
        Object resource = websocketDeal.get(data.getDealName()).doSomeTing(data.getMessage());

        sendMessage(JSON.toJSONString(resource));
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] websocketDeals = applicationContext.getBeanNamesForType(websocketDeal.class);
        for(String deal:websocketDeals){
            websocketDeal.put(((websocketDeal)applicationContext.getBean(deal)).getName(),(websocketDeal)applicationContext.getBean(deal));
        }

    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public CopyOnWriteArraySet<Session> getUserSession(String userId,String groupId)  {
        return userSession.get(groupId).get(userId);
    }
    public void setUserSessionNull(String userId,String groupId)  {
        ConcurrentHashMap<String, CopyOnWriteArraySet<Session>> userSessions = userSession.get(groupId);
        Iterator<String> iter = userSessions.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if(userId.equals(key)){
                iter.remove();
                return ;
            }
        }
    }
    //实现服务器主动推送
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public static sendMessageResult sendInfo(String message, String userId,String groupId) throws IOException {
        ConcurrentHashMap<String, CopyOnWriteArraySet<Session>> groupSessions = userSession.get(groupId);
        if(groupSessions==null){
            return new sendMessageResult(500,"该分组不存在");
        }
            CopyOnWriteArraySet<Session> userSessions = groupSessions.get(userId);
        if(userSessions==null){
            return new sendMessageResult(500,"该用户不存在");
        }
            for (Session user : userSessions) {
                try {
                    user.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return new sendMessageResult(200,"发送成功");
    }
    public  static <T> sendMessageResult sendInfo(WebcocketMessage<T> message, String groupId) throws IOException {
        ConcurrentHashMap<String, CopyOnWriteArraySet<Session>> groupSessions = userSession.get(groupId);
        if(groupSessions==null){
            return new sendMessageResult(500,"该分组不存在");
        }
        for (String item : groupSessions.keySet()) {
            CopyOnWriteArraySet<Session> userSessions = groupSessions.get(item);
            for (Session user : userSessions) {
                try {
                    user.getBasicRemote().sendText(JSON.toJSONString(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sendMessageResult(200,"发送成功");
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
*/
