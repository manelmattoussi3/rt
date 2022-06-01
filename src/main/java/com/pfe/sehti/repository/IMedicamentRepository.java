package com.pfe.sehti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pfe.sehti.entity.Medicament;

@Repository
public interface IMedicamentRepository extends MongoRepository<Medicament, String>{

}
