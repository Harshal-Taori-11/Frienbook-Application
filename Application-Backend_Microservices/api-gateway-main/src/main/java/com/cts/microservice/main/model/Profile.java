package com.cts.microservice.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;

	@Column
	private String phonenumber;
	@Column
	private String aboutme;
	@Column
	private String avatar;
	@Column
	private String background;

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	// oneToOne Relationship with UserID
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private UserCredential user;

	@Override
	public String toString() {
		return String.format("username: %s, avatar: %s", username, avatar);
	}
}