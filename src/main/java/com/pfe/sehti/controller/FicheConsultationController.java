package com.pfe.sehti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.sehti.entity.Consultation;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.OperationAnalyse;
import com.pfe.sehti.entity.OperationRadio;
import com.pfe.sehti.service.IFicheConsultationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FicheConsultationController {
	@Autowired
	IFicheConsultationService ficheserv;
	@PostMapping("/addFiche")
	public String addFiche(@RequestBody FicheConsultation fiche) {
		return ficheserv.addFiche(fiche);
	}

	@GetMapping("/getOperationsAnalyse/{idFiche}")
	public OperationAnalyse getOperationsAnalyse(@PathVariable("idFiche") String idFiche) {
		return ficheserv.getOperationsAnalyse(idFiche);
	}
	@GetMapping("/getOperationsRadio/{idFiche}")
	public OperationRadio getOperationsRadio(@PathVariable("idFiche") String idFiche) {
		return ficheserv.getOperationsRadio(idFiche);
	}

	@PutMapping("/AjouterObservation/observation/{observation}/fiche/{idFiche}")
	public String AjouterObservation (@PathVariable("observation") String observation ,@PathVariable("idFiche") String idFiche) {
		return ficheserv.AjouterObservation(observation, idFiche);
	}
	@PostMapping("/PartagerAnalyse/{idPatient}")
	public String PartagerAnalyse(@RequestBody OperationAnalyse analyse,@PathVariable("idPatient") String idPatient) {
		return ficheserv.PartagerAnalyse(analyse,idPatient);
	}
	@PostMapping("/PartagerRadio/{idPatient}")
	public String PartagerRadio(@RequestBody OperationRadio radio,@PathVariable("idPatient") String idPatient) {
		return ficheserv.PartagerRadio(radio, idPatient);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAllFiches")
	public List<FicheConsultation> findAllFiches(){
		return ficheserv.findAllFiches();
	}
	@PostMapping("/PartagerConsultation/patient/{idPatient}/ordonance/{idOrdonance}/certificat/{idCertificat}")
	public String PartagerConsultation(@RequestBody Consultation consultation,@PathVariable("idPatient") String idPatient ,@PathVariable("idOrdonance") String idOrdonance,@PathVariable("idCertificat") String idCertificat) {
		return ficheserv.PartagerConsultation(consultation, idPatient ,idOrdonance ,idCertificat);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findFicheById/{idFiche}")
	public FicheConsultation findFicheById(@PathVariable("idFiche") String idFiche) {
		return ficheserv.findFicheById(idFiche);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getFicheByDossier/{idDossier}")
	public List<FicheConsultation> getFicheByDossier(@PathVariable String idDossier) {
		return ficheserv.getFicheByDossier(idDossier);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getConsultationByFiche/{idFiche}")
	public Consultation getConsultationByFiche(@PathVariable("idFiche") String idFiche) {
		return ficheserv.getConsultationByFiche(idFiche);
	}
}
