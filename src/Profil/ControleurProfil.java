package Profil;

import Accueil.MainApp;
import Accueil.modeleAccueil;
import BDD.SqlRequete;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControleurProfil implements ChangeListener {
	
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp=mainApp;
	}
	
	@FXML
	private TextField nom;
	
	@FXML
	private void setNomTextField() {
		this.Nom = nom.getText();
	}
	
	
	
	@FXML
	private TextField prenom;
	
	@FXML
	private void setPrenomTextField() {
		this.Prenom = prenom.getText();
	}
	
	@FXML
	private TextField tel;
	
	@FXML
	private void setTelTextField() {
		this.Tel = tel.getText();
	}
	
	@FXML
	private TextField mail;
	
	@FXML
	private void setMailTextField() {
		this.Mail = mail.getText();
	}
	
	@FXML
	private Button boutonModifier;
	
	@FXML 
	private Button boutonRetour;
	
	private String Nom ;
	private String Prenom;
	private String Tel;
	private String Mail;
	private modeleAccueil modele;
	
	//Action bouton modif
	@FXML
	private void clicBoutonModifier() {
			this.setNomTextField();
			this.setMailTextField();
			this.setPrenomTextField();
			this.setTelTextField();
			SqlRequete sql = new SqlRequete();
			sql.Connect("update benevoles set nom='"+this.Nom+"', prenom='"+this.Prenom
					+"', telephone ='"+this.Tel+"', mail='"+this.Mail+"' where id_benevoles=1;");
			
			sql.Connect("select * from benevoles;");
			sql.CloseConnexion();
			this.mainApp.showProfil();

	}
	
	//Clic bouton retour
	@FXML
	private void clicBoutonRetour() {
		this.mainApp.showAccueilGeneral();
	}
	
	//la m�thode initialize permet de charger ce qu'on lui des qu'on fait appel � cette classe (c'est une sorte de deuxieme constructeur)
	@FXML
	private void initialize() {
		
		SqlRequete requete = new SqlRequete();
		this.nom.setText(requete.getUneValeurBDD("nom", "benevoles","id_benevoles=1"));
		this.prenom.setText(requete.getUneValeurBDD("prenom", "benevoles","id_benevoles=1"));
		this.tel.setText(requete.getUneValeurBDD("telephone", "benevoles","id_benevoles=1"));
		this.mail.setText(requete.getUneValeurBDD("mail", "benevoles","id_benevoles=1"));
		requete.CloseConnexion();
	}
	
	
	//tjr pareil ne pas faire attention
	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
		
	}
	
	

}