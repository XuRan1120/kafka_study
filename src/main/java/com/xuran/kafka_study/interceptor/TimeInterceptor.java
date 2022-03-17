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
public class TimeInterceptor implements ProducerInterceptor<String,String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {

        return new ProducerRecord<String, String>(producerRecord.topic(),producerRecord.partition(),
               producerRecord.timestamp(), producerRecord.key(),System.currentTimeMillis()+producerRecord.value(),
                producerRecord.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
