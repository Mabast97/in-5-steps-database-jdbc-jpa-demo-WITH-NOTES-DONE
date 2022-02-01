package in5stepsdatabast.databasedemo;

import in5stepsdatabast.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

// It talks to the database and get the values from there
@Repository
public class PersonJdbcDAO {

    @Autowired
    // We want to connect with the database and get values from. So, by using Spring we can obtain it as follows:
    JdbcTemplate  jdbcTemplate; // If you are using Spring, then you have to use JdbcTemplate to execute a query


    // Instead of using { new BeanPropertyRowMapper(Person.class) } for mapping our class to the database table, we can define our custom RowMapper .
    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }


    public List<Person> findAll(){
        /* new BeanPropertyRowMapper(Person.class) : bo awa bakaardet taawaku mapi haryaka la column akaani database aka
        lagal har yaka la field akani Person.class bkaat u rabtyan bkat pekawa.
         */
                                                            // or use your custome created { new PersonRowMapper() }
       return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
    }

    public Person findById(int id) {
        // When we want to query and retrieve a specific object, we well use queryForObject().
        return jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deleteById(int id) {
        // When we want to query and retrieve a specific object, we well use queryForObject().
        return jdbcTemplate.update("delete from person where id=?",
                                        new Object[]{id} );
    }

    public int insert(Person person) {
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
                                        new Object[] {person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime())});
    }

    public int update(Person person) {
        return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
                                        new Object[] {person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId()});
    }



}
