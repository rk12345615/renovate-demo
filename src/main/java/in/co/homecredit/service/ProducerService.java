package in.co.homecredit.service;



import in.co.homecredit.dto.LoxonMsgProducerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private in.co.homecredit.producer.LoxonMsgProducer loxonMsgProducer;

    //@Override
    public void sendLoxonReqByKafka(LoxonMsgProducerRequest request){

        try {
            LoxonMsgProducerRequest loxonMsgProducerRequest = new LoxonMsgProducerRequest();
            loxonMsgProducerRequest.setCuid(request.getCuid());
            loxonMsgProducerRequest.setContractNumber(request.getContractNumber());
            loxonMsgProducerRequest.setTaskNameAlias(request.getTaskNameAlias());
            loxonMsgProducerRequest.setTaskNameResult(request.getTaskNameResult());
            loxonMsgProducerRequest.setExpirationDate(request.getExpirationDate());
            loxonMsgProducerRequest.setComment(request.getComment());
            loxonMsgProducer.send(loxonMsgProducerRequest);

        } catch (Exception e) {
            logger.error("Error on create  sendLoxonReqByKafka Exception {} ", e.getMessage());
        }
    }

}
