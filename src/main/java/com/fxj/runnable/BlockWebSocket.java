package com.fxj.runnable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.bean.returnbean.IndexBlock;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class BlockWebSocket extends Thread {

    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    private BitCoinApi bitCoinApi;

    private WebSocketSession session;

    private Set<IndexBlock> set = new TreeSet<>();

    public BlockWebSocket(BitCoinJsonRpcLink bitCoinJsonRpcLink, BitCoinApi bitCoinApi, WebSocketSession session) {
        this.bitCoinJsonRpcLink = bitCoinJsonRpcLink;
        this.bitCoinApi = bitCoinApi;
        this.session = session;
    }

    @Override
    public void run() {
        int maxHeight = 0;

        while (true){
            JSONObject chainInfo = bitCoinApi.getChainInfo();
            int height = (int) chainInfo.get("blocks");
            if (maxHeight != height){
                maxHeight = height;
                String blockHashByHeight = "";
                try {
                    blockHashByHeight = bitCoinJsonRpcLink.getBlockHashByHeight(height);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                JSONArray headersByHash = bitCoinApi.getHeadersByHash(1, blockHashByHeight);
                try {
                    session.sendMessage(new TextMessage(headersByHash.getString(0)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
