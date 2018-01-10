package ru.hh.hibernateexample.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "creation_time", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.creationDate = new Date();
  }

  /** for Hibernate only */
  User() {}  // problem: somebody can use this constructor and create inconsistent instance

  public Integer id() {
    return id;
  }

  // no setId, Hibernate uses reflection to set field

  public String firstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String lastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date creationDate() {
    return creationDate;
  }

  // no setCreationDate

  @Override
  public boolean equals(Object that) {
    if (this == that) return true;
    if (that == null || getClass() != that.getClass()) return false;

    User thatUser = (User) that;
    return Objects.equals(id, thatUser.id)
        && creationDate.equals(thatUser.creationDate)
        && Objects.equals(firstName, thatUser.firstName)
        && Objects.equals(lastName, thatUser.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, creationDate);
  }

  @Override
  public String toString() {
    return String.format("%s{id=%d, firstName='%s', lastName='%s', creationDate='%s'}",
            getClass().getSimpleName(), id, firstName, lastName, creationDate);
  }
}
