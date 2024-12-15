package digital.paisley.product.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "producer-service")
public interface ProducerClient {

    // Check if a producer exists by ID
    @GetMapping("/api/v1/producers/{id}")
    void validateProducerExists(@PathVariable("id") Long id);
}
