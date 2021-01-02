package com.kdmins.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class metricService {
    @Autowired
    org.springframework.boot.actuate.metrics.MetricsEndpoint MetricsEndpoint;
 public    Double   getNum(String item){
    return  MetricsEndpoint.metric(item,null).getMeasurements().get(0).getValue();
    }
}
