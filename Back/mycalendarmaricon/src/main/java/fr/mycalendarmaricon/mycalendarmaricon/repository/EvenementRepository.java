package fr.mycalendarmaricon.mycalendarmaricon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.mycalendarmaricon.mycalendarmaricon.model.Evenement;
@Repository
public interface EvenementRepository extends CrudRepository<Evenement, Long> {
	public List<Evenement> findAll();
}
