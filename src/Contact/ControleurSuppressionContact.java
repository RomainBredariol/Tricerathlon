package Contact;

import java.net.URL;
import java.util.ResourceBundle;

import Accueil.MainApp;
import BDD.SqlRequete;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurSuppressionContact {

	@FXML
	private Button boutonAnnuler;
	
	@FXML
	private Button boutonSupprimer;
	
	private Stage fenetre;
	
	private MainApp main;
	
	public void setMainApp(MainApp main){
		this.main=main;
	}
	
	@FXML
	private void clicBoutonAnnuler() {
		this.fenetre.close();
	}
	
	@FXML
	private void clicBoutonConfirmer() {
		SqlRequete req= new SqlRequete();
		req.Connect("delete from benevoles where id_benevoles="+this.main.getValeurAConserver());
		req.CloseConnexion();
		this.fenetre.close();
		this.main.showContact("accueil");
	}

	

	public void setfenetre(Stage fenetreConfirmation) {
		this.fenetre=fenetreConfirmation;
	}
}
