# Bienvenue sur MyCalendarMaricon

MyCalendarMaricon est un calendrier où il est facile d'ajouter ses évènements.
Comme Google Calendar, il résulte d'un besoin d'avoir un calendrier electronique.

## Les technologies

### Back

- [Spring Boot / REST / JPA / HATEOAS](https://spring.io/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/fr/)

### Front

- [Node.js v10.15](https://nodejs.org/en/)
- [Angular CLI / Angular 7](https://cli.angular.io/)
- [Composant Calendrier Angular](https://mattlewis92.github.io/angular-calendar/#/kitchen-sink)
- [SweetAlert](http://t4t5.github.io/sweetalert/)

## Installation

### Configuration de l'API REST

- [Java JDK Version 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)

- [Eclipse Java EE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2)

- Clonez le projet dans votre entrepôt :

  git clone https://github.com/viperee/MyCalendarMaricon.git

- Dans votre workspace d'Eclipse : **Import -> Maven -> Existing Maven Projects**

- Cliquez droit sur votre projet : **Run as -> Maven -> Update project** pour télécharger les libs.

### Test de l'API Rest

- Vous pouvez tester l'api sans deployer le client Angular via l'application [Postman](https://www.getpostman.com/)
- Vous pouvez importer les requetes dans Postman via le bouton **Import** puis **Import from Link** :  https://www.getpostman.com/collections/bb595da6078e2c677d69

### Base de données

- La base de données est une base en mémoire [H2](http://h2database.com/html/main.html), vous n'avez donc pas besoin de configurer de base de données.

### Démarrer le serveur

- Sélectionnez votre projet

- Ouvrez l'arborescence jusqu'à trouver la classe **src/main/java -> fr.mycalendarmaricon.mycalendarmaricon -> MycalendarmariconApplication.java**

- Clique droit -> Run As -> Java Application

- Vous verrez dans la console la ligne suivante:

  ```
  2019-01-26 17:23:04.733  INFO 4912 --- [  restartedMain] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
  2019-01-26 17:23:05.220  INFO 4912 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
  2019-01-26 17:23:05.239  INFO 4912 --- [  restartedMain] f.m.m.MycalendarmariconApplication       : Started MycalendarmariconApplication in 17.909 seconds (JVM running for 19.291)
  ```

- Votre serveur est lancé

### Configuration du Front

- [Installer Node.js](https://nodejs.org/en/)

- [Installer Angular CLI](https://cli.angular.io/)

- Ouvrir un terminal

- Lancez la commande **npm install** dans le répertoire **myCalendarMariconFront**

- Puis saisissez la commande suivante pour démarrer le client :
  **`ng serve`** 

- Vous devriez voir les informations suivantes :

  `webpack: Compiled successfully.`

- Votre serveur est lancé.

- Ouvrez votre navigateur et allez à l'adresse suivante : [http://localhost:4200/](http://localhost:4200/)
