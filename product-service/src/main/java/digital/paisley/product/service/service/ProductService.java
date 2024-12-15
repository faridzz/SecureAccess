package digital.paisley.product.service.service;

import digital.paisley.product.service.client.ProducerClient;
import digital.paisley.product.service.dto.Product;
import digital.paisley.product.service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProducerClient producerClient; // Feign Client

    // Create a Product for a Producer
    @Transactional
    public Product createProduct(Long producerId, Product product) {
        // Call Producer microservice to validate Producer existence
        producerClient.validateProducerExists(producerId);

        product.setProducerId(producerId); // Set Producer ID instead of entity
        return productRepository.save(product);
    }

    // Get all Products
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product by ID
    @Transactional
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    // Get Products by Producer ID
    @Transactional
    public List<Product> getProductsByProducerId(Long producerId) {
        return productRepository.findByProducerId(producerId);
    }

    // Get Products with price greater than or equal to a value
    @Transactional
    public List<Product> getProductsByPrice(Double minPrice) {
        return productRepository.findByPriceGreaterThanEqual(minPrice);
    }

    // Update Product
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    // Delete Product by ID
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public int deleteProductByProducerId(Long id) {
        return productRepository.deleteProductByProducerId(id);
    }
}
