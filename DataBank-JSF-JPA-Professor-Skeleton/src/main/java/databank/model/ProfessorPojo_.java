package databank.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-07-09T21:54:21.313-0400")
@StaticMetamodel(ProfessorPojo.class)
public class ProfessorPojo_ {
	public static volatile SingularAttribute<ProfessorPojo, Boolean> editable;
	public static volatile SingularAttribute<ProfessorPojo, Integer> id;
	public static volatile SingularAttribute<ProfessorPojo, String> lastName;
	public static volatile SingularAttribute<ProfessorPojo, String> firstName;
	public static volatile SingularAttribute<ProfessorPojo, String> email;
	public static volatile SingularAttribute<ProfessorPojo, String> phoneNumber;
	public static volatile SingularAttribute<ProfessorPojo, String> degree;
	public static volatile SingularAttribute<ProfessorPojo, String> major;
	public static volatile SingularAttribute<ProfessorPojo, LocalDateTime> created;
	public static volatile SingularAttribute<ProfessorPojo, LocalDateTime> updated;
	public static volatile SingularAttribute<ProfessorPojo, Integer> version;
}
