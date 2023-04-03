package com.bjqg.document;

import com.bjqg.web.utils.map.CoordinateTransformUtils;
import com.bjqg.web.utils.map.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lbj
 * @date: 2023/1/4 11:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class CoordinateTransformUtilsTest {

    /**
     * 地球坐标系 =>火星坐标系、百度坐标系
     *
     * @return :
     * @author : lbj
     * @description: <地球坐标系 =>火星坐标系、百度坐标系>
     * @date : 2023/1/4 11:24
     */
    @Test
    public void wgsToBdAndGc() {
        //杭州市翠苑
        Point point = new Point(120.12030779999998D, 30.2899834D);
        System.out.println("地球坐标系：" + point);


        Point gcj02 = CoordinateTransformUtils.wgs84ToGcj02(point.getLng(), point.getLat());
        System.out.println("火星坐标系 : " + gcj02);

        Point bd09 = CoordinateTransformUtils.wgs84ToBd09(point.getLng(), point.getLng());
        System.out.println("百度坐标系：" + bd09);
    }

    @Test
    public void gcToBdAndWgs() {
        //火星坐标系中杭州
        Point point = new Point(120.12508930965186D, 30.287704957252362D);
        System.out.println("火星坐标系：" + point);

        Point bd09 = CoordinateTransformUtils.gcj02ToBd09(point.getLng(), point.getLat());
        System.out.println("百度坐标系：" + bd09);

        Point wgs84 = CoordinateTransformUtils.gcj02ToWgs84(point.getLng(), point.getLat());
        System.out.println("地球坐标系：" + wgs84);

        Point wgs84Exactly = CoordinateTransformUtils.gcj02ToWgs84Exactly(point.getLng(), point.getLat());
        System.out.println("实际地球坐标系：" + wgs84Exactly);
    }

    @Test
    public void bdToWgsAndGc() {
        //百度坐标系杭州
        Point point = new Point(120.13072433824077D, 30.29718443786077D);
        System.out.println("百度坐标系：" + point);

        Point gcj02 = CoordinateTransformUtils.bd09ToGcj02(point.getLng(), point.getLng());
        System.out.println("火星坐标系：" + gcj02);

        Point wgs84 = CoordinateTransformUtils.bd09ToWgs84(point.getLng(), point.getLat());
        System.out.println("地球坐标系：" + wgs84);

    }
}
