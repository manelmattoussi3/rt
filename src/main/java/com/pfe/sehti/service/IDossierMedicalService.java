package com.pfe.sehti.service;

import java.util.List;

import com.pfe.sehti.entity.DossierMedical;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.LettreConfidentielle;
import com.pfe.sehti.entity.OperationAnalyse;
import com.pfe.sehti.entity.OperationRadio;
import com.pfe.sehti.entity.Ordonance;
import com.pfe.sehti.entity.Patient;

public interface IDossierMedicalService {
	public DossierMedical AccederDossierByPatient(String idPatient);
	public List<DossierMedical> AccederDossierMedical();
	public DossierMedical findByPatient(String idDemande);
	public String save(DossierMedical d , String idPatient);
	public DossierMedical findbycodeAcce (String codeAcce);
	public String PartagerLettreConfidentielle(LettreConfidentielle l,String idDemande);
	
	public String PartagerFicheConsultation(FicheConsultation fiche,String idDemande);
	public DossierMedical findbyId (String idDossier);
	
}
