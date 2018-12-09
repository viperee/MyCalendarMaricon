package fr.mycalendarmaricon.mycalendarmaricon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.mycalendarmaricon.mycalendarmaricon.model.Colors;

@Repository
public interface ColorsRepository extends CrudRepository<Colors, Long> {

}
