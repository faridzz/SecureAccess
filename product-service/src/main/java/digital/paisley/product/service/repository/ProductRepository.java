package digital.paisley.product.service.repository;

import digital.paisley.product.service.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find Products by name
    List<Product> findByName(String name);

    // Find Products by Producer ID
    List<Product> findByProducerId(Long producerId);

    // Find Products with price greater than or equal to a value
    List<Product> findByPriceGreaterThanEqual(Double price);

    // Delete Product with producer ID
      int deleteProductByProducerId(Long producerId);
}
