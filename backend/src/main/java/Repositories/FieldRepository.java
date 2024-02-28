package Repositories;

import Models.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
    // You can add custom query methods here if needed
}
