package ma.fstt.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int idUser;

	@Column(nullable = true, length = 45)
	private String nomUser;
	
	@Column(nullable = true, length = 45)
	private String prenomUser;
	@Column(nullable = true, length = 45)
	private String emailUser;
	@Column(nullable = true, length = 45)
	private String adressUser;
	@Column(nullable = true, length = 45)
	private String passwordUser;

	public Utilisateur(int id_client, String nom, String prenom, String motDePasse, String email, String adresse) {
		super();
		this.idUser = id_client;
		this.nomUser = nom;
		this.prenomUser = prenom;
		this.emailUser = email;
		this.adressUser = adresse;
		this.passwordUser = motDePasse;

	}

  
	
	

	public String getMotDePasse() {
		return passwordUser;
	}


	public void setMotDePasse(String motDePasse) {
		this.passwordUser = motDePasse;
	}


	public int getId() {
		return idUser;
	}

	public void setId(int id) {
		this.idUser= id;
	}

	public String getNom() {
		return nomUser;
	}

	public void setNom(String nom) {
		this.nomUser = nom;
	}

	public String getPrenom() {
		return prenomUser;
	}

	public void setPrenom(String prenom) {
		this.prenomUser= prenom;
	}

	public String getEmail() {
		return emailUser;
	}

	public void setEmail(String email) {
		this.emailUser = email;
	}

	public String getAdresse() {
		return adressUser;
	}

	public void setAdresse(String adresse) {
		this.adressUser = adresse;
	}
	public String getPassword() {
		return passwordUser;
	}

	public void setPassword(String passwordUser) {
		this.passwordUser = passwordUser;
	}

}
