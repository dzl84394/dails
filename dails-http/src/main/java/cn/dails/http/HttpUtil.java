package cn.dails.http;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    private static final OkHttpClient client;

    static {
        // 初始化 OkHttpClient，设置超时时间
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 连接超时
                .readTimeout(10, TimeUnit.SECONDS)    // 读取超时
                .writeTimeout(10, TimeUnit.SECONDS)   // 写入超时
                .build();
    }

    /**
     * 发送 GET 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return 响应结果
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        Request request = buildRequest(url, headers, null, "GET");
        return executeRequest(request);
    }

    /**
     * 发送 POST 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param body    请求体（JSON 格式）
     * @return 响应结果
     */
    public static String post(String url, Map<String, String> headers, String body) throws IOException {
        Request request = buildRequest(url, headers, body, "POST");
        return executeRequest(request);
    }

    /**
     * 发送 PUT 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param body    请求体（JSON 格式）
     * @return 响应结果
     */
    public static String put(String url, Map<String, String> headers, String body) throws IOException {
        Request request = buildRequest(url, headers, body, "PUT");
        return executeRequest(request);
    }

    /**
     * 发送 DELETE 请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return 响应结果
     */
    public static String delete(String url, Map<String, String> headers) throws IOException {
        Request request = buildRequest(url, headers, null, "DELETE");
        return executeRequest(request);
    }

    /**
     * 构建请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param body    请求体
     * @param method  请求方法
     * @return 请求对象
     */
    private static Request buildRequest(String url, Map<String, String> headers, String body, String method) {
        Request.Builder builder = new Request.Builder()
                .url(url);

        // 添加请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置请求体和请求方法
        if (body != null) {
            RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json"));
            builder.method(method, requestBody);
        } else {
            builder.method(method, null);
        }

        return builder.build();
    }

    /**
     * 执行请求
     *
     * @param request 请求对象
     * @return 响应结果
     */
    private static String executeRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            return response.body().string();
        }
    }
}
