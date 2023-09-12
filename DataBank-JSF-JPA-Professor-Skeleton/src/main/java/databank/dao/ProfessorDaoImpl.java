/*****************************************************************
 * File:  ProfessorDaoImpl.java Course materials (23W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.ejb.ProfessorService;
import databank.model.ProfessorPojo;


/**
 * Description:  Implements the C-R-U-D API for the database
 * 
 * TODO 01 - Some components are managed by CDI.<br>
 * TODO 02 - Methods which perform DML need @Transactional annotation.<br>
 * TODO 03 - Fix the syntax errors to correct methods. <br>
 * TODO 04 - Refactor this class.  Move all the method bodies and EntityManager to a new service class (e.g. ProfessorService) which is a
 * singleton (EJB).<br>
 * TODO 05 - Inject the service class using EJB.<br>
 * TODO 06 - Call all the methods of service class from each appropriate method here.
 */
@Named
@ApplicationScoped
public class ProfessorDaoImpl implements ProfessorDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	@EJB
	protected ProfessorService professorService;
	
	
	@Override
	public List< ProfessorPojo> readAllProfessors() {
		return professorService.findAllProfessors();
	}

	public ProfessorPojo createProfessor(ProfessorPojo professor) {
		return professorService.persistProfessor(professor);
	}
	
	public ProfessorPojo readProfessorById(int professorId) {
		return professorService.findProfessorById(professorId);
	}
	
	public ProfessorPojo updateProfessor(ProfessorPojo professor) {
		return professorService.mergeProfessor(professor);
	}
	
	public void deleteProfessorById(int professorId) {
		professorService.removeProfessor(professorId);
	}
	

}
