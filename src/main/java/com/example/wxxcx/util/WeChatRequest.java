package com.example.wxxcx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class WeChatRequest {

    public static String httpPost(String url, String jsonString) {
        PrintWriter out = null;
        BufferedReader in = null;
        String jsonResult = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            /*
             * 浏览器头标
             * Mozilla/4.0 Mozilla基金会 4.0
             * compatible 适合的;兼用的
             * MSIE6.0 微软公司IE浏览器6.0版本
             * Windows NT 5.1 微软公司服务器操作系统
             * SV1=Security Version 1 安全版本1
             */
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //解决乱码
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
//			out = new PrintWriter(conn.getOutputStream());
            out.print(jsonString);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                jsonResult += line;
            }
        } catch (Exception e) {
            System.out.println("==========Post异常==========");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * http Get请求
     * @param url 请求微信服务器Url
     * @return 返回jsonObject结果
     */
    public static JSONObject httpGet(String url) {
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSON.parseObject(strResult);
                System.out.println("strResult=" + strResult);
            } else {
                System.out.println("HTTP响应状态异常：" + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            System.out.println("==========Get异常==========");
            e.printStackTrace();
        }
        return jsonResult;
    }

}
