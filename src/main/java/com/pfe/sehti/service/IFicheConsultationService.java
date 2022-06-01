package com.pfe.sehti.service;

import java.util.List;

import com.pfe.sehti.entity.Consultation;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.OperationAnalyse;
import com.pfe.sehti.entity.OperationRadio;
import com.pfe.sehti.entity.Ordonance;

public interface IFicheConsultationService {
public String addFiche(FicheConsultation fiche);
public List<FicheConsultation> findAllFiches();
//public String PartagerAnalyseAuFicheConsultation(int idAnalyse,int idFiche);
public OperationAnalyse getOperationsAnalyse(String idFiche);
public OperationRadio getOperationsRadio(String idFiche);
//public String PartagerRadioAuFicheConsultation(int idRadio,int idFiche);
public String AjouterObservation (String observation , String idFiche);
public String PartagerAnalyse(OperationAnalyse analyse,String idPatient);
public String PartagerRadio(OperationRadio radio,String idPatient);
public String PartagerConsultation(Consultation consultation,String idPatient,String idOrdonance,String idCertificat);
public String PartagerOrdonance(Ordonance ordonance,String idPatient);
public FicheConsultation findFicheById(String idFiche);
public List<FicheConsultation> getFicheByDossier(String idDossier);
public Consultation getConsultationByFiche(String idFiche);
}
