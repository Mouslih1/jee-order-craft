#application gestion des commandes en Java entreprise edition

Classe User
Package : com.example.ordercraftnew.Model
La classe User représente une entité utilisateur au sein de l'application. Elle encapsule les informations de l'utilisateur telles que leur identifiant unique (id), leur adresse e-mail (email), leur mot de passe (password), et leur nom (name) avec construteur parametrique et par default, getters , setters.


Classe Produit
Package : com.example.ordercraftnew.Model
La classe Produit représente un produit au sein de l'application. Elle contient des informations telles que l'identifiant unique (id), le nom (name), la description (description), le prix (prix), et la quantité de produit (quantite_produit) avec construteur parametrique et par default, getters , setters.


Enumération Etat
L'énumération Etat représente les différents états possibles d'une entité dans l'application. Elle est utilisée pour définir l'état actuel d'une commande, d'une transaction, ou d'une autre entité.

Valeurs possibles :
EN_COURS :
Indique que l'entité est en cours, ce qui signifie qu'elle est actuellement en progression ou en traitement.
TERMINEE :
Indique que l'entité a été complétée avec succès. Cet état est atteint une fois que toutes les opérations associées sont terminées.
ANNULEE :
Indique que l'entité a été annulée. Cela peut se produire en raison d'une annulation de commande, d'une transaction non aboutie, ou d'autres circonstances similaires.

Classe CommandeProduit
La classe CommandeProduit représente une association entre une commande et un produit au sein de l'application. Elle contient des informations telles que l'identifiant unique (id), la commande associée (commande), le produit associé (produit), la quantité (quantite), et le prix total (prix_total) avec construteur parametrique et par default, getters , setters.

Classe Commande
La classe Commande représente une commande passée par un client dans l'application. Elle contient des informations telles que l'identifiant unique (id), le client associé (client), l'adresse de livraison (address_livraison), la date de la commande (date), et l'état de la commande (etat_commande) avec construteur parametrique et par default, getters , setters.

Classe Client
La classe Client représente un client dans l'application. Elle contient des informations telles que l'identifiant unique (id), le nom (name), l'email (email), et la ville (ville) du client avec construteur parametrique et par default, getters , setters.

- Classe UserDAO
La classe UserDAO est une implémentation de l'interface InterfaceDAOUser qui gère l'accès aux données des utilisateurs dans la base de données.

Méthodes :
add(User user): void

Ajoute un nouvel utilisateur à la base de données en utilisant les informations fournies dans l'objet User.
delete(int id): boolean

Supprime l'utilisateur de la base de données en utilisant son identifiant (id).
update(User user): boolean

Met à jour les informations de l'utilisateur dans la base de données en utilisant les nouvelles informations fournies dans l'objet User.
getById(int id): User

Récupère les informations d'un utilisateur de la base de données en utilisant son identifiant (id).
getAll(): List<User>

Récupère la liste de tous les utilisateurs présents dans la base de données.
extractFromResultSet(ResultSet resultSet): User

Extrait les informations d'un utilisateur à partir d'un objet ResultSet retourné par une requête SQL.

 - Classe ProduitDAO
La classe ProduitDAO est une implémentation de l'interface InterfaceDAOProduit qui gère l'accès aux données des produits dans la base de données.

Méthodes :
add(Produit produit): void

Ajoute un nouveau produit à la base de données en utilisant les informations fournies dans l'objet Produit.
delete(int id): boolean

Supprime le produit de la base de données en utilisant son identifiant (id).
update(Produit produit): boolean

Met à jour les informations du produit dans la base de données en utilisant les nouvelles informations fournies dans l'objet Produit.
getById(int id): Produit

Récupère les informations d'un produit de la base de données en utilisant son identifiant (id).
getAll(): List<Produit>

Récupère la liste de tous les produits présents dans la base de données.
extractFromResultSet(ResultSet resultSet): Produit

Extrait les informations d'un produit à partir d'un objet ResultSet retourné par une requête SQL.
Requêtes SQL :
INSERT_PRODUIT :

Requête SQL pour insérer un nouveau produit dans la base de données.
DELETE_PRODUIT :

Requête SQL pour supprimer un produit de la base de données.
UPDATE_PRODUIT :

Requête SQL pour mettre à jour les informations d'un produit dans la base de données.
GET_PRODUIT_BY_ID :

Requête SQL pour récupérer les informations d'un produit en utilisant son identifiant.
GET_ALL_PRODUITS :

