package digital.paisley.producer.service.service;

import digital.paisley.producer.service.client.ProductClient;
import digital.paisley.producer.service.dto.Producer;
import digital.paisley.producer.service.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ProductClient productClient;

    // Create a new Producer
    @Transactional
    public Producer createProducer(Producer producer) {
        if (producerRepository.findByEmail(producer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Producer with this email already exists.");
        }
        return producerRepository.save(producer);
    }

    // Get all Producers
    @Transactional
    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    // Get Producer by ID
    @Transactional
    public Producer getProducerById(Long id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producer not found with ID: " + id));
    }

    // Get Producers by Name
    @Transactional
    public List<Producer> getProducersByName(String name) {
        return producerRepository.findByNameContainingIgnoreCase(name);
    }

    // Update Producer
    @Transactional
    public Producer updateProducer(Long id, Producer updatedProducer) {
        Producer existingProducer = getProducerById(id);
        existingProducer.setName(updatedProducer.getName());
        existingProducer.setEmail(updatedProducer.getEmail());
        return producerRepository.save(existingProducer);
    }

    // Delete Producer by ID
    @Transactional
    public void deleteProducer(Long id) {
        if (!producerRepository.existsById(id)) {
            throw new IllegalArgumentException("Producer not found with ID: " + id);
        }
        producerRepository.deleteById(id);
        try {
            productClient.deleteProductsByProducerId(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete products for producer ID: " + id, e);
        }

    }

}
