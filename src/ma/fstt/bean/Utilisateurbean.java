package ma.fstt.bean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ma.fstt.persistence.DatabaseOperations;
import ma.fstt.persistence.Produit;
import ma.fstt.persistence.Utilisateur;

@SessionScoped
@ManagedBean(name = "Utilisateurbean")
public class Utilisateurbean {
	
	private int idUser;
	private String nomUser;
	private String prenomUser;
	private String emailUser;
	private String adressUser;
	private String passwordUser;
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int id_client) {
		this.idUser = id_client;
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
	//save_User_function
		public String Save(Utilisateurbean bean) throws IOException {
			return DatabaseOperations.creerUser(bean.nomUser, bean.prenomUser, bean.emailUser, bean.adressUser,bean.passwordUser );
		}
	
	//List_All_Users
		public List<Utilisateur> listUsers() {
			return DatabaseOperations.getAllUsers();
		}

		public String redirectUpdate(int idUser) 
		{
			System.out.println("idUser:  " + idUser);
			Utilisateur u = DatabaseOperations.getUserById(idUser);

			this.nomUser = u.getNom();
			this.prenomUser = u.getPrenom();
			this.emailUser = u.getEmail();
			this.passwordUser = u.getPassword();
			this.adressUser = u.getAdresse();
			

			return "updateUser.xhtml?faces-redirect=true&idUser=" + idUser;
		}

		public String redirectDelete(int idUser) {
			Utilisateur u = DatabaseOperations.getUserById(idUser);
			return DatabaseOperations.deleteUser(u.getId());
		}

		public String redirectAddUser() {
			return "addUser.xhtml?faces-redirect=true";
		}

		public String updateUser(Utilisateurbean bean) throws IOException {
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			Utilisateur user = new Utilisateur(Integer.parseInt(params.get("idUser")), bean.nomUser, bean.prenomUser, bean.emailUser,bean.adressUser, bean.passwordUser);
			return DatabaseOperations.updateUser(user);
		}
		

		public String delete(int idUser) {
			return DatabaseOperations.deleteUser(idUser);
		}

//		// Pour récupérer le id passé dans l'url
		public int setUser(int idUser) {
			this.idUser = DatabaseOperations.getUserById(idUser).getId();
			this.nomUser = DatabaseOperations.getUserById(idUser).getNom();
			return this.idUser;
		}



}
