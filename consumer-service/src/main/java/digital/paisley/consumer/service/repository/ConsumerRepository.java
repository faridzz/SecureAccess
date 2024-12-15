package digital.paisley.consumer.service.repository;

import digital.paisley.consumer.service.model.dto.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
