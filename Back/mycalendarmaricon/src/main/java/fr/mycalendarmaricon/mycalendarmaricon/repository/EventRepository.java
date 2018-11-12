package fr.mycalendarmaricon.mycalendarmaricon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.mycalendarmaricon.mycalendarmaricon.model.Event;
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	public List<Event> findAll();
}