Requête SQL pour récupérer la liste de tous les produits présents dans la base de données.

- Classe LoginDAO
La classe LoginDAO est une implémentation de l'interface InterfaceLoginDAO qui gère l'authentification des utilisateurs et l'accès aux données utilisateur dans la base de données.

Méthodes :
login(String email, String password): boolean

Vérifie l'authenticité d'un utilisateur en comparant l'email fourni avec le mot de passe associé dans la base de données.
getUserByEmail(String email): User

Récupère les informations d'un utilisateur à partir de la base de données en utilisant l'email.
addUser(User user): void

Ajoute un nouvel utilisateur à la base de données en utilisant les informations fournies dans l'objet User.
Requêtes SQL :
LOGIN_USER :
Requête SQL pour récupérer les informations d'un utilisateur en utilisant son email lors de la tentative de connexion.

- Classe LigneCommandeDAO
La classe LigneCommandeDAO est une implémentation de l'interface InterfaceDAOLigneCommande qui gère l'accès aux données des lignes de commande dans la base de données.

Méthodes :
add(CommandeProduit commandeProduit): CommandeProduit

Ajoute une nouvelle ligne de commande à la base de données en utilisant les informations fournies dans l'objet CommandeProduit.
getById(int id): CommandeProduit

Récupère les informations d'une ligne de commande de la base de données en utilisant son identifiant (id).
getAll(): List<CommandeProduit>

Récupère la liste de toutes les lignes de commande présentes dans la base de données.
extractFromResultSet(ResultSet resultSet): CommandeProduit

Extrait les informations d'une ligne de commande à partir d'un objet ResultSet retourné par une requête SQL.
updateQuantityWithPrixTotal(CommandeProduit commandeProduit, Produit produit): void

Met à jour la quantité commandée et le prix total d'une ligne de commande dans la base de données.

UPDATE_QUANTITY_COMMANDER :
Requête SQL pour mettre à jour la quantité commandée et le prix total d'une ligne de commande dans la base de données.

- Classe CommandeDAO
La classe CommandeDAO est une implémentation de l'interface InterfaceDAOCommande qui gère l'accès aux données des commandes dans la base de données.

Méthodes :
add(Commande commande): Commande

Ajoute une nouvelle commande à la base de données en utilisant les informations fournies dans l'objet Commande.
delete(int id): boolean

Supprime une commande de la base de données en utilisant son identifiant (id).
update(Commande commande): boolean

Met à jour les informations d'une commande dans la base de données.
getById(int id): Commande

Récupère les informations d'une commande à partir de la base de données en utilisant son identifiant (id).
getAll(): List<Commande>

Récupère la liste de toutes les commandes présentes dans la base de données.
extractFromResultSet(ResultSet resultSet): Commande

Extrait les informations d'une commande à partir d'un objet ResultSet retourné par une requête SQL.
updateEtat(Commande commande): void

Met à jour l'état d'une commande dans la base de données.
Requêtes SQL :
INSERT_COMMANDE :

Requête SQL pour insérer une nouvelle commande dans la base de données.
DELETE_COMMANDE :

Requête SQL pour supprimer une commande de la base de données en utilisant son identifiant.
UPDATE_COMMANDE :

Requête SQL pour mettre à jour les informations d'une commande dans la base de données.
GET_COMMANDE_BY_ID :

Requête SQL pour récupérer les informations d'une commande en utilisant son identifiant.
GET_ALL_COMMANDES :

Requête SQL pour récupérer la liste de toutes les commandes présentes dans la base de données.
UPDATE_ETAT :

Requête SQL pour mettre à jour l'état d'une commande dans la base de données.

- Classe ClientDAO
La classe ClientDAO est une implémentation de l'interface InterfaceDAOClient qui gère l'accès aux données des clients dans la base de données.

Méthodes :
add(Client client): void

Ajoute un nouveau client à la base de données en utilisant les informations fournies dans l'objet Client.
delete(int id): boolean

Supprime un client de la base de données en utilisant son identifiant (id).
update(Client client): boolean

Met à jour les informations d'un client dans la base de données.
getById(int id): Client

Récupère les informations d'un client à partir de la base de données en utilisant son identifiant (id).
getAll(): List<Client>

Récupère la liste de tous les clients présents dans la base de données.
extractFromResultSet(ResultSet resultSet): Client

Extrait les informations d'un client à partir d'un objet ResultSet retourné par une requête SQL.







