package digital.paisley.producer.service.repository;

import digital.paisley.producer.service.dto.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    // Find Producer by email
    Optional<Producer> findByEmail(String email);

    // Find Producers by name containing (for search functionality)
    List<Producer> findByNameContainingIgnoreCase(String name);
}
