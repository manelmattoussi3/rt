package com.pfe.sehti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pfe.sehti.entity.FicheConsultation;

@Repository
public interface IFicheConsultationRepository extends MongoRepository<FicheConsultation, String>{

}
