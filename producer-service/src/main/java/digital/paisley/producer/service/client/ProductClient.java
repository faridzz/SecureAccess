package digital.paisley.producer.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "product-service")
public interface ProductClient {
    @DeleteMapping("/api/v1/products/producer/{producerId}")
    ResponseEntity<String> deleteProductsByProducerId(@PathVariable("producerId") Long producerId);
}
