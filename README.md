#Application Gestion des Commandes en Java - Enterprise Edition
**************************************************************
Ce projet Java entreprise propose une gestion des commandes, mettant en œuvre des classes telles que User, Produit, Etat, CommandeProduit, Commande, et Client. Il utilise également plusieurs classes DAO pour interagir avec la base de données, telles que UserDAO, ProduitDAO, LoginDAO, LigneCommandeDAO, CommandeDAO, et ClientDAO.

- Classes Principales
Classe User
Package: com.example.ordercraftnew.Model

La classe User représente une entité utilisateur avec des informations telles que l'identifiant unique (id), l'adresse e-mail (email), le mot de passe (password), et le nom (name). Elle dispose de constructeurs paramétriques et par défaut, ainsi que de getters et setters.

- Classe Produit
Package: com.example.ordercraftnew.Model

La classe Produit représente un produit avec des détails tels que l'identifiant unique (id), le nom (name), la description (description), le prix (prix), et la quantité (quantite_produit). Elle dispose de constructeurs paramétriques et par défaut, ainsi que de getters et setters.

- Enumération Etat
L'énumération Etat définit les différents états possibles d'une entité dans l'application, tels que EN_COURS, TERMINEE, et ANNULEE.

- Classe CommandeProduit
La classe CommandeProduit représente l'association entre une commande et un produit. Elle contient des informations telles que l'identifiant unique (id), la commande associée (commande), le produit associé (produit), la quantité (quantite), et le prix total (prix_total).

- Classe Commande
La classe Commande représente une commande passée par un client avec des détails tels que l'identifiant unique (id), le client associé (client), l'adresse de livraison (address_livraison), la date (date), et l'état de la commande (etat_commande).

- Classe Client
La classe Client représente un client avec des informations telles que l'identifiant unique (id), le nom (name), l'e-mail (email), et la ville (ville).

#Classes DAO Principales
- Classe UserDAO
Implémentation de InterfaceDAOUser pour la gestion des utilisateurs dans la base de données. Elle offre des méthodes telles que add, delete, update, getById, getAll, et extractFromResultSet.

- Classe ProduitDAO
Implémentation de InterfaceDAOProduit pour la gestion des produits dans la base de données. Elle propose des méthodes comme add, delete, update, getById, getAll, et extractFromResultSet, avec des requêtes SQL associées.

- Classe LoginDAO
Implémentation de InterfaceLoginDAO pour l'authentification des utilisateurs et l'accès aux données utilisateur dans la base de données. Elle offre des méthodes telles que login, getUserByEmail, et addUser, avec une requête SQL pour la connexion utilisateur.

- Classe LigneCommandeDAO
Implémentation de InterfaceDAOLigneCommande pour la gestion des lignes de commande dans la base de données. Elle propose des méthodes telles que add, getById, getAll, extractFromResultSet, et updateQuantityWithPrixTotal, avec une requête SQL associée.

- Classe CommandeDAO
Implémentation de InterfaceDAOCommande pour la gestion des commandes dans la base de données. Elle offre des méthodes comme add, delete, update, getById, getAll, extractFromResultSet, et updateEtat, avec plusieurs requêtes SQL.

- Classe ClientDAO
Implémentation de InterfaceDAOClient pour la gestion des clients dans la base de données. Elle propose des méthodes telles que add, delete, update, getById, getAll, et extractFromResultSet.

#Méthodes de UserServlet
- showFormUser(HttpServletRequest req, HttpServletResponse resp)
  Redirige la requête pour afficher le formulaire utilisateur ("/user-form.jsp").
 - add(HttpServletRequest req, HttpServletResponse resp)
   Extrait les données de l'utilisateur de la requête, crée un nouvel objet User, l'ajoute à la base de données à l'aide de UserDAO, puis redirige vers la liste des utilisateurs.
- delete(HttpServletRequest req, HttpServletResponse resp)
  Extrait l'ID de l'utilisateur de la requête, supprime l'utilisateur correspondant à l'aide de UserDAO, puis redirige vers la liste des utilisateurs.
- showFormEdit(HttpServletRequest req, HttpServletResponse resp)
  Extrait l'ID de l'utilisateur de la requête, récupère l'utilisateur correspondant dans la base de données à l'aide de UserDAO, puis redirige la requête pour afficher le formulaire d'édition de l'utilisateur ("/user-form.jsp").
- update(HttpServletRequest req, HttpServletResponse resp)
 Extrait les données de l'utilisateur de la requête, crée un nouvel objet User, met à jour l'utilisateur correspondant dans la base de données à l'aide de UserDAO, puis redirige vers la liste des utilisateurs.
- listUser(HttpServletRequest req, HttpServletResponse resp)
Description : Récupère la liste de tous les utilisateurs de la base de données à l'aide de UserDAO et redirige la requête pour afficher la liste des utilisateurs ("/users.jsp").

++++++ Avec un switch qui la gestion des ces methodes dans doGet() par getServletPath() +++++++++++

#Dans les autres class il fait la méme concepte sauf la class login et logout qui contient des methodes qui fait ces fonctionalite et la class commandeProduitServlet continent deux important methode :

++++++ addQuantity(HttpServletRequest req, HttpServletResponse resp)
  Extrait l'ID du produit, l'ID de la commande produit et la quantité à ajouter à partir de la requête. Met à jour la quantité commandée et le prix total de la ligne de 
  commande dans la base de données.
  
+++++ updateEtat(HttpServletRequest req, HttpServletResponse resp)
  Extrait les informations de la commande à partir de la requête, puis met à jour l'état de la commande dans la base de donné

#Diagramme class :


![Class Diagram Realization Example](https://github.com/Mouslih0/jee-order-craft/assets/106397107/91fb1538-4669-4d5c-8594-9477819c10ec)













