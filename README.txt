
Avant tout:

Vous devez avoir le jar de basex.

Description des étapes/modifications du projet:

La première étape était de créer une application qui avant tout utilisé un rss téléchargé (fichier xml) et stocké sur votre pc, et un fichier series.xml créé par vous-même qui décrit les séries que vous souhaitez téléchargé. 

Pour cela, nous sommes parties du projet existant donné, et nous avons développé un 5e case dans la classe Controller.java du package xquery.controller 

Le 5e case correspond à notre application. Il permet de faire des requêtes sur nos deux fichiers xml (citées au-dessus).Il vérifie si il y a correspondance entre nos séries et les séries du fichier rss. 

Nous avons eu besoin d'un fichier config.properties qui permet de définir le nom de la base de données du rss, le nom de la bdd de nos séries, le chemin du fichier xml qui correspond au fichier rss, et le chemin de notre xml, le series.xml

Nous avons ajouté deux attributs dans la classe configLoader.java qui correspond à nos chemins et nos bdd. Nous avons également ajouté un attribut dans l'interface Vue.java du package xquery.view pour notre 5e case.

Pour éditer et créer notre xml de sortie qui correspond à la série jugé correspondante entre notre series.xml et le rss, nous avons crée une classe WriteXML.java dans le package xquery.model. Nous l'appelons dans le controller. Tout ceci dans le but de garder et de respecter le choix d'architecture déjà présent.

Nous avons ajouté dans la vue, dans la classe CommandLinesInterface.java une system.out pour notre 5e case. 

Nous avons créé un fichier xsl pour pouvoir ouvrir notre fichier xml de sortie dans un navigateur internet. Ce dernier se trouve dans le dossier racine du projet, dans le répertoire appelé "xml".

Nous avons créé un fichier xsd pour décrire notre fichier xml de sortie. Ce dernier se trouve dans le dossier racine du projet, dans le répertoire appelé "xml".

Lorsque les xml sont créés, ils sont également stockés dans le répertoire "xml" qui se trouve à la racine de notre projet. 

Par manque de temps, le projet n'est pas abouti car nous avons commencé à développer l'idée de faire appel, à OMDB. Nous obtenons réponse de la part d'OMDB, pour nos séries de notre séries.xml mais nous n'avons pas encore fait la liaison avec les xml générés par notre application.
