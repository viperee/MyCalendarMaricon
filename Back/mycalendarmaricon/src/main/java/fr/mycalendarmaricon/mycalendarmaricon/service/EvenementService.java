package fr.mycalendarmaricon.mycalendarmaricon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mycalendarmaricon.mycalendarmaricon.model.Evenement;
import fr.mycalendarmaricon.mycalendarmaricon.repository.EvenementRepository;

@Service
public class EvenementService {
	@Autowired
	private EvenementRepository evenementRepository;
	
	public List<Evenement> getAllEvenement() {
		return evenementRepository.findAll();
	}
	
}
