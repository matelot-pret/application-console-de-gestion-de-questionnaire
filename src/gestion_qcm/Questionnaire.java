package gestion_qcm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Questionnaire {
	private Question[] listeDeQuestions;
	private int nbrQuestions;
	private String nom;
	private LocalDate dateDeCreation;
	private LocalDate dateDeSoumission;
	private LocalDate dateButoir;
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	//pre : constructeur pour initialiser chaque champs d'un questionnaire
	Questionnaire(Question[] tab, String nom, LocalDate creation, LocalDate soumission){
		listeDeQuestions = tab.clone();
		this.nom = nom;
		dateDeCreation = creation;
		dateDeSoumission = soumission;
		dateButoir = dateDeSoumission.plusMonths(1);
	}
	
	/* constructeur permettant de créer un questionnaire avec une liste de question vide,
	*  une date de création initialisé à aujourd'hui. Le constructeur prend en paramètre 
	*  le nombre max de question et le nom du questionnaire
	*/
	Questionnaire(int nbrQuestionsMax, String nom){
		listeDeQuestions = new Question[nbrQuestionsMax];
		this.nom = nom;
		dateDeCreation = LocalDate.now();
	}
	
	/**constructeur permettant de créer un questionnaire avec une liste de question,
	 * une date de création, de soumission et une date butoir déjà initialisé. Le constructeur 
	 * ne prend que le nom du questionnaire en paramètre. 
	 */
	Questionnaire(String nom){
		this.nom = nom;
		String[] listeq1 = {"1/4", "0", "1/2", "3/4", "5/4"};
		QuestionQCM q1 = new QuestionQCM("(1/2)^2 =",  listeq1);
		QuestionOuverte q2 = new QuestionOuverte("Combien de Lundis peut-il y avoir au maximum dans une période de 75 jours consécutifs ?", 2, 11);
		Question[] listeQuestions = {q1, q2};
		nbrQuestions = listeQuestions.length;
		listeDeQuestions = new Question[listeQuestions.length];
		for(int i = 0; i < listeQuestions.length; i++) {
			this.listeDeQuestions[i] = listeQuestions[i];
		}
		dateDeCreation = LocalDate.of(2025, 04, 11);
		dateDeSoumission = dateDeCreation.plusWeeks(1);
		dateButoir = dateDeSoumission.plusMonths(1);
	}
	
	/**pre : q est un objet Question entièrement initialisé et et le questionnaire n'as pas 
	 * encore toutes ses questions
	 * post : l'objet this est modifié en ajoutant q aux questions si this
	 * n'a pas toutes ces questions sinon this n'est pas modifié
	 * resultat : affiche un message si this a déjà toutes ces questions sinon
	 * n'affiche rien
	 */
	public void addQuestion(Question q ) {
		if(this.dateDeSoumission == null) {
			this.listeDeQuestions[nbrQuestions] = q;
			nbrQuestions ++;
			System.out.println("Question ajouté avec succès");
		}
	}
	
	/**pre : this n'a pas encore été soumis
	 * post : si this possède toutes ses questions la date de soumission 
	 * est initialisé à aujourd'hui sinon la date de soumission n'est pas modifié
	 * res : affiche un message si this n'a pas toutes ses questions sinon soumet le questionnaire
	 */
	public void sumitQuestion() {
		if(this.nbrQuestions >= this.listeDeQuestions.length) {
			this.dateDeSoumission = LocalDate.now();
			
		}else {
			System.out.println("Vous ne pouvez pas soumettre le questionnaire tant qu'il n'a pas toutes ces questions.");
		}
	}
	
	/**pre : la date de soumission est déjà initialisé et valide 
	 * post : la date butoir est initialisé à 1 mois après la date de soumission
	  */
	public void modifieDateButoir() {
		this.dateButoir = this.dateDeSoumission.plusMonths(1);
	}
	
	/**pre : this est entiérement initialisé
	 * post : this n'est pas modifié
	 * res : retourne un clone de this 
	 */
	public Questionnaire clone() {
		Question[] copieListe = new Question[this.listeDeQuestions.length];
		
		for(int i = 0; i < copieListe.length; i ++) {		
			copieListe[i] = this.listeDeQuestions[i].clone();
		}
		return new Questionnaire(copieListe, this.nom, this.dateDeCreation, this.dateDeSoumission);
	}
	
	/** pre : listeDeQuestions est entièrement initialisé et ne possède pas de case null,
	 * this est soumis
	 * post : modifie les positions des éléments de listeDeQuestions dans this*/
	public void permutesQuestion() {
		Question temp = this.listeDeQuestions[this.listeDeQuestions.length/2];
		this.listeDeQuestions[this.listeDeQuestions.length/2] = this.listeDeQuestions[0];
		this.listeDeQuestions[0] = temp;
		int j = this.listeDeQuestions.length - 1;
		for(int i = 0; i < j; i ++, j --) {
			temp = this.listeDeQuestions[i];
			this.listeDeQuestions[i] = this.listeDeQuestions[j];
			this.listeDeQuestions[j] = temp;
	
			this.listeDeQuestions[i].permuteProposition();
			this.listeDeQuestions[j].permuteProposition();
		}
	}
	
	/**pre : monFichier est déjà initialisé avec un fichier txt, 
	 * l'objet this est entièrement initialisé et soumis
	 * post : monFichier est modifié, this n'est pas modifié*/
	public void saveQuestionnaire(File monFichier) {
		try {
			FileWriter ecrireFichier = new FileWriter(monFichier);
			System.out.println("Questionnaire soumis avec succès !\nQuestionnaire sauvegardé avec succès à " + monFichier.getAbsolutePath());
			ecrireFichier.write("Nom du questionnaire : " + this.nom + "\n");
			ecrireFichier.write("Nombre de questions : ");
			ecrireFichier.write(String.valueOf(this.listeDeQuestions.length) + "\n");
			ecrireFichier.write("Date de création : " + this.dateDeCreation.toString() + "\n");
			ecrireFichier.write("Date de soumission : " + this.dateDeSoumission.toString() + "\n");
			ecrireFichier.write("Date Butoir : " + this.dateButoir.toString() + "\n");
			for(int i = 0; i < this.listeDeQuestions.length; i++) {
				ecrireFichier.write(" \n");
				ecrireFichier.write("Question " + String.valueOf(i+1) + "\n");
				ecrireFichier.write(this.listeDeQuestions[i].toString());
			}
			ecrireFichier.close();
		}catch(FileNotFoundException e) {
			System.out.println("Le fichier n'a pas pu être ouvert");
		} catch (IOException f) {
			System.out.println("Impossible de lire dans le fichier");
		}
	}
	
	//res : retourne le nomdre de question
	public int getnbrQuestion() {
		return nbrQuestions;
	}
	
	
	
	public Question[] getListeDeQuestions() {
		Question[] copieListe = new Question[this.listeDeQuestions.length];
		
		for(int i = 0; i < listeDeQuestions.length; i ++) {
			copieListe[i] = listeDeQuestions[i].clone();
		}
		return copieListe;
	}
	
	/**
	 * pre : numQ est déjà initialisé et est compris entre 0 et nbrQuestions-1
	 * post : listeDeQuestion, rep et numQ restent inchangés
	 * res : retourne le nombre de points gagnés en répondant à la question numQ, 5 points si réponse
	 * correct et 0 sinon
	 * */
	public int repondre (int numQ, String rep) {
		int point = 0;
			String correct = this.listeDeQuestions[numQ-1].getReponseCorrect().trim().toLowerCase();
			String tentative = rep.toLowerCase().trim();
			if(correct.equals(tentative)) {
				System.out.println("Réponse Correct ! Vous gagnez 5 points");
				point += 5;
			}else {
				System.out.println("Réponse Incorrect ! La bonne réponse est " + this.listeDeQuestions[numQ-1].getReponseCorrect().trim().toLowerCase()
						+ "Vous avez entrer " + rep);
			}
		
		return point;
	}
	
	/** pre : f est un fichier au format texte déjà initialisé, this est un questionnaire soumis
	 * res : retourne un questionnaire initialisé à partir du fichier this
	 * */
	public static Questionnaire lireFichierEtCreerQuestionnaire(File f) {
		String nom = "";
		int nbrQuestionsMax = 0;
		Questionnaire q = null;

		try {
			Scanner clavier = new Scanner(f);
			int index = 0;

			while (clavier.hasNextLine()) {
				String line = clavier.nextLine().trim();

				if (line.startsWith("Nom du questionnaire")) {
					nom = line.split(":", 2)[1].trim();
				} else if (line.startsWith("Nombre de questions")) {
					nbrQuestionsMax = Integer.parseInt(line.split(":", 2)[1].trim());
					q = new Questionnaire(nbrQuestionsMax, nom);
				} else if (q != null && line.startsWith("Date de création")) {
					q.dateDeCreation = LocalDate.parse(line.split(":", 2)[1].trim());
					q.dateDeSoumission = LocalDate.parse(clavier.nextLine().split(":", 2)[1].trim());
					q.dateButoir = LocalDate.parse(clavier.nextLine().split(":", 2)[1].trim());
				} else if (q != null && line.startsWith("Question")) {
					int niveau = clavier.nextLine().length()-2;

					String enonce = clavier.nextLine().trim();

					ArrayList<String> liste = new ArrayList<>();//Je pars du principe qu'on ne connait pas le nombre de 
					String propositionLine = " ";				//proposition à l'avance donc je ne peux pas utiliser un
					String repString = " ";						//tableau statique d'où j'ai cherché si il ya une méthode 
					boolean stop = false;						//pour agrandir un tableau ou bien une sorte de taleau dont
					while (clavier.hasNextLine() && stop == false) {// la taille n'est pas statique et j'ai trouvé l'objet ArrayList
						propositionLine = clavier.nextLine().trim();//Je me suis retrouvé face à un problème et j'ai cherché une solution
						if (propositionLine.startsWith("rep") && propositionLine.contains(":")) {
							repString = propositionLine.split(":", 2)[1].trim();
							stop = true;

						}else if (propositionLine.contains(":")) {
							liste.add(propositionLine.split(":", 2)[1].trim());//split la ligne en deux parties maximum séparé par le : 
						}
					}

					if (!liste.isEmpty()) {
						String[] tab = liste.toArray(new String[0]);
						char rep = repString.charAt(0);

						q.listeDeQuestions[index] = new QuestionQCM(enonce, niveau, tab, rep);
					} else {
						int repNum = Integer.parseInt(repString.trim());
						q.listeDeQuestions[index] = new QuestionOuverte(enonce, niveau, repNum);
					}
					index ++;
				}
			}
			

			clavier.close();
			System.out.println("Questionnaire crée avec succès !");
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas pu être ouvert.");
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
			e.printStackTrace();
		}

		return q;
	}
	
	/**
	 * pre : candidate, min et max sont déjà initialisé
	 * post : candidate, min et max sont déjà initialisé
	 * res : retourne true si candidate est dans l'interval min max sinon retourne false*/
	static boolean getIntWithinBounds(int candidate, int min, int max) {
		
		return candidate >= min && candidate <= max;
	}

}
