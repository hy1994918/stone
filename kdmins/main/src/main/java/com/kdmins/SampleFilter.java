package com.kdmins;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Data;
import org.quartz.core.QuartzSchedulerThread;

@Data
public class SampleFilter extends Filter<ILoggingEvent> {
    String name;
    @Override
    public FilterReply decide(ILoggingEvent event) {
        QuartzSchedulerThread QuartzSchedulerThread;
        if(event.getLoggerName().indexOf(name)>=0){
            return FilterReply.ACCEPT;
        }else{
            return FilterReply.DENY;
        }
    }
}

