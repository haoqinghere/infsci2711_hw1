package edu.pitt.sis.infsci2711.tutorial.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pitt.sis.infsci2711.tutorial.models.PersonDBModel;
import edu.pitt.sis.infsci2711.tutorial.utils.JdbcUtil;

public class PersonDAO {
public static List<PersonDBModel> findAll() throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM Person";
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				List<PersonDBModel> result = new ArrayList<PersonDBModel>();
				
				while (resultSet.next()) {
					result.add(new PersonDBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));					 
				}
				
				return result;
			}
		}
		
	}
	
	public static PersonDBModel findById(final int id) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = "SELECT * FROM Person where id = " + id;
			try (Statement statement = connection.createStatement()){
				
				ResultSet resultSet = statement.executeQuery(sql);
				
				while (resultSet.next()) {
					return new PersonDBModel(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));					 
				}
				
				return null;
			}
		}
		
	}
	
	public static int save(final PersonDBModel person) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil.getConnection()) {
			String sql = String.format("INSERT INTO Person (firstName, lastName) VALUES ('%s', '%s')", person.getFirstName(), person.getLastName());
			try (Statement statement = connection.createStatement()){
				
				int res = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()){
					person.setId(rs.getInt(1));
				}
				
				return res;
			}
		}
		
	}
	
	
	
}
