package com.fxj.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxj.api.BitCoinApi;
import com.fxj.api.BitCoinJsonRpcLink;
import com.fxj.bean.returnbean.IndexBlock;
import com.fxj.bean.returnbean.IndexTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/html")
@CrossOrigin
public class HtmlController {

    @Autowired
    private BitCoinApi bitCoinApi;

    @Autowired
    private BitCoinJsonRpcLink bitCoinJsonRpcLink;

    private long maxHeight = 0;

    /**
     * 首页查询列表信息
     * @param sign
     * @return
     * @throws Exception
     */
    @GetMapping("/index")
    private List index(String sign) throws Throwable {
        List list = null;
        if (sign.equals("block")) {
            JSONObject chainInfo = bitCoinApi.getChainInfo();
            int height = (int) chainInfo.get("blocks")-4;
            String blockHashByHeight = bitCoinJsonRpcLink.getBlockHashByHeight(height);
            JSONArray headersByHash = bitCoinApi.getHeadersByHash(5, blockHashByHeight);
            list = headersByHash.toJavaList(IndexBlock.class);
            Collections.reverse(list);
            IndexBlock o = (IndexBlock) list.get(0);
            maxHeight = o.getHeight()-1;
        } else if (sign.equals("tx")) {
            JSONArray memPool = bitCoinJsonRpcLink.getMemPool();
            list = new ArrayList();
            for (int i = memPool.size()-1 ; i >(memPool.size()-6<=0?0:memPool.size()-6) ; i--){
                JSONObject txByHash = bitCoinJsonRpcLink.getRawTransaxtion(memPool.get(i).toString());
                IndexTransaction indexTransaction = new IndexTransaction();
                indexTransaction.setTxhash(txByHash.getString("txid"));
                indexTransaction.setTime(txByHash.getLong("time"));
                indexTransaction.setFees(txByHash.getJSONArray("vout").size()>1?txByHash.getJSONArray("vout").getJSONObject(1).getFloat("value").toString():txByHash.getJSONArray("vout").getJSONObject(0).getFloat("value").toString());
                indexTransaction.setPrice("");
                list.add(indexTransaction);
            }
        } else {
            throw new Exception("标记有误");
        }
        return list;
    }

    /**
     * 根据块高度获得块信息
     * @param blockheight
     * @return
     * @throws Throwable
     */
    @GetMapping("/getBlockByHeight")
    private JSONObject getBlockByHeight(int blockheight) throws Throwable {
        String blockHashByHeight = bitCoinJsonRpcLink.getBlockHashByHeight(blockheight);
        JSONObject blockByHash = bitCoinApi.getBlockByHash(blockHashByHeight);
        blockByHash.put("maxheight",maxHeight);
        return blockByHash;
    }

    /**
     * 根据块哈希获得块信息
     * @param blockhash
     * @return
     */
    @GetMapping("/getBlockByHash")
    private JSONObject getBlockByHash(String blockhash){
        JSONObject blockByHash = bitCoinApi.getBlockByHash(blockhash);
        blockByHash.put("maxheight",maxHeight);
        return blockByHash;
    }

    /**
     * 根据交易哈希获得交易信息
     * @param txhash
     * @return
     */
    @GetMapping("/getTxByHash")
    private JSONObject getTxByHash(String txhash){
        return bitCoinApi.getTxByHash(txhash);
    }

}
