package app.planet.component;

import app.planet.core.constant.MessageTypeConstant;
import com.alibaba.fastjson.JSON;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/websocket/{username}")
public class WebSocketServer {


    //存放用户Session
    private Session session;

    //存放当前用户名
    private String username;

    //存放需要接受消息的用户名
    private String toUsername;

    //存放在线的用户数量
    private static Integer onlineCount = 0;

    //存放每个客户端对应的Session
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username) throws IOException {
        this.session = session;
        this.username = username;
        sessionMap.put(username,this.session);
        onlineCount++;
        //在线用户列表
        Set<String> userList = sessionMap.keySet();

        Map<String, Object> messageMap = new HashMap<>();
        //封装所有用户列表
        messageMap.put("userList",userList);
        //返回消息类型
        messageMap.put("messageType", MessageTypeConstant.ONLINE);
        //返回在线人数
        messageMap.put("onlineCount",onlineCount);
        //返回用户名
        messageMap.put("username",this.username);

        //发送给所有用户谁上线了
        sendMessageAll(JSON.toJSONString(messageMap),username);

        // 更新在线人数(给所有人)
        Map<String, Object> updateMessageMap = new HashMap();

        updateMessageMap.put("messageType", MessageTypeConstant.ONLINE_USERS);
        //把所有用户放入updateMessageMap
        updateMessageMap.put("onlineUsers", userList);
        //返回在线人数
        updateMessageMap.put("number", onlineCount);
        // 更新所有的在线用户信息
        sendMessageAll(JSON.toJSONString(updateMessageMap),this.username);
    }
    @OnClose
    public void onClose(@PathParam("username") String username) throws IOException {
        //移除在线名单
        sessionMap.remove(username);
        //在线用户数减少
        onlineCount--;
        //通知下线
        Map<String, Object> offlineMap = new HashMap();

        offlineMap.put("messageType", MessageTypeConstant.OFFLINE);
        //所有在线用户
        offlineMap.put("onlineUsers", sessionMap.keySet());
        //下线用户的用户名
        offlineMap.put("username", this.username);
        //返回在线人数
        offlineMap.put("number", onlineCount);
        //发送信息
        sendMessageAll(JSON.toJSONString(offlineMap),this.username);
        log.info("连接断开, 总数:{}", sessionMap.size());

        // 更新在线人数(给所有人)
        Map<String, Object> updateOnlineMap = new HashMap();
        //获得所有的用户
        Set<String> userLists = sessionMap.keySet();
        //messageType
        updateOnlineMap.put("messageType", MessageTypeConstant.ONLINE_USERS);
        //把所有用户放入map
        updateOnlineMap.put("onlineUsers", userLists);
        //返回在线人数
        updateOnlineMap.put("number", onlineCount);
        // 消息发送所有人
        sendMessageAll(JSON.toJSONString(updateOnlineMap),this.username);
        log.info("连接断开, 总数:{}", sessionMap.size());
    }


    @OnMessage
    public void OnMessage(String message) throws IOException {
        log.info("收到客户端发来的消息:{}", message);
        //解析前端数据
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(message);
        //获取所有数据
        String textMessage = jsonObject.getString("message");
        String username = jsonObject.getString("username");
        String type = jsonObject.getString("type");
        String toUsername = jsonObject.getString("tousername");
        //群发
        if(type.equals(MessageTypeConstant.MASS)){
            Map<String, Object> massMap = new HashMap();
            massMap.put("messageType", MessageTypeConstant.ORDINARY_MESSAGE);
            //所有在线用户
            massMap.put("onlineUsers", sessionMap.keySet());
            //发送消息的用户名
            massMap.put("username", username);
            //返回在线人数
            massMap.put("number", onlineCount);
            //发送的消息
            massMap.put("textMessage", textMessage);
            //发送信息，所有人
            sendMessageAll(JSON.toJSONString(massMap),this.username);
        }
        //私发
        else{
            //发送给对应的私聊用户
            Map<String, Object> privateMap = new HashMap();
            privateMap.put("messageType", MessageTypeConstant.ORDINARY_MESSAGE);
            //所有在线用户
            privateMap.put("onlineUsers", sessionMap.keySet());
            //发送消息的用户名
            privateMap.put("username", username);
            //返回在线人数
            privateMap.put("number", onlineCount);
            //发送的消息
            privateMap.put("textMessage", textMessage);
            //发送信息
            sendMessageTo(JSON.toJSONString(privateMap),toUsername);

            //发送给自己
            Map<String, Object> ownMap = new HashMap();
            ownMap.put("messageType", MessageTypeConstant.ORDINARY_MESSAGE);
            //所有在线用户
            ownMap.put("onlineUsers", sessionMap.keySet());
            //发送消息的用户名
            ownMap.put("username", username);
            //返回在线人数
            ownMap.put("number", onlineCount);
            //发送的消息
            ownMap.put("textMessage", textMessage);
            //发送信息
            sendMessageTo(JSON.toJSONString(privateMap),username);
        }
    }
    /**
     *  消息发送所有人
     */
    public void sendMessageAll(String message, String FromUsername) throws IOException {
        for (Session session : sessionMap.values()) {
            //消息发送所有人（同步）getAsyncRemote
            session.getBasicRemote().sendText(message);
        }
    }
    /**
     *  消息发送指定人
     */
    public void sendMessageTo(String message, String toUsername) throws IOException {
        sessionMap.get(toUsername).getBasicRemote().sendText(message);
    }

}
