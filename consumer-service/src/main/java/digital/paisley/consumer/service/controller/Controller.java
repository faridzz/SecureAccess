package digital.paisley.consumer.service.controller;

import digital.paisley.consumer.service.service.ConsumerService;
import digital.paisley.consumer.service.model.dto.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumer")
public class Controller {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public List<Consumer> getConsumers() {
        return consumerService.getAllConsumers();
    }

    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(consumerService.createConsumer(consumer));
    }
}
