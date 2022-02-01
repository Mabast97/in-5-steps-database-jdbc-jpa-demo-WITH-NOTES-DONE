package in5stepsdatabast.jpa;

import in5stepsdatabast.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class PersonJpaRepository {

    // It manages the entities. All the operations that you are performing in a specific session all stored inside EntityManager
    @PersistenceContext
    EntityManager entityManager ;

    public Person findById(int id) {
        return entityManager.find(Person.class, id); // JPA
    }

}
