package com.brucepang.brucepangapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.brucepang.brucepangapiclientsdk.model.User;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.brucepang.brucepangapiclientsdk.utils.SignUtils.genSign;

/**
 * 调用第三方接口的客户端
 * @author BrucePang
 */
public class BrucePangApiClient {

    private static final String API_URL = "http://localhost:8223/api/name/";

    private String accessKey;
    private String secretKey;

    public BrucePangApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get(API_URL, paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post(API_URL, paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String,String> getHeaderMap(String body) {
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        // secretKey一定不能直接发送给后端，否则会被破解
//        headerMap.put("secretKey", secretKey);
        headerMap.put("nonce", RandomUtil.randomNumbers(5)); // 生成100以内的随机数
        headerMap.put("body", body);
        headerMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headerMap.put("sign", genSign(body, secretKey));
        return headerMap;
    }



    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(API_URL + "user")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus()); // 响应的状态码
        String result = httpResponse.body();// 响应内容
        System.out.println(result);
        return result;
    }
}
