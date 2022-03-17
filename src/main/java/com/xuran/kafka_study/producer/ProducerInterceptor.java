package com.xuran.kafka_study.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author XuRan
 * @Date 2022/3/17 15:55
 * @Version 1.0
 * @Description
 */
public class ProducerInterceptor {
    public static void main(String[] args) {


        //因为 生产者对象需要Properties 参数(里面放kafka的参数)
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG,"all");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG,1);

        //拦截器链
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("com.xuran.kafka_study.interceptor.TimeInterceptor");
        interceptors.add("com.xuran.kafka_study.interceptor.CounterInterceptor");


        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);

        //创建一个生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //调用send 方法(每次发一条消息，所以死循环)
        for(int i=0;i<1000;i++){
            producer.send(new ProducerRecord<String, String>("first",i+"","message-"+i));
        }

        //关闭生产者
        producer.close();

    }
}
