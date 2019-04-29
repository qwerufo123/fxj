package com.fxj.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bitcoin",url = "http://localhost:18332")
public interface BitCoinApi {

    /**
     * 查询块链信息状态
     * @return
     */
    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    /**
     * hash查询交易信息
     * @param phash
     * @return
     */
    @GetMapping("/rest/tx/{hash}.json")
    JSONObject getTxByHash(@PathVariable("hash") String hash);

    /**
     * hash查询块信息
     * @param hash
     * @return
     */
    @GetMapping("/rest/block/{hash}.json")
    JSONObject getBlockByHash(@PathVariable("hash") String hash);

    /**
     * hash查询当前块上方块数量
     * @param hash
     * @return
     */
    @GetMapping("/rest/headers/{count}/{hash}.json")
    JSONArray getHeadersByHash(@PathVariable("count") Integer count, @PathVariable("hash") String hash);

    /**
     * height查询块信息
     * @param height
     * @return
     */
    @GetMapping("/rest/blockhashbyheight/{height}.json")
    JSONObject getBlockByHeight(@PathVariable("height") Integer height);

    /**
     * 查询可交易详细信息
     * @return
     */
    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    /**
     * 查询交易可使用事务
     * @return
     */
    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

}
