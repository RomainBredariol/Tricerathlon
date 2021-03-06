package AccueilGeneral;

import java.time.LocalDate;
import java.util.HashMap;

import BDD.SqlRequete;
import MainApp.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControleurNouveauTriathlon {

	private MainApp main;

	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
	}

	@FXML
	private Button valider;

	// Genre
	@FXML
	private RadioButton amical;
	@FXML
	private RadioButton competition;
	@FXML
	private RadioButton championnat;

	// Type
	@FXML
	private RadioButton xs;
	@FXML
	private RadioButton s;
	@FXML
	private RadioButton m;
	@FXML
	private RadioButton l;
	@FXML
	private RadioButton half;
	@FXML
	private RadioButton xl;
	@FXML
	private RadioButton xxl;

	// Taches
	@FXML
	private CheckBox choix;
	@FXML
	private CheckBox validations;
	@FXML
	private CheckBox autorisations;
	@FXML
	private CheckBox premiersContacts;
	@FXML
	private CheckBox commandesFermes;
	@FXML
	private CheckBox infrastructure;
	@FXML
	private CheckBox rH;
	@FXML
	private CheckBox affectations;
	@FXML
	private CheckBox verifications;
	@FXML
	private CheckBox pub;
	@FXML
	private CheckBox verificationAchatMarchandises;
	@FXML
	private CheckBox majSite;
	@FXML
	private CheckBox ravitaillment;
	@FXML
	private CheckBox relances;
	@FXML
	private CheckBox installationZone;
	@FXML
	private CheckBox gestionVeilleEpreuve;
	@FXML
	private CheckBox Accueil;

	// Renseignement
	@FXML
	private TextField nom;
	@FXML
	private DatePicker dateDebut;
	@FXML
	private TextField ville;

	// ces 2 methodes servent a verifier si 1 seul RadioButton est selectionn�
	private boolean unSeulRadioButtonType() {
		HashMap<String, RadioButton> type = new HashMap<String, RadioButton>();
		type.put("xs", this.xs);
		type.put("s", this.s);
		type.put("m", this.m);
		type.put("l", this.l);
		type.put("half", this.half);
		type.put("xl", this.xl);
		type.put("xxl", this.xxl);
		int nbRadioButtonSelected = 0;
		for (RadioButton rb : type.values()) {
			if (rb.isSelected()) {
				nbRadioButtonSelected++;
			}
		}

		return (nbRadioButtonSelected == 1 || nbRadioButtonSelected == 0);
	}

	private boolean unSeulRadioButtonGenre() {
		HashMap<String, RadioButton> genre = new HashMap<String, RadioButton>();
		genre.put("amical", this.amical);
		genre.put("competition", this.competition);
		genre.put("champ_nat", this.championnat);
		int nbRadioButtonSelected = 0;
		for (RadioButton rb : genre.values()) {
			if (rb.isSelected()) {
				nbRadioButtonSelected++;
			}
		}

		return (nbRadioButtonSelected == 1 || nbRadioButtonSelected == 0);
	}

	@FXML
	private void clicBoutonValider() {
		// REQUETE CONCERNANT LA TABLE TRIATHLON

		// cr�er un element triathlon dans la bdd ssi un seul radioButton selectionn�
		// et que le nom est entr�
		if (this.nom.getText() != null) {
			if (this.unSeulRadioButtonGenre() == false || this.unSeulRadioButtonType() == false) {
				// charge la page d'erreur
				this.main.showErreurChoixTriathlon();
			} else {
				// creation requete
				SqlRequete req = new SqlRequete();
				LocalDate date = null;

				// charge la date selectionn�
				date = this.dateDebut.getValue();
				// charge le nom
				String nom = this.nom.getText();
				// charge la ville
				String lieu = this.ville.getText();

				// championnat
				int championnat = 0;
				if (this.championnat.isSelected()) {
					// =1 si champ_nat est selction� sinon =0
					championnat = 1;
				}

				// req.Connect("delete from triathlon");
				// format xs selectionn�
				if (this.xs.isSelected()) {
					String format = "xs";
					// requete
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.s.isSelected()) {
					String format = "s";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.m.isSelected()) {
					String format = "m";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.l.isSelected()) {
					String format = "l";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.half.isSelected()) {
					String format = "half";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.xl.isSelected()) {
					String format = "xl";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}
				if (this.xxl.isSelected()) {
					String format = "xxl";
					req.Connect("insert into triathlon(nom, date, format, lieu, champ_nat)" + "values('" + nom + "', '"
							+ date.toString() + "', '" + format + "', '" + lieu + "', " + championnat + ");");
				}

				// REQUETE CONCERNANT LA TABLE TACHE
				String id = req.getUneValeurBDD("id_triathlon", "triathlon", "nom='" + this.nom.getText() + "'");
				this.main.conserverIdTriathlon(Integer.parseInt(id));

				if (this.choix.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description) values('Choix(date, lieu...)', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "' , '');");
				}
				if (this.validations.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Validations', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '');");
				}
				if (this.autorisations.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Autorisations' , "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "' , '' );");
				}
				if (this.premiersContacts.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Premiers Contact', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "' , '' );");
				}
				if (this.commandesFermes.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Commandes', "
									+ id + ", '','" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.infrastructure.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Infrastructure', "
									+ id + ", '','" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.rH.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Ressources Humaines', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.affectations.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Affectations', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.verifications.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Verifications', "
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.pub.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Publicite',"
									+ id + ", '','" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.verificationAchatMarchandises.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Verification Achat Marchandises',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.majSite.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Mise a jour du Site',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.ravitaillment.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Ravitaillement',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.relances.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Relances',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.installationZone.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Installation de la Zone',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.gestionVeilleEpreuve.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Gestion veille Epreuve',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}
				if (this.Accueil.isSelected()) {
					req.Connect(
							"insert into tache(nom, id_triathlon, priorite, datedebut, datefin, description)  values('Accueil',"
									+ id + ", '', '" + date.toString() + "','" + date.toString() + "', '' );");
				}

				req.CloseConnexion();
				this.main.showTacheAccueil();
			}

		}
	}

}
