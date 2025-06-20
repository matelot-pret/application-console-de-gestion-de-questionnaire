package gestion_qcm;

import java.io.File;
import java.util.Scanner;

public class PrincipalPartie2 {
	public static void main (String[]args) {
		File fichier = null;
		fichier = new File ("/home/samourai/Documents/_projets/qcm/Questionnaire.txt");
		Questionnaire q = Questionnaire.lireFichierEtCreerQuestionnaire(fichier);
		Questionnaire q1 = q.clone();
		q1.permutesQuestion();
		boolean reponseValid = false;
		Scanner clavier = new Scanner(System.in);
		int pointAccumuler = 0;
		for(int i = 1; i <= q1.getnbrQuestion(); i++) {
			System.out.println(q1.getListeDeQuestions()[i-1].retourneEnonce());
			System.out.println("Veuillez entrer votre réponse : \n"
					+ "Pour passer la question entrez 0");
			
			while(reponseValid == false) {
				String input = clavier.nextLine();
				
				if(input.isEmpty() == false) {
					reponseValid = true;
				}
				if(input.equals("0")) {
					System.out.println("Vous avez passer la question " + i);
					pointAccumuler += 2;
				}else {
					pointAccumuler += q1.repondre(i, input);
				}
			}
		}
		clavier.close();
		System.out.println("\nQuestionnaire Terminé.\nPoint total : " + pointAccumuler);
	}
}