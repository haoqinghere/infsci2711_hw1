package edu.pitt.sis.infsci2711.tutorial.business;

import java.sql.SQLException;
import java.util.List;

import edu.pitt.sis.infsci2711.tutorial.dao.PersonDAO;
import edu.pitt.sis.infsci2711.tutorial.models.PersonDBModel;


public class PersonService {
	public List<PersonDBModel> getAll() throws SQLException, Exception {
		List<PersonDBModel> result = PersonDAO.findAll();
		
		return result;
	}
	
	public PersonDBModel findById(final int id) throws SQLException, Exception {
		PersonDBModel result = PersonDAO.findById(id);
		
		return result;
	}
	
	public PersonDBModel add(final PersonDBModel person) throws SQLException, Exception {
		PersonDAO.save(person);
		
		return person;
	}
	
	
}
