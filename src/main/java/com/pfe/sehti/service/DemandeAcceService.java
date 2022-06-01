package com.pfe.sehti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.sehti.entity.DemandeAcce;
import com.pfe.sehti.entity.DossierMedical;
import com.pfe.sehti.entity.EtatAcce;
import com.pfe.sehti.entity.Patient;
import com.pfe.sehti.entity.Praticien;
import com.pfe.sehti.repository.IDemandeAcceRepository;
import com.pfe.sehti.repository.IDossierMedicalRepository;
import com.pfe.sehti.repository.IPatientRepository;
import com.pfe.sehti.repository.IPraticienRepository;

@Service
public class DemandeAcceService implements IDemandeAcceService{
	@Autowired
	IDemandeAcceRepository accerepo;
	@Autowired
	IPatientRepository patientrep;
	@Autowired
	IPraticienRepository praticienrep;
	@Autowired
	IDossierMedicalRepository dosrep;
	@Override
	public List<DemandeAcce> findByPraticien(String idPraticien) {

		return accerepo.findByPraticien(idPraticien);
	}
	
	@Override
	public List<DemandeAcce> AccederDemandesAcceEnAttentes(String idPatient) {
		
		DossierMedical d= dosrep.findByPatient(idPatient);
		List<DemandeAcce>demandes = new ArrayList<>();
		demandes.addAll(d.getDemandesacces());
		for(DemandeAcce de :demandes) {
			if(de.getEtat()==EtatAcce.EnAttente) {
				return d.getDemandesacces();
			}
		}
		return null;
	}
	@Override
	public String savedemande(DemandeAcce d) {
		accerepo.save(d);
		return "demande est ajouté";
	}
	@Override
	public String EnvoyerDemandeAcce(String idPraticien,String idDossier ,DemandeAcce d) {
		DossierMedical dossier =dosrep.findById(idDossier).get();
		Praticien praticien =praticienrep.findById(idPraticien).get();
		//List<Praticien>praticiens = new ArrayList<>();
		//praticiens.add(praticien);
		d.setPraticien(praticien);
		d.setDossierMedical(dossier);
		d.setEtat(EtatAcce.EnAttente);
		List<DemandeAcce>demandes = new ArrayList<>();
		
		accerepo.save(d);
		demandes.add(d);
		dossier.setDemandesacces(demandes);
		dosrep.save(dossier);
		return "la demande est envoyé avec succé";
	}
	@Override
	public String AccepterDemandeAcce(String idPatient) {
		DossierMedical d= dosrep.findByPatient(idPatient);
		List<DemandeAcce>demandes = new ArrayList<>();
		demandes.addAll(d.getDemandesacces());
		for(DemandeAcce de :demandes) {
		de.setEtat(EtatAcce.ACCEPT);
		accerepo.save(de);
		}
		return "la demande est accepté";
	}
	@Override
	public String RefuserDemandeAcce(String idPatient) {
		DossierMedical d= dosrep.findByPatient(idPatient);
		List<DemandeAcce>demandes = new ArrayList<>();
		demandes.addAll(d.getDemandesacces());
		for(DemandeAcce de :demandes) {
		de.setEtat(EtatAcce.REFUSE);
		accerepo.save(de);
		}
		return "la demande est refusé";
	}
	@Override
	public String DonnerAccePermanent(String idPatient) {
		DossierMedical d= dosrep.findByPatient(idPatient);
		List<DemandeAcce>demandes = new ArrayList<>();
		demandes.addAll(d.getDemandesacces());
		for(DemandeAcce de :demandes) {
		de.setEtat(EtatAcce.PERMANENT);
		accerepo.save(de);
		}
		return "la demande est permanent";
	}
	@Override
	public String RevoquerAccePraticien(String idPraticien) {

		List<DemandeAcce>demandes =this.findByPraticien(idPraticien);
		for(DemandeAcce d :demandes) {
			if(d.getEtat()==EtatAcce.PERMANENT) {
				d.setEtat(EtatAcce.ANNULE);
				accerepo.save(d);
			}
		}
		return "l'accé de praticien est revoqué";
	}
	@Override
	public List<DemandeAcce> findByDossierMedical(String idDossier) {
		
		return accerepo.findByDossierMedical(idDossier);
	}



}
