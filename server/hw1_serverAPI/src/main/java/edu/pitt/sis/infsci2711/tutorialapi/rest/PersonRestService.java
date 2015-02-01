package edu.pitt.sis.infsci2711.tutorialapi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pitt.sis.infsci2711.tutorial.business.PersonService;
import edu.pitt.sis.infsci2711.tutorial.models.PersonDBModel;
import edu.pitt.sis.infsci2711.tutorialapi.viewModels.Person;

@Path("Person/")

public class PersonRestService {
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response allPersons() {
		
		PersonService personService = new PersonService();
		
		List<PersonDBModel> personsDB;
		try {
			personsDB = personService.getAll();
		
			List<Person> persons = convertDbToViewModel(personsDB);
			
			GenericEntity<List<Person>> entity = new GenericEntity<List<Person>>(persons) {};
			
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response personById(@PathParam("id") final int id) {
		
		PersonService personService = new PersonService();
		
		try {
			PersonDBModel personsDB = personService.findById(id);
		 
			if (personsDB != null) {
				Person person = convertDbToViewModel(personsDB);
			
				return Response.status(200).entity(person).build();
			}
			return Response.status(404).entity("Person not found").build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(final Person person) {
		
		PersonService personService = new PersonService();
		
		try {
			PersonDBModel personsDB = personService.add(convertViewModelToDB(person));
		
			Person personInserted = convertDbToViewModel(personsDB);
			
			return Response.status(200).entity(personInserted).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.status(500).build();
		}
		
	}

	private PersonDBModel convertViewModelToDB(final Person person) {
		return new PersonDBModel(person.getFirstName(), person.getLastName());
	}

	private List<Person> convertDbToViewModel(final List<PersonDBModel> personsDB) {
		List<Person> result = new ArrayList<Person>();
		for(PersonDBModel personDB : personsDB) {
			result.add(convertDbToViewModel(personDB));
		}
		
		return result;
	}
	
	private Person convertDbToViewModel(final PersonDBModel personDB) {
		return new Person(personDB.getId(), personDB.getFirstName(), personDB.getLastName());
	}

}
