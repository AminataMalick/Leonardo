LEONARDO FRAMEWORK
==================

# Bienvenue sur Leonardo !

Leonardo est un framework suffisamment adaptable pour répondre rapidement à des diffuseurs de contenus qui voudraient proposer à leurs clients une zone d'échange bien spécifique.

Le framework est fourni et conseillé d'utilisation avec son template de démo.


## Par où commencer  


**Librairies intégrées :**
* Grizzly : *Sous licence Common Development and Distribution License, framework utilisant les librairies NIO et permettant de construire des serveurs évolutifs et robustes.
Supporté à partir de Java SE 7.*

* Jersey : *Sous licence GNU,framework pour gérer les requêtes HTTP. Cette librairie fournit un servlet qui gère le REST en utilisant un serveur HTTP contenu par Grizzly. Il met en place différentes annotations qui permettent de gérer des ressources de manière très intuitive.*

* JDBC : *Sous licence GNU, interface de programmation pour les programmes utilisant Java permettant d'accéder à une base de données relationnelle. Il existe des pilotes JDBC pour toutes les bases de données relationnelles connues.* 


* jBCrypt : *Sous licence ISC/BSD, implémentation Java de hachage de mots de passe.*

* Java JWT : *Sous licence MIT, librairie de vérification de tokens.*

* JavaMail : *Sous licence CDDL, GPL et BSD, API utilisée et  intégrée à la plate-forme Java EE pour envoyer et recevoir des e-mails.* 

* API Servlet : *Sous licence CDDL et GPL, ensemble d’interfaces et de classes Java, rangées dans les packages javax.servlet et javax.servlet.http.*

* Gson : *Sous licence Apache, librairie open source permettant de convertir un objet Java dans sa représentation JSON et vice versa.*


## Installation environnement

* **Apache Maven :** *Sous licence Apache, outil de gestion et d'automatisation de production des projets logiciels Java à partir de ses sources et utilisant un paradigme connu sous le nom de Project Object Model (POM) afin de décrire un projet logiciel et ses dépendances.*

Pour insérer les dépendances listées ci-dessus, copiez la partie de code ci-dessous dans votre fichier 'POM.xml' :
```xml
	<dependencies>
		<!-- https://mvnrepository.com/artifact/org.mindrot/jbcrypt -->
		<dependency>
			<groupId>org.mindrot</groupId>
			<artifactId>jbcrypt</artifactId>
			<version>0.4</version>
		</dependency>
		<!-- MySQL database driver -->
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>6.0.6</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.auth0/java-jwt -->
		<dependency>
			<groupId>com.auth0</groupId>
			<artifactId>java-jwt</artifactId>
			<version>3.3.0</version>
		</dependency>
		<dependency>
			<groupId>javax.mail</groupId>
			<artifactId>mail</artifactId>
			<version>1.4.3</version>
		</dependency>
		<dependency>
			<groupId>org.glassfish.jersey.containers</groupId>
			<artifactId>jersey-container-grizzly2-http</artifactId>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.2.4</version>
		</dependency>
	</dependencies>
```

**'exemple.BD.properties' :** Fichier se trouvant à la racine du projet.
```
################################################	
# Database access properties	
################################################	
###### JDBC driver  ######	
jdbc.driver = com.mysql.cj.jdbc.Driver

#####  Databse URL  ######
	database.url = jdbc:mysql://localhost/leonardo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	
##### Username and password to access the database	#####
database.username = <userName>
database.password = <userPWD>

```

### Mordifications à apporter :
* MySQL est utilisée dans la version fournie en démonstration et configurée dans le fichier ci-dessus avec pour < userName > votre nom d'utilisateur et < userPWD > votre mot de passe. 

* Il faut aussi supprimmer le 'exemple.' avant BD.Properties 

* Il existe des drivers JDBC pour toutes les bases de données connues, il est donc facile d'adapter Leonardo à une autre base de données en modifiant 'jdbc.driver'.

**Prérequis :** Java 1.7 ou version ultérieure.  

### Lancement du serveur 
Pour compiler et lancer le serveur, effectuez la commande ci-après :
```
	mvn clean compile exec:java
``` 
Le serveur est maintenant en marche et attend les requêtes.

## Licence 
Leonardo est licencié sous une licence GNU GPL.

Aucune partie de cette publication ne peut être reproduite ou transmise sous quelque forme ou à quelque fin que ce soit sans l'autorisation expresse de la société CPASAM.

La permission est par la présente accordée à toute personne obtenant une copie de ce logiciel et des fichiers de documentation associés (le «Logiciel»), de traiter le Logiciel sans restriction, y compris sans limitation les droits d'utilisation, de copie, de modification, de fusion , publier, distribuer, sous-licencier et / ou vendre des copies du Logiciel et permettre aux personnes auxquelles le Logiciel est fourni de le faire, sous réserve des conditions suivantes:

L'avis de copyright ci-dessus et cet avis d'autorisation doivent être inclus dans toutes les copies ou parties substantielles du logiciel.

LE LOGICIEL EST FOURNI « TEL QUEL », SANS AUCUNE GARANTIE, EXPLICITE OU IMPLICITE, Y COMPRIS, SANS LIMITATION, LES GARANTIES DE QUALITÉ MARCHANDE, D' ADEQUATION A UN USAGE PARTICULIER ET ABSENCE DE CONTREFAÇON. EN AUCUN CAS LES AUTEURS OU LES DÉTENTEURS DE DROITS D' AUTEUR NE SERA RESPONSABLE DE TOUTE RECLAMATION, DOMMAGE OU AUTRE RESPONSABILITÉ, QUE CE SOIT DANS UNE ACTION DE CONTRAT, UN TORT OU AUTRE, DECOULANT DE, OU EN RAPPORT AVEC LE LOGICIEL OU L'UTILISATION OU AUTRE DANS LE LOGICIEL.

