# Fellowship Score — Version PHP

Kata de refactoring sur `FellowshipMemberCombatScoreService`. Voir [FellowshipScore.md](../FellowshipScore.md) pour la spécification complète.

## Prérequis

- [Docker](https://docs.docker.com/get-docker/)

Aucune installation de PHP ou Composer n'est nécessaire : tout tourne dans un conteneur.

## Lancer les tests

```shell
cd php/
make test
```

## Structure

```text
src/
├── FellowshipScoringInterface.php   ← ne pas modifier
└── FellowshipMemberCombatScoreService.php   ← à refactorer
tests/
└── FellowshipMemberCombatScoreServiceTest.php
```

## Autres commandes

```shell
make install   # installe les dépendances Composer sans lancer les tests
make update    # met à jour les dépendances
```
