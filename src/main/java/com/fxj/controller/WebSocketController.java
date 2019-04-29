package com.fxj.controller;

import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.runnable.BlockWebSocket;
import com.fxj.runnable.TxWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class WebSocketController extends TextWebSocketHandler {

    @Autowired
    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    @Autowired
    private BitCoinApi bitCoinApi;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        BlockWebSocket blockWebSocket = null;
        TxWebSocket txWebSocket = null;
        if (payload.equals("block")){
//            blockWebSocket = new BlockWebSocket(bitCoinJsonRpcLink, bitCoinApi, session);
//            blockWebSocket.start();
//            if (txWebSocket!=null){
//                txWebSocket.interrupt();
//            }
        }else if(payload.equals("tx")){
//            txWebSocket = new TxWebSocket(bitCoinJsonRpcLink, bitCoinApi, session);
//            txWebSocket.start();
//            if (blockWebSocket!=null){
//                blockWebSocket.interrupt();
//            }
        }else{
            throw new Exception("标记有误");
        }
        System.out.println(123);
    }

}
