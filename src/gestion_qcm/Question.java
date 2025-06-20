package gestion_qcm;

abstract class Question {
	protected String enonce;
	protected int niveauDifficulte;
	
	Question(String enon, int difficulte){
		enonce = enon;
		niveauDifficulte = difficulte;
	}

	/**
	 * pre : le niveau de difficulte est déjà initialisé et strictement supérieur à 0 et inférieur ou égale à 3
	 * post : le niveau de difficulte est affiché
	 * res : le niveau de difficulté est affiché dans le format (*), le nombre de * depend du niveau
	 */
	public String afficheDifficulte() {
		String s = " ";
		if(this.niveauDifficulte == 1) {
			s = "(*)";
		}else if(this.niveauDifficulte == 2){
			s = "(**)";
		}else {
			s = "(***)";
		}
		
		return s;
	}
	
	/**
	 * pre : 
	 * post : retourne l'enoncé de la question
	 * res : la chaîne enonce est retournée sans modification
	 */
	public String getEnonce() {
		return enonce;
	}

	/**
	 * pre : enonce est déjà initialisé
	 * post : this.enonce est modifié et recoit enonce
	 */
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	/**
	 * pre : 
	 * post : retourne le niveau de difficulté
	 * res : retourne l'entier niveauDifficulte
	 */
	public int getNiveauDeDifficulte() {
		return niveauDifficulte;
	}
	
	public String[] getListeDeProposition() {
		return null;
	}
	
	//pre : a est déjà initialisé et 2 < a < 3, 
	//post : niveauDeDifficulte est modifié 
	void setNiveauDifficulte(int a) {
		this.niveauDifficulte = a;
	}

	/**
	 * pre : -
	 * post : retourne une chaîne personnalisée de l'objet
	 * res : retourne la difficulté et l'enoncé formatés
	 */
	public String toString() {
	    
		return this.afficheDifficulte() + "\n" + this.enonce + "\n";
	}
	

	/**
	 * pre : l'objet this est entièrement initialisé
	 * post : l'objet this n'est pas modifié
	 * res : retourne un clone de l'objet this
	 */
	public abstract Question clone();
	
	//res: sers juste pouvoir appelé la méthode sur un objet de la classe mère
	public void permuteProposition() {
		
	}
	
	/**
	 * pre : 
	 * post : retourne une chaîne (null ici)
	 */
	public String getReponseCorrect() {
		return null;
	}
	
	/**
	 * res : retourne l’énoncé de la question 
	 * */
	public String retourneEnonce() {
		return this.enonce;
	}
}
class QuestionQCM extends Question{
	private String[] listeDeProposition;
	private char reponseCorrect;
	
	//pré : niveauDeDifficulte ne recois comme valeur que 1 ou 2 ou 3 et rien d'autre
	QuestionQCM(String enon, int difficulte, String[] liste, char reponse){
		super(enon, difficulte);
		reponseCorrect = reponse;
		listeDeProposition = liste.clone();
		
	}
	
	//pre : niveauDeDifficulte est initialisé avec 1, reponseCorrect est initialisé avec la première réponse correct
	QuestionQCM(String enon, String[] liste){
		super(enon, 1);
		listeDeProposition = liste.clone();
		reponseCorrect = 'A';
	}
	
	
	
	//pre : listeDeProposition de l'objet this et de l'objet q sont entierement initialise
	//post : les objets this et q ne sont pas modifié  
	//resultat : retourne true si les elements des tableaux des deux objets sont identiques sinon false
	public boolean egalPosition(QuestionQCM q) {
		boolean equals = true;
		for(int i = 0; i < this.listeDeProposition.length && equals == true; i++) {
			if(!(this.listeDeProposition[i].equals(q.listeDeProposition[i]))) {
				equals = false;
			}
		}
		return equals;
	}
	
	//pre : this et q sont entierement initialiser
	//post : this et q ne sont pas modifies
	//resultat : retourne un booleen, true si this a les memes valeurs que q sinon false
	public boolean equals(QuestionQCM q) {
		return this.enonce.equals(q.enonce) &&
				this.egalPosition(q) &&
				this.niveauDifficulte ==q.niveauDifficulte&&
				this.reponseCorrect == q.reponseCorrect;
				
	}
	
	//pre : l'objet this est entierement initialise
	//post : l'objet this n'est pas modifie
	//resultat : retourne un clone de l'objet this 
	@Override
	public Question clone() {
			String[] copieListe = this.listeDeProposition.clone();
		return new QuestionQCM(enonce, niveauDifficulte, copieListe, reponseCorrect);	
	}
	
	//pre : listeDeProposition est entierement initialise
	//post : listeDeProposition n'est pas modifie
	//resultat : retourne un String contenant les elements de listeDeProposition 
	public String displayListOfAnswer() {
		String s = " ";
		char lettre = 'A';
		char[] alphabet = new char [listeDeProposition.length];
		for(int i = 0; i < listeDeProposition.length; i++) {
			alphabet[i] = lettre;
			s += alphabet[i] + " : " + listeDeProposition[i] + "\n" + " ";
			lettre ++;
		}
		return s;
	}
	
