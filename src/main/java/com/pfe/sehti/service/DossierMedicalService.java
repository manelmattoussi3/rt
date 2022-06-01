package com.pfe.sehti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.pfe.sehti.entity.DemandeAcce;
import com.pfe.sehti.entity.DossierMedical;
import com.pfe.sehti.entity.EtatAcce;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.LettreConfidentielle;
import com.pfe.sehti.entity.OperationAnalyse;
import com.pfe.sehti.entity.OperationRadio;
import com.pfe.sehti.entity.Ordonance;
import com.pfe.sehti.entity.Patient;
import com.pfe.sehti.repository.IDemandeAcceRepository;
import com.pfe.sehti.repository.IDossierMedicalRepository;
import com.pfe.sehti.repository.IFicheConsultationRepository;
import com.pfe.sehti.repository.ILettreConfidentielleRepository;
import com.pfe.sehti.repository.IPatientRepository;
@Service
public class DossierMedicalService implements IDossierMedicalService{
@Autowired
IDossierMedicalRepository dosrep;
@Autowired
private MongoOperations mongoOperations;
@Autowired
IPatientRepository patrep;
@Autowired
IFicheConsultationRepository ficherep;
@Autowired
IDemandeAcceRepository demanderep;
@Autowired
ILettreConfidentielleRepository lettreRepo;
@Override
public List<DossierMedical> AccederDossierMedical() {
	
	return dosrep.findAll();
}
@Override
//Acceder au dossier de patient de coté patient sans demande
public DossierMedical AccederDossierByPatient(String idPatient) {
	
	return dosrep.findByPatient(idPatient);

	}



@Override
//Acceder au dossier de patient de coté praticien avec demande
public DossierMedical findByPatient(String idDemande) {
	DemandeAcce d =demanderep.findById(idDemande).get();
	if(d.getEtat()==EtatAcce.ACCEPT || d.getEtat()==EtatAcce.PERMANENT) {
	return dosrep.findByPatient(d.getDossierMedical().getPatient().getIdPatient());
	}
	else {
	return null;
	}

}

@Override
public String save(DossierMedical d , String idPatient) {
	Patient p = patrep.findById(idPatient).get();
	d.setPatient(p);
	dosrep.save(d);
	return "dossier est ajoutée et affecté au patient N°"+idPatient;
}

@Override
public DossierMedical findbycodeAcce(String codeAcce) {
	
	return dosrep.findByCodeAcce(codeAcce);
}





@Override
public String PartagerLettreConfidentielle(LettreConfidentielle l ,String idDemande) {
	
	DossierMedical d =this.findByPatient(idDemande);
	List<LettreConfidentielle>lettres=new ArrayList<>();
	lettres.add(l);
	d.setLettres(lettres);
	dosrep.save(d);
	lettreRepo.save(l);
	return "la lettre est partagé avec succé";
}

@Override
public String PartagerFicheConsultation(FicheConsultation fiche,String idDemande) {
	DossierMedical d =this.findByPatient(idDemande);
	
	List<FicheConsultation>fiches=new ArrayList<>();
	fiches.add(fiche);
	d.setFiche(fiches);
	dosrep.save(d);
	ficherep.save(fiche);
	return "la fiche est ajouté dans le dossier avec succé";
}
@Override
public DossierMedical findbyId(String idDossier) {
	
	return dosrep.findById(idDossier).get();
}



}
