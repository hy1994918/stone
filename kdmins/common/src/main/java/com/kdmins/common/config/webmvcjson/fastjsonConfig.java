package com.kdmins.common.config.webmvcjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class fastjsonConfig {
    @Bean(name="fastConverter")
    public HttpMessageConverter fastConverter() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,          //结果格式化
                SerializerFeature.WriteNullStringAsEmpty,   //字符串null返回空字符串
                //SerializerFeature.WriteNullNumberAsZero,    //数值类型为0
                SerializerFeature.WriteNullListAsEmpty,     //空字段保留
                SerializerFeature.WriteDateUseDateFormat,   //时间正常
                SerializerFeature.WriteMapNullValue);      //输出为null 的字段
        //fastJsonConfig.setSerializeFilters(new FastJsonValueFilter());
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        //2-1 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        MappingJackson2HttpMessageConverter MappingJackson2HttpMessageConverter;
        return fastConverter;
    }
}
