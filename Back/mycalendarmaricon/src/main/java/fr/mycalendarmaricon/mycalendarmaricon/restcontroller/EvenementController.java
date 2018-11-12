package fr.mycalendarmaricon.mycalendarmaricon.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.mycalendarmaricon.mycalendarmaricon.model.Evenement;
import fr.mycalendarmaricon.mycalendarmaricon.service.EvenementService;

@RestController
public class EvenementController {
	@Autowired
	private EvenementService evenementService;
	
	@GetMapping("/evenements")
	public ResponseEntity<List<Evenement>> getAllEvenement(){
		List<Evenement> res = evenementService.getAllEvenement();
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}	
}
