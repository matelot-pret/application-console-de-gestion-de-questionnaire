package gestion_qcm;

import java.io.File;
import java.util.Scanner;

public class PrincipalPartie3{
	public static void main(String[]args) {
		Scanner clavier = new Scanner(System.in);
		File fichier = null;
		fichier = new File("Questionnaire.txt");
		
		System.out.println("Veuillez répondre aux questions suivantes pour créer votre questionnaire\n");
		System.out.println("Veuillez entrer le nom de votre questionnaire :");
		String nom = clavier.nextLine();
		while(nom.isBlank()) {
			System.out.println("[ERR] ENTREE INVALIDE ! enonce vide. Veuillez écrire quelque chose.");
			nom = clavier.nextLine();
		}
		int nbrQuestionMax = 0;
		boolean reponseValid = false;
		System.out.println("Combien de questions portera ce  questionnaire ?");
		while(reponseValid == false) {
			if(clavier.hasNextInt() == false) {
				clavier.next();
				System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrée un nombre ");
			}else {
				nbrQuestionMax = clavier.nextInt();
				reponseValid = true;
			}
		}
		clavier.nextLine();
		
		Questionnaire q1 = new Questionnaire(nbrQuestionMax, nom);

		for(int i = 1; i <= nbrQuestionMax && nbrQuestionMax > q1.getnbrQuestion(); i++) {
			System.out.println("Veuillez choisir le type de la question " + (i) + " : ");
			System.out.println("1- Question à choix multiple \n2- Question Ouverte");
			System.out.println("\n\nVeuillez entrer votre choix :");

			int typeQuestion = 0; 
			reponseValid = false;
			
			while(reponseValid == false) {
				if(clavier.hasNextInt() == false) {
					clavier.next();
					System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre");
				}else {
					typeQuestion = clavier.nextInt();
					while(Questionnaire.getIntWithinBounds(typeQuestion, 1, 2) == false) {
						System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer 1 ou 2 en fonction de votre choix");
						typeQuestion = clavier.nextInt();
					}
					reponseValid = true;
				}
			}
			clavier.nextLine();
			
			
			System.out.println("Veuillez entrer l'énoncé de la question " + i);
			String enonce = clavier.nextLine();
			while(enonce.isBlank()) {
				System.out.println("[ERR] ENTREE INVALIDE ! enonce vide. Veuillez écrire quelque chose.");
				enonce = clavier.nextLine();
			}
			switch(typeQuestion) {
				case 1:
					int nbrProposition = 0;
					reponseValid = false;
					System.out.println("Quel est le nombre de proposition de réponse pour cette question ?");
					while(reponseValid == false) {
						if(clavier.hasNextInt() == false) {
							clavier.next();
							System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre");
						}else {
							nbrProposition = clavier.nextInt();
							reponseValid = true;
						}
					}
					clavier.nextLine();
					String[] proposition = new String[nbrProposition];
					char lettre = 'A';
					for(int j = 0; j < nbrProposition; j++, lettre++) {
						System.out.println("Veuillez entrer la proposition de réponse " + lettre);
						proposition[j] = clavier.nextLine();
						while(proposition[j].isBlank()) {
							System.out.println("[ERR] ENTREE INVALIDE ! proposition vide. Veuillez écrire quelque chose.");
							proposition[j] = clavier.nextLine();
						}
					}
					QuestionQCM question = new QuestionQCM(enonce, proposition);
					char bonneReponse = 'A';
					reponseValid = false;
					System.out.println(question.displayListOfAnswer());
					System.out.println("Veuillez entrez la lettre de la proposition correspondante à la bonne réponse :");
					while(reponseValid == false) {
						String input = clavier.nextLine();
						if(input.isEmpty() == false) {
							bonneReponse = input.charAt(0);
							if(bonneReponse >= 'A' && bonneReponse <= lettre) {
								reponseValid = true;
							}else {
								System.out.println("[ERR] ENTREE INVALIDE Veuillez entrer la lettre de l'une des propositions en MAJUSCULE");
							}
						}else {
							System.out.println("[ERR] ENTREE INVALIDE Veuillez entrer la lettre de l'une des propositions en MAJUSCULE");
						}
					}
			
					question.setReponseCorrect(bonneReponse);

					int changeDifficulte = 0;
					reponseValid = false;
					System.out.println("Le niveau de difficulté est 1 par defaut.\n"
							+ "Pour le modifier à 2 entrez 2 , pour 3 entrez 3 \n"
							+ "Entrer 1 pour garder la valeur par défaut");
					while(reponseValid == false) {
						if(clavier.hasNextInt() == false) {
							System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre");
							clavier.next();
						}else{
							changeDifficulte = clavier.nextInt();
							if(changeDifficulte < 1 || changeDifficulte > 3) {
								System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre compris entre 1 et 3 inclus");
							}else {
								reponseValid = true;
							}
						}
					}
					
					clavier.nextLine();
					if(changeDifficulte != 1) {
						question.setNiveauDifficulte(changeDifficulte);
					}
					
					q1.addQuestion(question);
				break;
				case 2 :
					System.out.println("Veuillez entrer la réponse de la question " + i);
					int reponseCorrect = 0;
					reponseValid = false;
					while(reponseValid == false) {
						if(clavier.hasNextInt() == false) {
							clavier.next();
							System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre");
						}else {
							reponseCorrect = clavier.nextInt();
							reponseValid = true;
						}
					}
					
					int difficulte = 0;
					reponseValid = false;
					System.out.println("Le niveau de difficulté va de 1 à 3 \n"
							+ "Veuillez entrer le niveau de difficulté de la question :");
					while(reponseValid == false) {
						if(clavier.hasNextInt() == false) {
							System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer un nombre");
							clavier.next();
						}else{
							difficulte = clavier.nextInt();
							while(Questionnaire.getIntWithinBounds(difficulte, 1, 3) == false) {
								System.out.println("[ERR] ENTREE INVALIDE ! Veuillez entrer 1 ou 3 en fonction de votre choix");
								difficulte = clavier.nextInt();
							}
							reponseValid = true;
						}
					}
					QuestionOuverte questionOuverte = new QuestionOuverte(enonce, difficulte, reponseCorrect);
					q1.addQuestion(questionOuverte);
					
				break;
			}
		}
			
			
					if(nbrQuestionMax == q1.getnbrQuestion()) {
					System.out.println("Le questionnaire contient toutes ses questions");
					}
		
		q1.sumitQuestion();
		q1.modifieDateButoir();
		q1.saveQuestionnaire(fichier);
		
		Questionnaire q2 = Questionnaire.lireFichierEtCreerQuestionnaire(fichier);
		q2.saveQuestionnaire(fichier);
		
		
		clavier.close();    
	}
}