# Application Console de Gestion de Questionnaire

> **📚 Projet académique - Java première année**  
> *Projet en cours de développement dans le cadre d'un apprentissage Java*

## 📋 Description

Cette application Java console permet de créer, gérer et répondre à des questionnaires interactifs. Elle supporte deux types de questions :
- **Questions QCM** : avec choix multiples (A, B, C, D, E)
- **Questions ouvertes** : avec réponse libre

## 🚀 Fonctionnalités

### Création de questionnaires
- Interface interactive pour créer des questionnaires
- Saisie du nom du questionnaire et du nombre de questions
- Configuration de questions avec différents niveaux de difficulté (1 à 3 étoiles)
- Sauvegarde automatique des questionnaires dans des fichiers texte

### Réponse aux questionnaires
- Interface de réponse interactive
- Calcul automatique du score
- Possibilité de passer des questions
- Affichage des résultats finaux

### Gestion des dates
- Date de création automatique
- Date de soumission configurable
- Date butoir automatique (1 mois après la soumission)

## 📁 Structure du projet

```
qcm/
├── src/
│   ├── gestion_qcm/
│   │   ├── Principal.java           # Point d'entrée pour créer un questionnaire
│   │   ├── Questionnaire.java       # Classe principale de gestion des questionnaires
│   │   ├── Question.java            # Classe abstraite pour les questions
│   │   ├── QuestionQCM.java        # Implémentation des questions QCM
│   │   ├── RepondreQuestionnaire.java # Interface pour répondre aux questionnaires
│   │   └── PrincipalPartie2.java    # Point d'entrée alternatif
│   └── 1SAMOU_PATRICK/             # Archive de développement
├── bin/                            # Fichiers compilés (.class)
├── Questionnaire.txt               # Fichier de questionnaire généré
├── cas_nominal.txt                 # Exemple de questionnaire
├── date_butoir_dépassée.txt       # Test de gestion des dates
├── mix.txt                        # Questionnaire mixte
├── non_soumis.txt                 # Test questionnaire non soumis
└── README.md                      # Ce fichier
```

## 🛠️ Installation et utilisation

### Prérequis
- Java 8 ou version supérieure
- Un terminal/invite de commande

### Compilation
```bash
# Naviguer vers le répertoire du projet
cd chemin/vers/qcm

# Compiler les fichiers Java
javac -d bin src/gestion_qcm/*.java
```

### Exécution

#### Créer un nouveau questionnaire
```bash
java -cp bin gestion_qcm.Principal
```

#### Répondre à un questionnaire existant
```bash
java -cp bin gestion_qcm.RepondreQuestionnaire
```

## 📝 Utilisation détaillée

### Création d'un questionnaire

1. Lancez l'application avec `Principal.java`
2. Saisissez le nom du questionnaire
3. Indiquez le nombre de questions
4. Pour chaque question :
   - Saisissez l'énoncé
   - Choisissez le type (QCM ou ouverte)
   - Pour les QCM : ajoutez les propositions et la bonne réponse
   - Définissez le niveau de difficulté (1-3 étoiles)

### Répondre à un questionnaire

1. Lancez `RepondreQuestionnaire.java`
2. Le questionnaire se charge depuis `Questionnaire.txt`
3. Répondez aux questions une par une
4. Utilisez "0" pour passer une question
5. Consultez votre score final

## 🎯 Niveaux de difficulté

- `(*)` : Facile
- `(**)` : Moyen  
- `(***)` : Difficile

## 📄 Format des fichiers

Les questionnaires sont sauvegardés dans un format texte structuré :

```
Nom du questionnaire : [Nom]
Nombre de questions : [Nombre]
Date de création : [YYYY-MM-DD]
Date de soumission : [YYYY-MM-DD]
Date Butoir : [YYYY-MM-DD]

Question 1
[Niveau de difficulté]
[Énoncé]
[Options pour QCM]
rep : [Réponse correcte]
```

## 🧪 Tests et exemples

Le projet inclut plusieurs fichiers d'exemple :
- `cas_nominal.txt` : Questionnaire standard
- `mix.txt` : Mélange de questions QCM et ouvertes
- `date_butoir_dépassée.txt` : Test de gestion des dates
- `non_soumis.txt` : Questionnaire non soumis

## 🤝 Contribution

Ce projet a été développé dans le cadre d'un exercice académique de **première année de Java**. Il s'agit d'un **projet d'apprentissage en cours de développement**.

**Objectifs pédagogiques :**
- Maîtrise des concepts de base de Java (classes, objets, héritage)
- Gestion des entrées/sorties console
- Manipulation de fichiers
- Gestion des dates avec `LocalDate`
- Validation des saisies utilisateur

Pour contribuer :
1. Fork le projet
2. Créez une branche pour votre fonctionnalité
3. Committez vos changements
4. Poussez vers la branche
5. Ouvrez une Pull Request

## 📞 Support

Pour toute question ou problème, veuillez consulter la documentation du code ou contacter le développeur.

## 📜 Licence

Ce projet est développé à des fins éducatives dans le cadre d'un cursus Java première année.

---
*Projet étudiant - Année académique 2024-2025*
