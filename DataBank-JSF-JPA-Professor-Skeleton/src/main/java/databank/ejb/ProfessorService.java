package databank.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.model.ProfessorPojo;
/**
 * Stateless Session Bean - InventoryService
 */
//@Stateless
@Singleton
public class ProfessorService implements Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	//Get the log4j2 logger for this class
	private static final Logger LOG = LogManager.getLogger();

	@PersistenceContext(name = "PU_DataBank")
	protected EntityManager em;
	
	/**
	 * Default constructor.
	 */
	public ProfessorService() {
	}


	public List<ProfessorPojo> findAllProfessors() {
		LOG.debug("reading all professors");
		//Use the named JPQL query from the ProfessorPojo class to grab all the professors
		TypedQuery<ProfessorPojo> allProfessorsQuery = em.createNamedQuery(ProfessorPojo.PROFESSOR_FIND_ALL, ProfessorPojo.class);
		//Execute the query and return the result/s.
		return allProfessorsQuery.getResultList();
	}


	@Transactional
	public ProfessorPojo persistProfessor(ProfessorPojo professor) {
		LOG.debug("creating a professor = {}", professor);
		em.persist(professor);
		return professor;
	}


	public ProfessorPojo findProfessorById(int professorId) {
		LOG.debug("read a specific professor = {}", professorId);
		return em.find(ProfessorPojo.class, professorId);
	}


	@Transactional
	public ProfessorPojo mergeProfessor(ProfessorPojo professorWithUpdates) {
		LOG.debug("updating a specific professor = {}", professorWithUpdates);
		
		try {
			ProfessorPojo mergedProfessorPojo = em.merge(professorWithUpdates);
			return mergedProfessorPojo;
			
		} catch (OptimisticLockException e) {
			return null;
		}


	}


	@Transactional
	public void removeProfessor(int professorId) {
		LOG.debug("deleting a specific professorID = {}", professorId);
		ProfessorPojo professor = findProfessorById(professorId);
		LOG.debug("deleting a specific professor = {}", professor);
		if (professor != null) {
			em.refresh(professor);
			em.remove(professor);
		}
	}
	
	
	
}

