
Avant tout:

Vous devez avoir le jar de basex.

Description des �tapes/modifications du projet:

La premi�re �tape �tait de cr�er une application qui avant tout utilis� un rss t�l�charg� (fichier xml) et stock� sur votre pc, et un fichier series.xml cr�� par vous-m�me qui d�crit les s�ries que vous souhaitez t�l�charg�. 

Pour cela, nous sommes parties du projet existant donn�, et nous avons d�velopp� un 5e case dans la classe Controller.java du package xquery.controller 

Le 5e case correspond � notre application. Il permet de faire des requ�tes sur nos deux fichiers xml (cit�es au-dessus).Il v�rifie si il y a correspondance entre nos s�ries et les s�ries du fichier rss. 

Nous avons eu besoin d'un fichier config.properties qui permet de d�finir le nom de la base de donn�es du rss, le nom de la bdd de nos s�ries, le chemin du fichier xml qui correspond au fichier rss, et le chemin de notre xml, le series.xml

Nous avons ajout� deux attributs dans la classe configLoader.java qui correspond � nos chemins et nos bdd. Nous avons �galement ajout� un attribut dans l'interface Vue.java du package xquery.view pour notre 5e case.

Pour �diter et cr�er notre xml de sortie qui correspond � la s�rie jug� correspondante entre notre series.xml et le rss, nous avons cr�e une classe WriteXML.java dans le package xquery.model. Nous l'appelons dans le controller. Tout ceci dans le but de garder et de respecter le choix d'architecture d�j� pr�sent.

Nous avons ajout� dans la vue, dans la classe CommandLinesInterface.java une system.out pour notre 5e case. 

Nous avons cr�� un fichier xsl pour pouvoir ouvrir notre fichier xml de sortie dans un navigateur internet. Ce dernier se trouve dans le dossier racine du projet, dans le r�pertoire appel� "xml".

Nous avons cr�� un fichier xsd pour d�crire notre fichier xml de sortie. Ce dernier se trouve dans le dossier racine du projet, dans le r�pertoire appel� "xml".

Lorsque les xml sont cr��s, ils sont �galement stock�s dans le r�pertoire "xml" qui se trouve � la racine de notre projet. 

Par manque de temps, le projet n'est pas abouti car nous avons commenc� � d�velopper l'id�e de faire appel, � OMDB. Nous obtenons r�ponse de la part d'OMDB, pour nos s�ries de notre s�ries.xml mais nous n'avons pas encore fait la liaison avec les xml g�n�r�s par notre application.
