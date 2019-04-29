package com.fxj.runnable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.bean.returnbean.IndexTransaction;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TxWebSocket extends Thread{

    private BitCoinApi bitCoinApi;

    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    private WebSocketSession session;

    public TxWebSocket(BitCoinJsonRpcLink bitCoinJsonRpcLink, BitCoinApi bitCoinApi, WebSocketSession session) {
        this.bitCoinApi = bitCoinApi;
        this.bitCoinJsonRpcLink = bitCoinJsonRpcLink;
        this.session = session;
    }

    @Override
    public void run() {
        int setSize = 0;
        while (true){
            JSONArray memPool = null;
            Set<IndexTransaction> set = new TreeSet<>();
            try {
                memPool = bitCoinJsonRpcLink.getMemPool();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            int size = memPool.size();
            if (setSize!=size){
                for (int i = memPool.size()-1 ; i >0 ; i--){
                    JSONObject txByHash = bitCoinApi.getTxByHash(memPool.get(i).toString());
                    IndexTransaction indexTransaction = new IndexTransaction();
                    indexTransaction.setTxhash(txByHash.getString("txid"));
                    indexTransaction.setTime(txByHash.getLong("locktime"));
                    indexTransaction.setFees(txByHash.getJSONArray("vout").getJSONObject(1).getFloat("value").toString());
                    indexTransaction.setPrice("5");
                    set.add(indexTransaction);
                }
                List list = new ArrayList();
                int i = 1;
                for (IndexTransaction indexTransaction : set) {
                    list.add(indexTransaction);
                    if(i==5){
                        break;
                    }
                }
                try {
                    session.sendMessage(new TextMessage(list.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
