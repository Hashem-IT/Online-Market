package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/*
* Klasse für ShoppingCartRepository
*/

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    @Query("SELECT s From ShoppingCart s WHERE s.customer.email LIKE :email")
    Optional<ShoppingCart> findByCustomerEmail(@Param("email") String email);
}
