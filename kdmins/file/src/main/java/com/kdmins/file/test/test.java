package com.kdmins.file.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class test {
    public static void main(String[] args) {

            // 创建动态客户端
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?WSDL");

            Object[] objects = new Object[0];
            try {
                // invoke("方法名",参数1,参数2,参数3....);
                objects = client.invoke("getSupportDataSet");
                System.out.println("返回数据:" + objects[0]);
            } catch (java.lang.Exception e) {
                e.printStackTrace();
            }
        }

}
