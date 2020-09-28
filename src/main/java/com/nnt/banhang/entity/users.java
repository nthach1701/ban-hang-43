package com.nnt.banhang.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;





@Entity
@Table(name = "users")
public class users implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	
	public Set<role> getRoles() {
		return roles;
	}

	public void setRoles(Set<role> roles) {
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name",nullable = false)
	 private String name;
	 
	 @Column(name = "email", nullable = false)
	    private String email;
	 
	 @Column(name = "password", nullable = false)
	    private String password;
	 
	 @ManyToMany
	    @JoinTable(
	            name = "user_role",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<role> roles;
}
