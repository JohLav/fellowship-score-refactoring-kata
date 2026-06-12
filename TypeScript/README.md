# Fellowship Score — Version TypeScript

Kata de refactoring sur `FellowshipMemberCombatScoreService`. Voir [FellowshipScore.md](../FellowshipScore.md) pour la spécification complète.

## Prérequis

- [Docker](https://docs.docker.com/get-docker/)

Aucune installation de Node ou npm n'est nécessaire : tout tourne dans un conteneur.

## Lancer les tests

```shell
cd TypeScript/
make test
```

## Structure

```text
src/
├── FellowshipScoringInterface.ts   ← ne pas modifier
└── FellowshipMemberCombatScoreService.ts   ← à refactorer
test/
└── FellowshipMemberCombatScoreService.spec.ts
```

## Autres commandes

```shell
make install   # installe les dépendances npm sans lancer les tests
```
