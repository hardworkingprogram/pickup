package org.example.pojo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;

public class GaodeGeocodingUtil {
    private static final String API_KEY = "8f2c257691c38cc67dbac9c22a1c33da"; // 生产环境从配置文件读取
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(java.time.Duration.ofSeconds(5)) // 连接超时
            .build();

    /**
     * 地址转经纬度（串行版本，无并发）
     * @param address 完整地址（如："北京市朝阳区望京SOHO"）
     * @param city 可选城市（提高精度，如："北京市"）
     * @return 经纬度数组 [longitude, latitude]，失败返回null
     */
    public static double[] addressToLngLat(String address, String city) {
        try {
            // 构建请求URL（带URLEncoding）
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String url = String.format(
                    "https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s&city=%s",
                    API_KEY, address, city
            );
            System.out.println("请求地址：" + url);
            // 构建请求（带响应超时）
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(java.time.Duration.ofSeconds(10)) // 整体请求超时
                    .build();

            // 每执行三次查询后，暂停一会再继续
            Thread.sleep(1000);
            // 同步阻塞发送（天然串行）
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 处理响应
            return handleResponse(response.body());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            System.err.println("地址解析被中断：" + address);
            return null;
        } catch (Exception e) {
            System.err.printf("地址解析失败：%s | 原因：%s%n", address, e.getMessage());
            return null;
        }
    }


    private static double[] handleResponse(String responseBody) {
        try {
            System.out.println("响应内容：" + responseBody);
            JSONObject json = new JSONObject(responseBody);
            if (!"1".equals(json.getString("status"))) {
                System.err.println("高德API错误：" + json.getString("info"));
                return null;
            }

            JSONArray geocodes = json.getJSONArray("geocodes");
            if (geocodes.isEmpty()) {
                System.err.println("无解析结果：" + responseBody);
                return null;
            }

            String location = geocodes.getJSONObject(0).getString("location");
            System.out.println("解析结果：" + location);
            return parseLocation(location);

        } catch (Exception e) {
            System.err.printf("响应解析失败：%s | 原因：%s%n", responseBody, e.getMessage());
            return null;
        }
    }

    private static double[] parseLocation(String location) {
        try {
            String[] parts = location.split(",");
            return new double[]{
                    Double.parseDouble(parts[0]),  // 经度
                    Double.parseDouble(parts[1])   // 纬度
            };
        } catch (Exception e) {
            System.err.printf("坐标解析失败：%s | 原因：%s%n", location, e.getMessage());
            return null;
        }
    }

    // 无城市参数的重载方法
    public static double[] addressToLngLat(String address) {
        return addressToLngLat(address, "");
    }
}