LEONARDO FRAMEWORK
==================

Zone de developpement :
-----------------------

Pour chaque fonctionnalité il faut :

+ Se mettre sur la banche develop

```bash
git checkout develop
```

+ Démarrer le developpement de la fonctionnalité

```bash

git flow feature start <nomDeLaFonctionnalite>

```


+ Developper sa fonctionnalité
	
```bash

#Afficher l'etat de l'index
git status 

#Ajouter les modifications à l'index
git add <nomDuFichier>

#Créer une version
git commit -m "Type de modification, méthode modifié, classe modifiée"

```

+ Fermer la fonctionnalité ( si la fonctionnalité est terminée ) 

```bash

git flow feature stop <nomDeLaFonctionnalite>

```

+ Mettre la fonctionnalité sur Github

```bash

#Récuperer les fonctionnalités sur le serveur
git pull origin develop

#Uploader ses fonctionnalités sur le serveur
git push origin develop

```

Et voilà vous pouvez maintenant faire une nouvelle fonctionnalitée
