package com.pfe.sehti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.sehti.entity.Certificat;
import com.pfe.sehti.service.ICertificatService;

@RestController
public class CertificatController {
@Autowired
ICertificatService certifServ;
@PostMapping("/saveCertificat")
public String saveCertificat(@RequestBody Certificat ce) {
	return certifServ.saveCertificat(ce);
}
@CrossOrigin(origins = "http://localhost:4200")
@GetMapping("/getCertifById/{idCertif}")
public Certificat getCertifById(@PathVariable("idCertif") String idCertif) {
	return certifServ.getCertifById(idCertif);
}
}
