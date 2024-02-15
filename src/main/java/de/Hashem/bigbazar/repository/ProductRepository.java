package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/*
* Klasse für ProductRepository
*/

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByName(@Param("name") String name);
}
