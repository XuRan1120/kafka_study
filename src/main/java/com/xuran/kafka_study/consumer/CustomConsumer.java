package com.xuran.kafka_study.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Author XuRan
 * @Date 2022/3/15 11:06
 * @Version 1.0
 * @Description 消费者
 */
public class CustomConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"1120");
        //1、创建1个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //指定消费哪个生产者(topic)
        consumer.subscribe(Arrays.asList("first"));

        //2、调用poll、100毫秒
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("topic = " + record.topic() +"offset = " + record.offset() + " value = "+record.value());
            }
            //同步提交offset、失败就重新提交，一直到提交成功。失败重试机制
            consumer.commitSync();
            //异步提交offset、提交一次，不管成功失败
            consumer.commitAsync();
        }

        //没有关闭

    }
}
