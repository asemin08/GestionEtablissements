# Projet de gestion d'établissement

Projet de gestion établissement web 

Une base de données est fournie donc utiliser les identifiants de connexions fournis ci-dessous pour vous connecter à l'application

Lors de la navigation sur le site le retour en arrière ce fais par le clique sur le tire de la page


### Pré-requis
 
    1. Utiliser une java jdk 15 pour le projet
    2. Avoir Maven d'installer
    3. Avoir un serveur tomcat prêt à être utiliser 
    4. Avoir un serveur de base de données mysql
    5. Récupérer le projet en effectuant un git clone -b web + lien du projet
    
---
### Base de données
    
    1. Ouvrir un terminal est connecter vous a votre base de données. (Si phpmyadmin est installé rendez vous dessus et connecter vous).
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

### Création du war 

    6. Aller a la racine du projet 
    7. Ce rendre dans le dossier présentation
    8. Ouvrir un terminal depuis ce dossier
    9. Executer la commande mvn clean package
    10. Ouvrir un navigateur web 
    11. Ce rendre sur le manager de tomcat http://votre_url/manager/html
    12. Se connecter
    13. Cherche la section "Fichier WAR à déployer"
    14. Cliquer sur choisir un fichier
    15. Depuis la pop up qui c'est ouvert ce rendre dans le dossier du projet puis dans GestionEtablissement-presentation puis dans target. 
    16. Séléctionner le fichier .war GestionEtablissement-presentation-1.0.war présent
    17. Cliquer sur ouvrier
    18. Cliquer sur deployer
    19. Attendre que le serveur tomcat deploy le war
    20. Pour vérifier que le war est déployer regarder dans la section "Applications" si le war est présent
    21. Ouvrir un nouveau naviagteur ou onglet et ce rendre sur http://votre_url/GestionEtablissement_presentation_war/
    22. Normalement vous arriverais sur l'écran de login
---

### Informations de connexion
* compte directeur : id : directeur , mdp : directeur 
* compte manager : id : manager , mdp : manager 




### Fonctionnalité non-fini 

* Statique commencer retourne un graph sous forme de photo (photo générer depuis la servlet statistique)
* Manque du lien modification etudiant depuis la liste d"étudiants

### Test unitaire du projet

    1. Aller a la racine du projet
    2. Ouvrir un terminal depuis la racine du fichier
    3. Exécuter la commande: mvn test
