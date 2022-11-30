package ma.fstt.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ma.fstt.persistence.Categorie;
import ma.fstt.persistence.DatabaseOperations;
import ma.fstt.persistence.Produit;

@SessionScoped
@ManagedBean(name = "ProduitBean")
public class ProduitBean {
	private int idProd = 0;
	private String nomProd;
	private int quantiteProd;
	private Float puProd;
	private String descProd;
	private Categorie cat;
	private int idc;

	public int getIdc() {
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}
	public int getIdProd() {
		return idProd;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}

	public String getNomProd() {
		return nomProd;
	}

	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}

	public int getQuantiteProd() {
		return quantiteProd;
	}

	public void setQuantiteProd(int quantiteProd) {
		this.quantiteProd = quantiteProd;
	}

	public Float getPuProd() {
		return puProd;
	}

	public void setPuProd(Float puProd) {
		this.puProd = puProd;
	}

	public String getDescProd() {
		return descProd;
	}

	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}


	private List<Categorie> listeCategories = new ArrayList<Categorie>();

//save_product_function
	public String Save(ProduitBean bean) throws IOException {
		return DatabaseOperations.creerProduit(bean.nomProd, bean.quantiteProd, bean.puProd, bean.descProd, cat.getIdCat());
	}
//List_products_where_idCat=id
	public List<Produit> listProduits(int id) {

		this.cat = DatabaseOperations.getCategorieById(id);
		return DatabaseOperations.getAllProduits(id);

	}
//List_All_products
	public List<Produit> listProduits() {
		return DatabaseOperations.getAllProduits();
	}

	public String redirectUpdate(int idProd) 
	{
		System.out.println("idProd:  " + idProd);
		Produit p = DatabaseOperations.getProduitById(idProd);

		this.nomProd = p.getNomProd();
		this.quantiteProd = p.getQuantiteProd();
		this.puProd = p.getPuProd();
		this.descProd = p.getDescProd();
		this.cat = p.getCat();

		return "updateProduit.xhtml?faces-redirect=true&idProd=" + idProd;
	}

	public String redirectDelete(int idProd) {
		Produit p = DatabaseOperations.getProduitById(idProd);
		return DatabaseOperations.deleteProduit(p.getIdProd());
	}

	public String redirectAddProd() {
		return "addProduit.xhtml?faces-redirect=true";
	}

	public List listCategories() {
		return DatabaseOperations.getAllCategories();
	}

	public String updateProduit(ProduitBean bean) throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		Produit prod = new Produit(Integer.parseInt(params.get("idProd")), bean.nomProd, bean.quantiteProd, bean.puProd,
				bean.descProd, bean.cat.getIdCat());
		return DatabaseOperations.updateProduit(prod);
	}
	

	public String delete(int idProd) {
		return DatabaseOperations.deleteProduit(idProd);
	}

//	// Pour r�cup�rer le id pass� dans l'url
	public int setProduit(int idProd) {
		this.idProd = DatabaseOperations.getProduitById(idProd).getIdProd();
		this.nomProd = DatabaseOperations.getProduitById(idProd).getNomProd();
		return this.idProd;
	}


	
}
