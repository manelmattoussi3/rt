package com.pfe.sehti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.sehti.entity.Certificat;
import com.pfe.sehti.entity.Consultation;
import com.pfe.sehti.entity.DossierMedical;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.LettreConfidentielle;
import com.pfe.sehti.entity.OperationAnalyse;
import com.pfe.sehti.entity.OperationRadio;
import com.pfe.sehti.entity.Ordonance;
import com.pfe.sehti.repository.ICertificatRepository;
import com.pfe.sehti.repository.IConsultationRepository;
import com.pfe.sehti.repository.IDossierMedicalRepository;
import com.pfe.sehti.repository.IFicheConsultationRepository;
import com.pfe.sehti.repository.IOperationAnalyseRepository;
import com.pfe.sehti.repository.IOperationRadioRepository;
import com.pfe.sehti.repository.IOrdonanaceRepository;


@Service
public class FicheConsultationService implements IFicheConsultationService{
@Autowired
IFicheConsultationRepository ficherep;
@Autowired
IOperationAnalyseRepository oprep;
@Autowired
IOperationRadioRepository opRadiorep;
@Autowired
IDossierMedicalRepository dosrep;
@Autowired
IConsultationRepository consultationrep;
@Autowired
IOrdonanaceRepository ordonanceRepo;
@Autowired
ICertificatRepository certifRepo;
	@Override
	public String addFiche(FicheConsultation fiche) {
	ficherep.save(fiche);
		return "fiche est ajouté";
	}

	@Override
	public OperationAnalyse getOperationsAnalyse(String idFiche) {
		FicheConsultation fiche =ficherep.findById(idFiche).get();
		OperationAnalyse o =fiche.getAnalyse();
		return o;
	}
	@Override
	public OperationRadio getOperationsRadio(String idFiche) {
		FicheConsultation fiche =ficherep.findById(idFiche).get();
		OperationRadio o =fiche.getRadio();
		return o;
	}

	@Override
	public String AjouterObservation (String observation , String idFiche) {
		FicheConsultation fiche =ficherep.findById(idFiche).get();
		fiche.setObservation(observation);
		ficherep.save(fiche);
		return "Observation est ajoutée avec succé";
	}

	@Override
	public String PartagerAnalyse(OperationAnalyse analyse,String idPatient) {
		
		DossierMedical d =dosrep.findByPatient(idPatient);
		List<OperationAnalyse>analyses=new ArrayList<>();
		analyses.add(analyse);
		List<FicheConsultation> fiches=new ArrayList<>();
		fiches.addAll(d.getFiche());
		for(FicheConsultation fiche:fiches) {
			fiche.setAnalyse(analyse);
			ficherep.save(fiche);
		}
		oprep.save(analyse);
		return "l'analyse est partagé avec succé";
	}

	@Override
	public String PartagerRadio(OperationRadio radio,String idPatient) {
		
		DossierMedical d =dosrep.findByPatient(idPatient);
		List<OperationRadio>radios=new ArrayList<>();
		radios.add(radio);
		List<FicheConsultation> fiches=new ArrayList<>();
		fiches.addAll(d.getFiche());
		for(FicheConsultation fiche:fiches) {
			fiche.setRadio(radio);
			ficherep.save(fiche);
		}
		opRadiorep.save(radio);
		return "l'operation radio est partagé avec succé";
	}

	@Override
	public String PartagerOrdonance(Ordonance ordonance,String idPatient) {
		
		return null;
	}

	@Override
	public List<FicheConsultation> findAllFiches() {
		
		return ficherep.findAll();
	}

	@Override
	public String PartagerConsultation(Consultation consultation,String idPatient,String idOrdonance,String idCertificat) {
		Ordonance o = ordonanceRepo.findById(idOrdonance).get();
		Certificat ce =certifRepo.findById(idCertificat).get();
		DossierMedical d =dosrep.findByPatient(idPatient);
		List<Consultation>consultations=new ArrayList<>();
		consultations.add(consultation);
		List<FicheConsultation> fiches=new ArrayList<>();
		fiches.addAll(d.getFiche());
		for(FicheConsultation fiche:fiches) {
			fiche.setConsultation(consultation);
			ficherep.save(fiche);
		}
		consultation.setCertificat(ce);
		consultation.setOrdonance(o);
		consultationrep.save(consultation);
		return "la consultation est partagé avec succé";
	}

	@Override
	public FicheConsultation findFicheById(String idFiche) {
		
		return ficherep.findById(idFiche).get();
	}

	@Override
	public List<FicheConsultation> getFicheByDossier(String idDossier) {
		DossierMedical d=dosrep.findById(idDossier).get();
		return d.getFiche();
	}

	@Override
	public Consultation getConsultationByFiche(String idFiche) {
	FicheConsultation fiche =ficherep.findById(idFiche).get();	
		return fiche.getConsultation();
	}
}
