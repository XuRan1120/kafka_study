package com.xuran.kafka_study.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;


public class ProducerCallback {

    /*
     * 回掉函数
     * */

    public static void main(String[] args) {


        //因为 生产者对象需要Properties 参数(里面放kafka的参数)
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        //创建一个生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //调用send 方法(每次发一条消息，所以死循环)
        for (int i = 0; i < 1000; i++) {
            producer.send(new ProducerRecord<String, String>("first", i + "", "message-" + i), (metadata, exception) -> {
                        if (exception == null) {
                            System.out.println("success");
                        } else {
                            exception.printStackTrace();
                        }
                    }
            );
        }

        //关闭生产者
        producer.close();

    }

}
