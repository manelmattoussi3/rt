package com.pfe.sehti.service;

import java.util.List;

import com.pfe.sehti.entity.LettreConfidentielle;

public interface ILettreConfidentielleService {

public LettreConfidentielle ConsulterLettre(String idLettre ,String idDemande);
public LettreConfidentielle findLettreById(String idLettre);
public List<LettreConfidentielle> findAllLettres();
}
