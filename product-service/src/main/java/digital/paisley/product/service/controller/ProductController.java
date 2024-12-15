package digital.paisley.product.service.controller;

import digital.paisley.product.service.dto.Product;
import digital.paisley.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a Product for a Producer
    @PostMapping("/producer/{producerId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long producerId, @RequestBody Product product) {
        Product createdProduct = productService.createProduct(producerId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Get all Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Get Products by Producer ID
    @GetMapping("/producer/{producerId}")
    public ResponseEntity<List<Product>> getProductsByProducerId(@PathVariable Long producerId) {
        List<Product> products = productService.getProductsByProducerId(producerId);
        return ResponseEntity.ok(products);
    }

    // Get Products by Minimum Price
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPrice(@RequestParam Double minPrice) {
        List<Product> products = productService.getProductsByPrice(minPrice);
        return ResponseEntity.ok(products);
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Delete all products for a specific producer
    @DeleteMapping("/producer/{producerId}")
    public ResponseEntity<String> deleteProductsByProducerId(@PathVariable Long producerId) {
        int deletedCount = productService.deleteProductByProducerId(producerId);
        return ResponseEntity.ok(deletedCount + " products deleted for producer ID: " + producerId);
    }

}
