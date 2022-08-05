package com.cts.microservice.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class ImageFile {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column
	private String username;
	@Column
	private String fileName;
	@Column
	private String fileType;
	@Column
	private String fileURL;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private UserCredential user;

	@Lob
	private byte[] data;

	public ImageFile() {
		// TODO Auto-generated constructor stub
	}

	public ImageFile(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	
	@Override
    public String toString() {
        return String.format("File: %s", fileURL);
    }
}
