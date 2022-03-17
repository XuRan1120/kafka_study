package com.xuran.kafka_study.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author XuRan
 * @Date 2022/3/17 11:29
 * @Version 1.0
 * @Description
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {
     private long successNum = 0L;
     private long errorNum = 0L;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null){
            successNum ++;
        }else{
            errorNum ++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功："+successNum);
        System.out.println("失败："+errorNum);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
