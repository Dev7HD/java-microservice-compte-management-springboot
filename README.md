# Microservice de Gestion des Comptes

## Description
Ce projet est un microservice de gestion des comptes bancaires développé en utilisant **Spring Boot**. Il inclut les fonctionnalités de création, consultation, mise à jour, et suppression de comptes via une API Rest, avec une persistance des données gérée par **Spring Data JPA** et **H2**. Le projet inclut également une exposition des API via **Spring Data Rest**, des **projections**, et une intégration de **GraphQL** pour des requêtes avancées.

## Fonctionnalités
- **Création d'un microservice en Spring Boot**
- Gestion des comptes avec persistance JPA
- API Rest exposée via Spring Data Rest et des projections
- Intégration de **GraphQL**
- Documentation et test des API avec Swagger
- Tests des endpoints avec Postman

## Prérequis
- **JDK 17+**
- **Maven 3.8+**
- **IDE** (IntelliJ, Eclipse, ou autre)
- **Postman** (pour tester les API Rest)

## Dépendances utilisées
- **Spring Boot Starter Web**: Pour créer le web service Rest.
- **Spring Data JPA**: Pour la gestion de la persistance des données.
- **H2 Database**: Base de données en mémoire pour les tests.
- **Lombok**: Simplification du code en évitant le boilerplate.
- **Spring Data Rest**: Pour exposer les API automatiquement.
- **GraphQL**: Pour exposer les données via une API GraphQL.
- **Swagger**: Pour documenter les API Rest.

## Instructions d'installation et d'exécution

1. **Cloner le projet**:
   ```bash
   git clone https://github.com/Dev7HD/microservice-compte-management-springboot.git
   cd microservice-compte-management-springboot
   ```

2. **Construire le projet**:
   ```bash
   mvn clean install
   ```

3. **Exécuter le projet**:
   ```bash
   mvn spring-boot:run
   ```

4. **Accéder à l'interface Swagger** pour tester les API:
    - Ouvrez votre navigateur à l'adresse: `http://localhost:8080/swagger-ui/index.html`

5. **Tester les API avec Postman**:
    - Importez la collection Postman fournie dans le projet ou créez manuellement les requêtes REST.

## Fonctionnalités techniques
1. **Entité JPA `Compte`**: Représente un compte bancaire avec des attributs comme `id`, `balance`, `typeCompte`, etc.

2. **DAO `CompteRepository`**: Interface Spring Data pour la gestion des accès aux données.

3. **API Rest avec Spring Boot**:
    - Création, mise à jour, suppression, et récupération des comptes via des endpoints REST.
    - Documentation via Swagger.

4. **Projections avec Spring Data Rest**: Expose des vues spécifiques des entités sans retourner tous les champs.

5. **GraphQL API**: Permet des requêtes et mutations sur les comptes.

6. **Tests DAO**: Validations et tests unitaires de la couche DAO.

## Auteurs
- **Hamza DAMIRI**
- **II-BDCC, ENSET de Mohammedia**
