package in.co.homecredit.config;


import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConfluentKafkaProducerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${sasl.jaas.config}")
    private String config;

    @Value("${sasl.mechanism}")
    private String mechanism;

    @Value("${security.protocol}")
    private String protocol;

    @Value("${basic.auth.credentials.source}")
    private String credentialsource;

    @Value("${basic.auth.user.info}")
    private String userInfo;

    @Value("${schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${producer.kafka.topic.timeout.ms}")
    private String timeout;

    @Value("${producer.kafka.topic.enable.idempotence}")
    private String enableIdempotence;


    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
       /* props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());*/
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        props.put("schema.registry.url", schemaRegistryUrl);
        props.put("security.protocol", protocol);
        props.put("sasl.mechanism", mechanism);
        props.put("sasl.jaas.config", config);
        props.put("basic.auth.credentials.source",credentialsource);
        props.put("basic.auth.user.info",userInfo);
        //props.put("transaction.timeout.ms",timeout);
        props.put("enable.idempotence" , enableIdempotence);

        return props;

    }

    @Bean("confluentProducer")
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean("MsgProducer")
    public KafkaTemplate<String , Object> kafkaTemplate(@Qualifier("confluentProducer") ProducerFactory confluentProducer) {
        return new KafkaTemplate<>(confluentProducer);

    }

}

