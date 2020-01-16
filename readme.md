# Projet 6 - Site web d'une association d'escalade

## Contenu
Ce projet résulte de la mise en application des cours OpenClassrooms HTML, CSS, Bootstrap, Maven, Java EE et SPring du parcours "Développeur d'application JAVA".

L'application est un site web communautaire permettant aux membres de l'association de partager leurs connaissances des sites d'escalade.


## Prérequis
 - **Java** version 1.8 
 - **JDK** : jdk1.8.0_201
 - **Maven** 3.6
 

## Base de données 
Les scripts SQL présents dans src/main/resources/Scripts permettent de créer une base de données et un jeux de test
 - Création des tables : *Create_DB_escalade.sql*
 - Alimentation des données : *Insert_DB_escalade.sql*



## Installation et déploiement
#### Déploiement sans conteneur web
 - cloner le projet Github
 - Exécuter la ligne de commande
 
 
    mvn clean package spring-boot:run
    
      
 - Ouvrir un navigateur web avec l'adresse
 
 
    http://localhost:8080


#### Déploiement dans un conteneur Web
 - Générer un package au format WAR dans le répertoire target avec la commande


    mvn clean package
 - Placer le package (war) dans le répertoire webapps d'un conteneur web (type Tomcat)
 - Avec un Tomcat déployé en local, utiliser la commande suivante dans un navigateur    
    
    
     http://localhost:8080/escalade-0.0.1-SNAPSHOT.war

