package zan.ind.productionmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zan.ind.productionmanager.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {

}
