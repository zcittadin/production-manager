package zan.ind.productionmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zan.ind.productionmanager.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
