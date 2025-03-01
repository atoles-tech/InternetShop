package shop.repository;

import org.springframework.data.repository.CrudRepository;

import shop.model.Order;

public interface OrderRepository extends CrudRepository<Order,Integer>{}

