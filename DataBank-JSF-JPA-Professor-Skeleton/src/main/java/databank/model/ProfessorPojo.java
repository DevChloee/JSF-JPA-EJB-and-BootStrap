/*****************************************************************
 * File:  ProfessorPojo.java Course materials (23W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TODO 14.1 - Complete the @Entity with correct name.<br>
 * TODO 14.2 - Complete the two @NamedQueries.<br>
 * TODO 15 - Use the correct table name.<br>
 * TODO 16 - Fix the AccessType.<br>
 * TODO 17 - Make ProfessorPojoListener be the listener of this class.  Refer to:
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-listeners.html<br>
 * TODO 18.1 - Add the degree and major fields, add getter and setter, and update toString method to include the degree and major fields.<br>
 * TODO 18.2 - Add the remaining @Basic and @Column to all the fields.<br>
 * TODO 19 - Use @Version on the correct field.  This annotation helps keeping track of what version of entity we are
 * working with.<br>
 * TODO 20 - Dates (LocalDateTime) are to be mapped and 'editable' field is not to be mapped.
 */
@ViewScoped
@Entity(name = "Professor")
@Table(name = "professor", catalog = "databank", schema = "")
@Access(AccessType.PROPERTY)
@EntityListeners( { ProfessorPojoListener.class })
@NamedQuery(name = ProfessorPojo.PROFESSOR_FIND_ALL, query = "SELECT a FROM Professor a")
@NamedQuery(name = ProfessorPojo.PROFESSOR_FIND_ID, query = "SELECT a FROM Professor a WHERE a.id = :id")
public class ProfessorPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PROFESSOR_FIND_ALL = "Professor.findAll";
	public static final String PROFESSOR_FIND_ID = "Professor.findById";


	private int id;

	private String lastName;

	private String firstName;

	private String email;

	private String phoneNumber;
	
	//new
	private String degree;
	private String major;


	private LocalDateTime created;

	private LocalDateTime updated;

	private int version = 1;

	private boolean editable=false;

	
	public ProfessorPojo() {
		super();
	}
	
	//new
	@Transient
	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	/**
	 * @param id new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value for lastName
	 */
	@Basic
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the value for firstName
	 */
	@Basic
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "phone")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	//new
	@Basic
	@Column(name = "degree")
	public String getDegree() {
		return degree;
	}
	
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Basic
	@Column(name = "major")
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	
	

	@Basic
	@Column( name = "created")
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Basic
	@Column( name = "updated")
	public LocalDateTime getUpdated() {
		return updated;
	}
	
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	@Version
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Very important:  Use getter's for member variables because JPA sometimes needs to intercept those calls<br/>
	 * and go to the database to retrieve the value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		// Only include member variables that really contribute to an object's identity
		// i.e. if variables like version/updated/name/etc. change throughout an object's lifecycle,
		// they shouldn't be part of the hashCode calculation
		return prime * result + Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		/* Enhanced instanceof - yeah!
		 * As of JDK 14, no need for additional 'silly' cast:
		    if (animal instanceof Cat) {
		        Cat cat = (Cat) animal;
		        cat.meow();
                // Other class Cat operations ...
            }
         * Technically, 'otherProfessorPojo' is a <i>pattern</i> that becomes an in-scope variable binding.
         * Note:  Need to watch out just-in-case there is already a 'otherProfessorPojo' variable in-scope!
		 */
		if (obj instanceof ProfessorPojo otherProfessorPojo) {
			// See comment (above) in hashCode():  compare using only member variables that are
			// truly part of an object's identity.
			return Objects.equals(this.getId(), otherProfessorPojo.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Professor [id = ").append(getId());
		if (getLastName() != null) {
			builder.append(", ").append("lastName = ").append(getLastName());
		}
		if (getFirstName() != null) {
			builder.append(", ").append("firstName = ").append(getFirstName());
		}
		if (getPhoneNumber() != null) {
			builder.append(", ").append("phoneNumber = ").append(getPhoneNumber());
		}
		if (getEmail() != null) {
			builder.append(", ").append("email = ").append(getEmail());
		}
		if (getCreated() != null) {
			builder.append(", ").append("created = ").append(getCreated());
		}
		if (getUpdated() != null) {
			builder.append(", ").append("updated = ").append(getUpdated());
		}
		
		//new
		if (getDegree() != null) {
			builder.append(", ").append("degree = ").append(getDegree());
		}
		if (getMajor() != null) {
			builder.append(", ").append("major = ").append(getMajor());
		}
		
		builder.append("]");
		return builder.toString();
	}

}
