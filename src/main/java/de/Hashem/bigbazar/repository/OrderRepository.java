package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Order;
import org.springframework.data.repository.CrudRepository;

/*
* Klasse für OrderRepository
* brauchen wir das nicht
*/

public interface OrderRepository extends CrudRepository<Order, Long> {
}
