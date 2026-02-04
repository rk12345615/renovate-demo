package in.co.homecredit.controller;

import in.co.homecredit.dto.LoxonMsgProducerRequest;
import in.co.homecredit.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LegalLogsController {

    private static final Logger logger = LoggerFactory.getLogger(LegalLogsController.class);

    @Autowired
    private ProducerService producer;

    @PostMapping(value = "/test")
    public void sendMessageToConKafkaTopic(@RequestBody LoxonMsgProducerRequest req) {
        logger.info("In sendMessageToConKafkaTopic method contractCode {} ", req.getContractNumber());
        producer.sendLoxonReqByKafka(req);
    }

}