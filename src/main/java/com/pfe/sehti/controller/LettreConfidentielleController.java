package com.pfe.sehti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.sehti.entity.LettreConfidentielle;
import com.pfe.sehti.service.ILettreConfidentielleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LettreConfidentielleController {
@Autowired
ILettreConfidentielleService lettreServ;

@GetMapping("/ConsulterLettre/lettre/{idLettre}/Demande/{idDemande}")
public LettreConfidentielle ConsulterLettre(@PathVariable("idLettre") String idLettre ,@PathVariable("idDemande")String idDemande) {
	return lettreServ.ConsulterLettre(idLettre,idDemande);
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping("/findLettreById/{idLettre}")
public LettreConfidentielle findLettreById(@PathVariable("idLettre") String idLettre) {
	return lettreServ.findLettreById(idLettre);
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping("/findAllLettres")
public List<LettreConfidentielle> findAllLettres() {
	return lettreServ.findAllLettres();
}
}
