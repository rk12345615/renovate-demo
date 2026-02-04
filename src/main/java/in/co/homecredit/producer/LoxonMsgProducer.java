package in.co.homecredit.producer;


import com.fakecontact.LoxonRequest;
import in.co.homecredit.dto.JsonPOJOSerializer;
import in.co.homecredit.dto.LoxonMsgProducerRequest;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Service
    public class LoxonMsgProducer {
        private static final Logger logger = LoggerFactory.getLogger(LoxonMsgProducer.class);

        @Autowired
        @Qualifier("MsgProducer")
        private KafkaTemplate<String, Object> kafkaTemplate;

        @Value("${spring.kafka.template.default-topic}")
        private String TOPIC ;

    public void send(LoxonMsgProducerRequest data) {
        try {
            logger.info("sending data='{}' to topic='{}', for accNo : {}", data.toString(), TOPIC, data.getContractNumber());

            //////////////
            Producer<String, LoxonMsgProducerRequest> producer = new KafkaProducer<String, LoxonMsgProducerRequest>(producerConfigs());
            ProducerRecord<String, LoxonMsgProducerRequest> record
                    = new ProducerRecord<>(TOPIC, data.getContractNumber(), data);
            producer.send(record).get();
            producer.close();
            //ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, data.getContractNumber(), data);


            ///////////////

           /* LoxonRequest smsRequest = LoxonRequest.newBuilder()
                    .setCUID(data.getCuid())
                    .setContractNumber(data.getContractNumber())
                    .setTaskNameAlias(data.getTaskNameAlias())
                    .setTaskNameResult(data.getTaskNameResult())
                    .setExpirationDate(data.getExpirationDate())
                    .setComment(data.getComment())
                    .build();

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, data.getContractNumber(), data);*/

           /* ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, data.getContractNumber(), smsRequest);*/

           /* future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable ex) {
                    logger.info("Unable to send Loxon Data: {} due to {}", data, ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    RecordMetadata recordMetadata = result.getRecordMetadata();
                    if (TOPIC.equalsIgnoreCase(recordMetadata.topic())) {
                        logger.info("Sent Sms {} with offset {} ", data, recordMetadata.offset());
                    } else {
                        logger.info("Unable to send Loxon Data");
                    }
                }
            });*/
        } catch (Exception ex) {
            logger.error("Data could not be converted successfully {} - exception - {}", TOPIC, ex.getMessage());
        }

    }

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cpkafka01-in00c1.in.infra:9092,cpkafka02-in00c1.in.infra:9092,cpkafka03-in00c1.in.infra:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPOJOSerializer.class);


        props.put("schema.registry.url", "https://schema-registry-in00c1.in.infra:8081");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"FAKECONTACTS_User\" password=\"FAKECONTACTS_User\";");
        props.put("basic.auth.credentials.source","USER_INFO");
        props.put("basic.auth.user.info","FAKECONTACTS_User:FAKECONTACTS_User");
        //props.put("transaction.timeout.ms",timeout);
        props.put("enable.idempotence" , "false");

        return props;

    }

}
