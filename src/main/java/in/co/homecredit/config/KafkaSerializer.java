package in.co.homecredit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class KafkaSerializer implements Serializer {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    public static byte[] retVal=null;

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
