package digital.paisley.producer.service.controller;

import digital.paisley.producer.service.dto.Producer;
import digital.paisley.producer.service.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    // Create a new Producer
    @PostMapping
    public ResponseEntity<Producer> createProducer(@RequestBody Producer producer) {
        Producer createdProducer = producerService.createProducer(producer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducer);
    }

    // Get all Producers
    @GetMapping
    public ResponseEntity<List<Producer>> getAllProducers() {
        List<Producer> producers = producerService.getAllProducers();
        return ResponseEntity.ok(producers);
    }

    // Get Producer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id) {
        Producer producer = producerService.getProducerById(id);
        return ResponseEntity.ok(producer);
    }

    // Get Producers by Name
    @GetMapping("/search")
    public ResponseEntity<List<Producer>> searchProducersByName(@RequestParam String name) {
        List<Producer> producers = producerService.getProducersByName(name);
        return ResponseEntity.ok(producers);
    }

    // Update Producer
    @PutMapping("/{id}")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long id, @RequestBody Producer producer) {
        Producer updatedProducer = producerService.updateProducer(id, producer);
        return ResponseEntity.ok(updatedProducer);
    }

    // Delete Producer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
        return ResponseEntity.noContent().build();
    }
}
