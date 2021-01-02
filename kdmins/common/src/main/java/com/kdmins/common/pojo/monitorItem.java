package com.kdmins.common.pojo;

import lombok.Data;

@Data
public class monitorItem {
    Double systemCpuUse;
    Double processCpuUse;
    Long LoadAverage;
    Long tolAverage;

}
