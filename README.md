# API d'Historique des Messages

Cette API permet de récupérer des messages et des conversations entre utilisateurs.

## Prérequis

### Lancer Zookeeper

`
  - Aller dans le dossier de Zookeper
  - /bin
  - ./zkServer (bash ou sh en fonction linux/Windows)

`

### Lancer Kafka

- Aller dans le dossier Kafka
- /bin
- ./kafka-server-start.sh ../config/server.properties
- Créer le topic si ce n'est aps encore fait
-     ./kafka-topics.sh --create --topic conversations --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

### Lancer le microservice

- Aller dans le dossier du projet
- Vérifier le port utilisé dans application.properties
- mvn spring-boot:run




## Les endpoints

### GET /messages/{receiverId}

Permet de récupérer tout les messages reçu par un identifiant particulier

### GET /messages/conversation

Permet d'avoir les conversations entre 1 envoyeur et un receveur

Query Parameters :
senderId : identifiant de l'expéditeur
receiverId : identifiant du destinataire

#### Exemple

Requête :

GET http://localhost:8082/api/history/messages/conversation?senderId=user1&receiverId=user2

Réponse attendue (JSON) :
    
    [
        {
            "id": 1,
            "senderId": "user1",
            "receiverId": "user2",
            "content": "Salut, comment vas-tu ?"
        }
    ]



### GET /messages/conversation/bidirectional

Permet d'avoir la conversation entre 2 utilisateurs par leurs identifiants.

Query Parameters :
user1 : identifiant du premier utilisateur
user2 : identifiant du second utilisateur

GET http://localhost:8082/api/history/messages/conversation/bidirectional?user1=user1&user2=user2

Réponse attendue (JSON) :
    
     [
      {
          "id": 1,
          "senderId": "user1",
          "receiverId": "user2",
          "content": "Salut, comment vas-tu ?"
      },
      {
          "id": 2,
          "senderId": "user2",
          "receiverId": "user1",
          "content": "Ça va bien, et toi ?"
      }
  ]



