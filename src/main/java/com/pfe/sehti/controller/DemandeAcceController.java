package com.pfe.sehti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.sehti.entity.DemandeAcce;
import com.pfe.sehti.entity.EtatAcce;
import com.pfe.sehti.service.IDemandeAcceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeAcceController {
@Autowired
IDemandeAcceService demandeserv;
@PostMapping("/savedemande")
public String savedemande(@RequestBody DemandeAcce d) {
	return demandeserv.savedemande(d);
}
@GetMapping("/AccederDemandesAcceEnAttentes/{idPatient}")
public List<DemandeAcce> AccederDemandesAcceEnAttentes(@PathVariable("idPatient")String idPatient){
	return demandeserv.AccederDemandesAcceEnAttentes(idPatient);
}
@PostMapping("/EnvoyerDemandeAcce/Praticien/{idPraticien}/Dossier/{idDossier}")
public String EnvoyerDemandeAcce(@PathVariable("idPraticien") String idPraticien,@PathVariable("idDossier") String idDossier ,@RequestBody DemandeAcce d) {
	return demandeserv.EnvoyerDemandeAcce(idPraticien,idDossier, d);
}

@PutMapping("/AccepterDemandeAcce/{idPatient}")
public String AccepterDemandeAcce(@PathVariable("idPatient")String idPatient) {
	return demandeserv.AccepterDemandeAcce(idPatient);
}
@PutMapping("/RefuserDemandeAcce/{idPatient}")
public String RefuserDemandeAcce(@PathVariable("idPatient") String idPatient) {
	return demandeserv.RefuserDemandeAcce(idPatient);
	
}
@PutMapping("/DonnerAccePermanent/{idPatient}")
public String DonnerAccePermanent(@PathVariable("idPatient") String idPatient) {
	return demandeserv.DonnerAccePermanent(idPatient);
}
@PutMapping("/RevoquerAccePraticien/Praticien/{idPraticien}")
public String RevoquerAccePraticien(@PathVariable("idPraticien")String idPraticien) {
	return demandeserv.RevoquerAccePraticien( idPraticien);
}
@GetMapping("/findByPraticien/{idPraticien}")
public List<DemandeAcce> findByPraticien(@PathVariable("idPraticien") String idPraticien){
	return demandeserv.findByPraticien(idPraticien);
}

@GetMapping("/findByDossierMedical/{idDossier}")
public List<DemandeAcce> findByDossierMedical(@PathVariable("idDossier") String idDossier) {
	return demandeserv.findByDossierMedical(idDossier);
}

}
