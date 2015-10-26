# resources-manager #

## Utilisation ##

### Comment déployer l'application ###

* Récupérer le WAR et le déployer sur un Tomcat.
* La base de donnée est créée automatiquement
* Pour disposer de données par défaut (dont les utilisateurs, indispensable pour utiliser l'application) : **localhost:8080/resources-manager/pages/demo-init**

Identifiants par défaut (mots de passe vide par défaut)
* "admin"/"" 
* "michel"/""

*Testé sur Tomcat 7.0.65 et 8.0.26*

## Compilation dans Eclipse ##

### En cas de ClassNotFound au lancement ###

Project Properties -> Deployment Assembly -> Add... -> Java Build Path Entries -> Maven Dependencies -> Finish

Il faut maintenant qu'il y ait une ligne **Maven Dependencies : WEB-INF/lib**

### En cas de problème d'exceptions dans les JSP ###

Project Properties -> Java Build Path -> Librairies -> Add Librairies -> Server Runtime -> Selectionner le Tomcat