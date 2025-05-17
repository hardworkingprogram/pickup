package org.example.pojo;

public class GeoUtil {

    private static final int EARTH_RADIUS = 6371000; // meters, 地球平均半径

    /**
     * 使用 Haversine 公式计算地球上两个点之间的距离.
     *
     * @param lat1 第一个点的纬度 (度).
     * @param lon1 第一个点的经度 (度).
     * @param lat2 第二个点的纬度 (度).
     * @param lon2 第二个点的经度 (度).
     * @return 距离 (米).
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 将度转换为弧度
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine 公式
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // 距离 (米)
    }
}