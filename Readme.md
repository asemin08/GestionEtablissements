# Projet de gestion d'établissement

Projet de gestion établissement web 

Lors de la navigation sur le site le retour en arrière ce fais par le clique sur le tire de la page




### Pré-requis
 
    1. Utiliser une java jdk 15 pour le projet
    2. Avoir Maven d'installer
    3. Avoir un serveur tomcat prêt à être utiliser 
    4. Avoir un serveur de base de données mysql
    5. Récupérer le projet en effectuant un git clone -b web https://github.com/asemin08/GestionEtablissements.git
    
---
### Base de données
    
    1. Ouvrir un terminal et connecter vous a votre base de données. (Si phpmyadmin est installé rendez vous dessus et connecter vous).
    2. Créer une base données pour le projet (par exemple: GestionEtablissement)
    3. Importer le fichier Gestion.sql (présent dans le dossier livrable) dans la base de données qui vient d'étre créer
     3.1 Sous phpmyadmin : aller dans l'onglet importer puis importer le fichier GestionEtablissement/livrable/GestionEtablissement.sql
     3.2 En terminal: importer le script GestionEtablissement/livrable/GestionEtablissement.sql en exécutant la commande : 
         * sous linux : mysql nom_base_de_donnees < GestionEtablissement/livrable/GestionEtablissement.sql
         * sous windows: mysql -u[utilisateur] -p [nom_base_de_donnees] < GestionEtablissement/livrable/GestionEtablissement.sql
    4. Effectuer la requête suivante pour vérifier que la base a bien été configurer (Deux valeur doit être renvoyer une personne directeur et une autre manager)
         Select * from Person; 
---

### Préparation à la création du war 

    1. Aller a la racine du projet GestionEtablissement
    2. Ce rendre dans le dossier GestionEtablissement-dao/src/main/java/eu/ensup/gestionetablissement/dao
    3. Ouvrir la classe Connect.java
    4. Modifier les valeur pour votre serveur mysql : 
      4.1 String URL = "jdbc:mysql://url-de-votre-base-de-donne/nom-de-la-base?serverTimezone=UTC"; Modifier avec le l'url + le nom de votre base de données.
      4.2 String USERNAME = "identifiant"; Modifier avec votre identifiant de base de données.
      4.3 String PASSWORD = "mot-de-passe";  Modifier avec votre mot de passe de base de données.

---

### Création du war + deployment du war

    1. Aller a la racine du projet GestionEtablissement
    2. Ce rendre dans le dossier GestionEtablissement-presentation
    3. Ouvrir un terminal depuis ce dossier
    4. Executer la commande mvn clean package
    
    
    # Installation avec l'administrateur tomcat :
    1. Ouvrir un navigateur web 
    2. Ce rendre sur le manager de tomcat http://votre_url/manager/html
    3. Se connecter
    4. Cherche la section "Fichier WAR à déployer"
    5. Cliquer sur choisir un fichier
    6. Depuis la pop up qui c'est ouvert ce rendre dans le dossier du projet puis dans GestionEtablissement-presentation puis dans target. 
    7. Séléctionner le fichier .war GestionEtablissement-presentation-1.0.war présent
    8. Cliquer sur ouvrier
    9. Cliquer sur deployer
    10. Attendre que le serveur tomcat deploy le war
    11. Pour vérifier que le war est déployer regarder dans la section "Applications" si le war est présent
    12. Ouvrir un nouveau naviagteur ou onglet et ce rendre sur http://votre-url-tomcat/GestionEtablissement_presentation_war/
    13. Normalement vous arriverais sur l'écran de login

    # Installation manuel sur le serveur tomcat:
    1. Ce rendre dans le dossier oû est installer le serveur tomcat
    2. Copier le fichier .war présent dans GestionEtablissement/GestionEtablissement-presentation/target/GestionEtablissement-presentation-1.0.war dans le dossier webapps du serveur tomcat
    3. Redémarer son serveur tomcat
    4. Ouvrir un navigateur 
    5. Ce rendre sur http://votre-url-tomcat/GestionEtablissement_presentation_war/
---

### Informations de connexion à l'application

* compte directeur : id : directeur , mdp : directeur 
* compte manager : id : manager , mdp : manager 
---



### Fonctionnalitées non-finit

* Statistique (chartjs) commencer retourne un bartchart + un pie chart sans les bonne valeurs correspondant au project
* Manque du lien modification etudiant depuis la liste d'étudiants
* Optimiser le fonctionnement de modifier un étudiant.
---
### Test unitaire du projet

    1. Aller a la racine du projet
    2. Ouvrir un terminal depuis la racine du fichier
    3. Exécuter la commande: mvn test
