package com.pfe.sehti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.sehti.entity.Certificat;
import com.pfe.sehti.repository.ICertificatRepository;

@Service
public class CertificatService implements ICertificatService{
@Autowired
ICertificatRepository certifRepo;
	@Override
	public String saveCertificat(Certificat ce) {
		certifRepo.save(ce);
		return "certificat est ajoutée avec succé";
	}
	@Override
	public Certificat getCertifById(String idCertif) {
		
		return certifRepo.findById(idCertif).get();
	}

}