	//pre : l'objet est entierement initialise 
	//post : l'objet n'est pas modifie
	//resultat : retourne un String personnaliser avec le contenu de l'objet
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
	    s.append(this.afficheDifficulte()).append("\n")
	      .append(enonce).append("\n\n")
	      .append(displayListOfAnswer()).append("\n")
	      .append("rep : ").append(reponseCorrect).append("\n");
	    return s.toString();
	}
	
	//pre : listeDeProposition est entierement initialise avec des propositions de reponse
	//post : Les positions des elements de listeDeProposition sont modifier et la reponse 
	// correct est mise à jour
	@Override
	public void permuteProposition() {
		String oldAnswer = null;
		char lettre = 'A';
		
		for(int i = 0; i < listeDeProposition.length && oldAnswer == null; i++, lettre ++) {
			if(lettre == reponseCorrect) {
				oldAnswer = listeDeProposition[i];
			}
		}
		
		String temp = " ";
		int j = listeDeProposition.length -1;
		for(int i = 0; j > i; i++, j--) {
			temp = listeDeProposition[i];
			listeDeProposition[i] = listeDeProposition[j];
			listeDeProposition[j] = temp;
		}
		
		lettre = 'A';
		boolean misAJour = false;
		for(int i = 0; i < listeDeProposition.length && misAJour == false;i++, lettre ++) {
			if(listeDeProposition[i].equals(oldAnswer)) {
				reponseCorrect = lettre;
				misAJour = true;
			}
		}
	}
	
	/**
	 * res : retourne le tableau listeDeProposition */
	@Override
	public String[] getListeDeProposition() {
		return listeDeProposition;
	}

	//pre : niveauDeDifficulte de this est deja initialisé
	//post : niveauDeDifficulte de this reste inchangé
	//resultat : return la chaine contenue dans niveauDeDifficulte
	public int getNiveauDeDifficulte() {
		return this.niveauDifficulte;
	}
	
	//pre : listeDeProposition est entierement initialisé
	//post : listeDeProposition n'est pas modifié
	//resultat : retourne le tableau listeDeProposition
	public String[] getlisteDeProposition() {
		return listeDeProposition;	
	}
	
	//pre : liste est entièrement initialisé 
	//post : this est modifié et recois liste
	public void setListeDeProposition(String[] liste) {
		for(int i = 0; i < liste.length; i++) {
			this.listeDeProposition[i] = liste[i];
		}
	}

	//pre : reponseCorrect est deja initialise 
	//post : reponseCorrect n'est pas modifie
	//resultat : retourne le caractere contenu dans reponseCorrect
	@Override
	public String getReponseCorrect() {
		
		return this.reponseCorrect + " ";
	}

	//pre : enonce est deja initialise 
	//post : enonce n'est pas modifie
	//resultat : retourne la chaine contenu dans enonce
	public String getEnonce() {
		return enonce;
	}
	
	//pre : enonce est déjà initialisé
	//post : this.enonce est modifié et recois enonce
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	//pre : reponse est deja initialise 
	//post : reponse n'est pas modifie
	//resultat : modifie ReponseCorrect
	public void setReponseCorrect(char reponse) {
		this.reponseCorrect = reponse;
	}
	
	//pre : a est déjà initialisé et 2 < a < 3, 
	//post : niveauDeDifficulte est modifié 
	@Override
	void setNiveauDifficulte(int a) {
		this.niveauDifficulte = a;
	}
	
	/**
	 * res : retourne l’énoncé de la question */
	@Override
	public String retourneEnonce() {
		StringBuilder s = new StringBuilder();
		s.append(enonce).append("\n\n")
	      .append(displayListOfAnswer()).append("\n");
		return s.toString();
	}
}

class QuestionOuverte extends Question {
	private int reponseCorrect;
	
	QuestionOuverte(String enon, int difficulte, int reponse){
		super(enon, difficulte);
		reponseCorrect = reponse;
	}
	
		
	//pre : this et q sont entierement initialiser
	//post : this et q ne sont pas modifies
	//resultat : retourne un booleen, true si this a les memes valeurs que q sinon false
	public boolean equals(QuestionOuverte q) {
		return this.enonce.equals(q.enonce) &&
				this.niveauDifficulte ==q.niveauDifficulte&&
				this.reponseCorrect == q.reponseCorrect;
				
	}
	
	//pre : l'objet this est entierement initialise
	//post : l'objet this n'est pas modifie
	//resultat : retourne un clone de l'objet this 
	@Override
	public Question clone() {
		return new QuestionOuverte(enonce, niveauDifficulte, reponseCorrect);	
	}

	//pre : l'objet est entierement initialise 
	//post : l'objet n'est pas modifie
	//resultat : retourne un String personnaliser avec le contenu de l'objet
	@Override
	public String toString() {

		return this.afficheDifficulte() + "\n" + this.enonce + "\n\n"
				+ "rep : " + this.reponseCorrect; 
	}
	
	//pre : niveauDeDifficulte de this est deja initialisé
	//post : niveauDeDifficulte de this reste inchangé
	//resultat : return la chaine contenue dans niveauDeDifficulte
	public int getNiveauDeDifficulte() {
		return this.niveauDifficulte;
	}

	//pre : reponseCorrect est deja initialise 
	//post : reponseCorrect n'est pas modifie
	//resultat : retourne le caractere contenu dans reponseCorrect
	@Override
	public String getReponseCorrect() {
		return " " + this.reponseCorrect;
	}

	//pre : enonce est deja initialise 
	//post : enonce n'est pas modifie
	//resultat : retourne la chaine contenu dans enonce
	public String getEnonce() {
		return enonce;
	}
	
	//pre : enonce est déjà initialisé
	//post : this.enonce est modifié et recois enonce
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	//pre : reponse est deja initialise 
	//post : reponse n'est pas modifie
	//resultat : modifie ReponseCorrect
	public void setReponseCorrect(int reponse) {
		this.reponseCorrect = reponse;
	}
	
	// pre : a est déjà initialisé avec une valeur entre 1 et 3 inclus
	//post : niveauDeDifficulte est modifié 
	void setNiveauDifficulte(int a) {
		this.niveauDifficulte = a;
	}
	
	/**
	 * res : retourne l’énoncé de la question 
	 * */
	@Override
	public String retourneEnonce() {
		return this.enonce;
	}
}



