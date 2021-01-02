package com.kdmins.common.commonInterface;

import com.alibaba.fastjson.JSONObject;
import com.kdmins.common.pojo.websocketData;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class getServerTime implements websocketDeal{
    String name="getServerTime";
    @Override
    public Object doSomeTing(Object object) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    @Override
    public String getName() {
        return name;
    }
}
