package com.quartz.job;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class HttpTest {
    public static String sendPost(String url)  {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,60000);
        //添加请求头,设置返回json
        postMethod.addRequestHeader("Content-Type","application/json");
        //执行post方法
        String result = null;
        try {
            httpClient.executeMethod(postMethod);
             result = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放连接
            postMethod.releaseConnection();
        }

        return result;

    }

    public static String sendGet(String url)  {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        GetMethod getMethod = new GetMethod(url);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,60000);
        //添加请求头,设置返回json
        getMethod.addRequestHeader("Content-Type","application/json");
        //执行post方法
        String result = null;
        try {
            httpClient.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //释放连接
            getMethod.releaseConnection();
        }

        return result;

    }

    public static void main(String[] args) {

        String url = "http://health-exp.ayyy.cn/ayhpv/hpv/index?code=003Mxh1w36THNW2XVD2w3vRqL72Mxh18&state=1";
        String getRes = sendGet(url);
        System.out.println(getRes);
        String urlPost = "http://health-exp.ayyy.cn/ayhpv/hpv/hpvFilter";
        String postRes = sendPost(urlPost);
        System.out.println(postRes);
    }
}
