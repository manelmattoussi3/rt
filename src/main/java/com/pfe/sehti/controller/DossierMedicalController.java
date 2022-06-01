package com.pfe.sehti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.sehti.entity.DossierMedical;
import com.pfe.sehti.entity.FicheConsultation;
import com.pfe.sehti.entity.LettreConfidentielle;
import com.pfe.sehti.entity.Patient;
import com.pfe.sehti.repository.IDossierMedicalRepository;
import com.pfe.sehti.service.IDossierMedicalService;
import com.pfe.sehti.service.IPatientService;

@RestController
public class DossierMedicalController {
	@Autowired
	IDossierMedicalService dosServ;
	@Autowired
	IPatientService patserv;
	@PostMapping("/save/Patient/{idPatient}")
	public ResponseEntity<String> save(@RequestBody DossierMedical d ,@PathVariable("idPatient") String idPatient){
		dosServ.save(d,idPatient);
		return new ResponseEntity<String>("Data inserted",HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAll")
	public List<DossierMedical> AccederDossierMedical() {
		return dosServ.AccederDossierMedical();
	}
	@GetMapping("/AccederDossierByPatient/{idPatient}")
	public DossierMedical AccederDossierByPatient(@PathVariable("idPatient") String idPatient) {
		return dosServ.AccederDossierByPatient(idPatient);
	}
	@GetMapping(value = "AccederDossierPatient/Demande/{idDemande}")

	public DossierMedical AccederDossierPatient(@PathVariable("idDemande")String idDemande) {
		//Patient p=patserv.getPatientById(idpa);
		return dosServ.findByPatient(idDemande);
	}
	@GetMapping(value = "findbycodeAcce/{codeAcce}")
	public DossierMedical findbycodeAcce(@PathVariable("codeAcce") String codeAcce) {
		return dosServ.findbycodeAcce(codeAcce);
	}

	
	@PostMapping("/PartagerLettreConfidentielle/Demande/{idDemande}")
	public String PartagerLettreConfidentielle(@RequestBody LettreConfidentielle l ,@PathVariable("idDemande") String idDemande) {
		return dosServ.PartagerLettreConfidentielle(l, idDemande);
	}
	@PostMapping("/PartagerFicheConsultation/Demande/{idDemande}")
	public String PartagerFicheConsultation(@RequestBody FicheConsultation fiche,@PathVariable("idDemande") String idDemande) {
		return dosServ.PartagerFicheConsultation(fiche, idDemande);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findbyId/{idDossier}")
	public DossierMedical findbyId(@PathVariable("idDossier") String idDossier) {
		return dosServ.findbyId(idDossier);
	}
}
