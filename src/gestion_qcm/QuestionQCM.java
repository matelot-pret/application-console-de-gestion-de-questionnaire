package gestion_qcm;



public class QuestionQCM {
	private int niveauDeDifficulte;
	private String enonce;
	private String[] listeDeProposition;
	private char reponseCorrect;
	
	//pré : niveauDeDifficulte ne recois comme valeur que 1 ou 2 ou 3 et rien d'autre
	QuestionQCM(int difficulte, String enon, String[] liste, char reponse){
		niveauDeDifficulte = difficulte;
		enonce = enon;
		reponseCorrect = reponse;
		listeDeProposition = new String [liste.length];
		for(int i = 0; i < liste.length; i++) {
			listeDeProposition[i] = liste[i];
		}
		
	}
	
	//pre : niveauDeDifficulte est initialisé avec 1, reponseCorrect est initialisé avec la première réponse correct
	QuestionQCM(String enon, String[] liste){
		niveauDeDifficulte = 1;
		enonce = enon;
		listeDeProposition = new String[liste.length];
		for(int i = 0; i < liste.length; i++) {
			listeDeProposition[i] = liste[i];
		}
		reponseCorrect = 'A';
	}
	
	//pre : listeDeProposition de l'objet this et de l'objet q sont entierement initialise
	//post : les objets this et q ne sont pas modifié  
	//resultat : retourne true si les elements des tableaux des deux objets sont identiques sinon false
	public boolean ifListAreEquals(QuestionQCM q) {
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
				this.ifListAreEquals(q) &&
				this.niveauDeDifficulte ==q.niveauDeDifficulte&&
				this.reponseCorrect == q.reponseCorrect;
				
	}
	
	//pre : l'objet this est entierement initialise
	//post : l'objet this n'est pas modifie
	//resultat : retourne un clone de l'objet this 
	public QuestionQCM clone() {
			String[] copieListe = this.listeDeProposition.clone();
		return new QuestionQCM(niveauDeDifficulte, enonce, copieListe, reponseCorrect);	
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
	public String toString() {
		return niveauDeDifficulte + "\n" + enonce + "\n" + displayListOfAnswer() + reponseCorrect; 
	}
	
	//pre : listeDeProposition est entierement initialise avec des propositions de reponse
	//post : Les positions des elements de listeDeProposition sont modifier et la reponse 
	// correct est mise à jour
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
	
	//pre : niveauDeDifficulte de this est deja initialisé
	//post : niveauDeDifficulte de this reste inchangé
	//resultat : return la chaine contenue dans niveauDeDifficulte
	public int getNiveauDeDifficulte() {
		return this.niveauDeDifficulte;
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
	public char getReponseCorrect() {
		return reponseCorrect;
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
	void setNiveauDeDifficulte(int a) {
		this.niveauDeDifficulte = a;
	}
	
	/** pre : bonneReponse déjà initialisé et correspond à une proposition de listeDeProposition, 
	 * listeDeProposition entièrement initialisé
	* post : bonneReponse et listeDeProposition restent inchangée
	* resultat : retourne 0 si bonneReponse n'est pas dans listeDeProposition,
	* 1 si bonneReponse est présent une fois et 2 si il est présent deux fois
	*/
	public int ifQuestionValid(char bonneReponse) {
		char lettre = 'A';
		String reponse = null;
		for(int i = 0; i < listeDeProposition.length && reponse == null; i++, lettre++) {
			if(lettre == bonneReponse) {
				reponse = listeDeProposition[i];
			}
		}
		int cpt = 0;
		for(int i = 0; i < listeDeProposition.length && cpt < 2; i++) {
			if(listeDeProposition[i].equals(reponse)) {
				cpt ++;
			}
		}
		return cpt;
	}
}
