# Clean Architecture
![img.png](img.png)
# Les bases de cette architecture
La logique que vous implémentez doit :

   * Être indépendante des frameworks : les frameworks et librairies doivent être des outils, sans pour autant vous contraindre. 
   * Être testable indépendamment : les tests doivent pouvoir être réalisés sans éléments externes (interface utilisateur, base de données ...)
   * Être indépendante de l’interface utilisateur : l’interface utilisateur doit pouvoir changer de forme (console, interface web ...)
   * Être indépendante de la base de données : il doit être possible de changer de SGBD.
   * Être indépendante de tout service ou système externe : en résumé elle ne doit pas avoir conscience de ce qui l’entoure.



# Définition de la structure du projet

* Application : la partie des appels extérieurs
* Business : La partie métier
* Infrastructure : la partie de communication avec les infrastructures de l'entreprise

# Description des packages pour chaques modules

## Business

|   Package    |      implèmentation      | Description                                                        |
|:------------:|:------------------------:|:-------------------------------------------------------------------|
|   services   |  interface adapters.in   | Contient les cases d'usages                                        |
|    rules     |                          | Ensemble de régles qui seront utilisées uniquement dans `services` |
|    models    |                          | Les classes de données                                             |
| adapters.in  |                          | Interfaces pour les cases d'usages                                 |
| adapters.out |                          | Interfaces pour le module `infrastructure`                          |

# Le Développement
Pour le développement d'une application avec clean architecture, 
il faut respecter plusieurs règles.
Ces règles seront en partie contrôlées par la librairie [ArchUnit](https://www.archunit.org/userguide/html/000_Index.html) 
 Ce qui explique la présence du module `Archeteture`

Le module Business doit-être complétement autonome [contrôle](architecture/src/test/java/org/model/projet/architecture/ControleArchitectureBusinessTest.java)



## Case d'usage :
### Création
La création d'un usage commence si possible de l'ajout d'un nouveau test dans le module `Business`
#### Nouveau usage

### Appel
# Ressources
[clean-architecture](https://leandeep.com/clean-architecture/)
[la-clean-architecture-couteau-suisse-du-code](https://easypartner.fr/blog/la-clean-architecture-couteau-suisse-du-code/)
[ArchUnit-Examples](https://github.com/TNG/ArchUnit-Examples/blob/main/example-plain/src/test/java/com/tngtech/archunit/exampletest/LayeredArchitectureTest.java)
