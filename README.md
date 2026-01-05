# 🚕 Taxi Booking - MVC Legacy Project

> **Projet pédagogique** : Application de réservation de taxi en architecture MVC "legacy" pour démontrer les principes de refactoring vers une Clean Architecture.

## 📋 Contexte

Ce projet sert de base pour un cours de 15 heures sur **l'architecture durable** couvrant :
- Clean Architecture
- Domain-Driven Design (DDD)
- Test-Driven Development (TDD)
- Principes SOLID
- Design Patterns

## ⚠️ État du code

**Ce code est volontairement mal conçu** pour illustrer les problèmes d'une architecture MVC couplée :
- Logique métier dans les contrôleurs
- Couplage fort avec la base de données
- Pas d'abstractions
- Noms de variables peu explicites
- Aucune séparation des responsabilités
- Pas de tests

## 🎯 Fonctionnalités

### Version initiale (v0.0-initial)
1. **Créer un taxi** - Enregistrement d'un nouveau taxi
2. **Lister les taxis disponibles** - Affichage des taxis libres
3. **Réserver un taxi** - Création d'une réservation
4. **Calculer le prix** - Tarification basique
5. **Envoyer une notification** - Email/SMS 10 minutes avant
6. **Consulter ses réservations** - Historique client
7. **Libérer un taxi** - Changement de statut après course

## 🛠️ Stack technique

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL** (via Docker)
- **H2** (pour les tests)
- **Gradle**

## 🚀 Démarrage rapide

### Prérequis
- Java 21
- Docker & Docker Compose
- Gradle (wrapper inclus)

### Installation

1. **Cloner le projet**
```bash
git clone [URL_DU_REPO]
cd taxi-booking
```

2. **Démarrer PostgreSQL**
```bash
docker-compose up -d
```

3. **Lancer l'application**
```bash
./gradlew bootRun
```

L'application démarre sur `http://localhost:8080`

## 📚 Progression du cours

Le projet évoluera à travers plusieurs itérations :

- **v0.0-initial** : Code MVC couplé (point de départ)
- **v1.0-extract-business-logic** : Extraction de la logique métier
- **v2.0-tdd-pricing** : Mise en pratique du TDD
- **v3.0-clean-architecture** : Mise en place de la clean architecture dans le projet
- **v4.0-rating-feature** : Ajout de la features de note pour les taxis

## 🎓 Objectifs pédagogiques

Les étudiants apprendront à :
1. Identifier les problèmes d'une architecture couplée
2. Refactorer progressivement vers une Clean Architecture
3. Appliquer les principes SOLID
4. Mettre en place des tests automatisés
5. Implémenter des Design Patterns pertinents

## 📖 Design Patterns abordés

- **Strategy** - Calcul de prix
- **Factory** - Création d'objets
- **Repository** - Abstraction de persistance
- **Adapter** - Ports & Adapters
  
## 🐳 Docker
```bash
# Démarrer la base de données
docker-compose up -d

# Arrêter
docker-compose down

# Voir les logs
docker-compose logs -f
```

## 🧪 Tests
```bash
# Lancer les tests
./gradlew test

# Avec couverture
./gradlew test jacocoTestReport
```

## 📝 License

Projet éducatif - Master 2 Web Engineering

## 👨‍🏫 Auteur

Projet file rouge - Cours Architecture Durable
