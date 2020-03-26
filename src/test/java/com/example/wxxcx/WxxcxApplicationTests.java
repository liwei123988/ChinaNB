package com.example.wxxcx;

import com.example.wxxcx.util.WeChatRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxxcxApplicationTests {

    @Test
    void contextLoads() {
//        String toKen = WeChatRequest.httpGet(
////                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd046ed8f69c38ff1&secret=8d4470035e567ad48a53a55c36e369ff"
////        ).get("access_token").toString();
////        System.out.println(toKen);
       add();
    }
    public static void del2(){
        //获取ToKen 由于返回的是JSONObject,所以可使用get(key)返回一个Object值  最后使用toString()转换字符串
        String toKen = WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                        "&appid=wxd046ed8f69c38ff1&secret=8d4470035e567ad48a53a55c36e369ff"
        ).get("access_token").toString();



        //最后以Post方式提交至https://api.weixin.qq.com/cgi-bin/menu/create?access_token=<ToKen>
        WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + toKen);
    }

    /**
     * 将菜单全部删除
     */
    public static void delAll(){
        //获取ToKen 由于返回的是JSONObject,所以可使用get(key)返回一个Object值  最后使用toString()转换字符串
        String toKen = WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                        "&appid=wxd046ed8f69c38ff1&secret=8d4470035e567ad48a53a55c36e369ff"
        ).get("access_token").toString();
        //最后以Post方式提交至https://api.weixin.qq.com/cgi-bin/menu/create?access_token=<ToKen>
        WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + toKen);
    }


    /**
     * 菜单的增加
     */
    public static void add(){
        String jsonString =
                "{\"button\":"
                        + "[{\"name\":\"北大青鸟\","
                        + "\"sub_button\":["
                        + "{\"name\":\"色魔\",\"type\":\"view\",\"url\":\"http://www.taobao.com\"},"
                        + "{\"name\":\"大海\",\"type\":\"view\",\"url\":\"http://www.qq.com\"},"
                        + "{\"name\":\"金金\",\"type\":\"view\",\"url\":\"http://www.hao123.com\"}],"
                        + "		\"type\":\"click\"},"
                        + "{\"name\":\"公司介绍\",\"type\":\"click\",\"key\":\"qiqi\"},"
                        + "{\"name\":\"联系客服\",\"type\":\"view\",\"url\":\"http://www.qq.com\"}]"
                        + "}";



        //获取ToKen 由于返回的是JSONObject,所以可使用get(key)返回一个Object值  最后使用toString()转换字符串
        String toKen = WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                        "&appid=wxd046ed8f69c38ff1&secret=8d4470035e567ad48a53a55c36e369ff"
        ).get("access_token").toString();



        //最后以Post方式提交至https://api.weixin.qq.com/cgi-bin/menu/create?access_token=<ToKen>
        WeChatRequest.httpPost(
                "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + toKen,
                jsonString);
    }


    /**
     * 查询出新创建的菜单列表JSON
     */
    public static void query(){
        //获取ToKen 由于返回的是JSONObject,所以可使用get(key)返回一个Object值  最后使用toString()转换字符串
        String toKen = WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                        "&appid=wxd046ed8f69c38ff1&secret=8d4470035e567ad48a53a55c36e369ff"
        ).get("access_token").toString();
        //最后以Post方式提交至https://api.weixin.qq.com/cgi-bin/menu/create?access_token=<ToKen>
        WeChatRequest.httpGet(
                "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + toKen);
    }


}
