package digital.paisley.consumer.service.service;

import digital.paisley.consumer.service.model.dto.Consumer;
import digital.paisley.consumer.service.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer createConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }
}
