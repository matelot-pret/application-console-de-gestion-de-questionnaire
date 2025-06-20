package gestion_qcm;

import java.io.File;
import java.util.Scanner;

public class RepondreQuestionnaire{
	public static void main (String[]args) {
		File fichier = null;
		fichier = new File ("/home/samourai/Documents/_projets/qcm/Questionnaire.txt");
		Questionnaire q = Questionnaire.lireFichierEtCreerQuestionnaire(fichier);

		boolean reponseValid = false;
		Scanner clavier = new Scanner(System.in);
		
		int pointAccumuler = 0;
		String input = " ";
		for(int i = 1; i <= q.getListeDeQuestions().length; i++) {
			System.out.println(q.getListeDeQuestions()[i-1].retourneEnonce());

			System.out.println("Veuillez choisir la bonne réponse \n"
					+ "Pour passer la question entrez 0");
			reponseValid = false;
			while(reponseValid == false) {
				input = clavier.nextLine();
					
				if(input.isEmpty() == false) {
					reponseValid = true;
				}
				if(input.equals("0")) {
					System.out.println("Vous avez passer la question " + i);
					pointAccumuler += 2;
				}else {
					pointAccumuler += q.repondre(i, input);
				}
			}
		}
		clavier.close();
		System.out.println("\nQuestionnaire Terminé.\nPoint total : " + pointAccumuler);
	}
}