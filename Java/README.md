# Fellowship Score — Version Java

Kata de refactoring sur `FellowshipMemberCombatScoreService`. Voir [FellowshipScore.md](../FellowshipScore.md) pour la spécification complète.

## Prérequis

- [Docker](https://docs.docker.com/get-docker/)

Aucune installation de Java ou Maven n'est nécessaire : tout tourne dans un conteneur.

## Lancer les tests

```shell
cd Java/
make test
```

## Structure

```text
src/
├── main/java/middleearth/fellowship/service/
│   ├── FellowshipScoringInterface.java   ← ne pas modifier
│   └── FellowshipMemberCombatScoreService.java   ← à refactorer
└── test/java/middleearth/fellowship/service/
    └── FellowshipMemberCombatScoreServiceTest.java
```

## Autres commandes

```shell
make install   # télécharge les dépendances Maven sans lancer les tests
```
