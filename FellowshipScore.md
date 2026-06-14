# Fellowship Score — Spécification du kata

## Contexte

Bienvenue au Conseil de Guerre de Gondor. Nous gérons le système d'évaluation des membres actifs de la Communauté avant chaque grande bataille.

Le système reçoit les fiches de combat des membres, organisées par compagnie. Pour chaque fiche, il calcule un coefficient de puissance de combat (`combat_power`) selon les règles du Conseil. Les membres dont aucune règle ne s'applique sont exclus de l'évaluation — le Conseil ne peut pas se permettre d'envoyer des guerriers non classifiés.

---

## Les membres

Chaque fiche de combat contient les attributs suivants :

| Attribut | Valeurs possibles |
| --- | --- |
| `race` | `homme`, `elfe`, `hobbit`, `nain` |
| `combat_class` | `cavalier`, `archer`, ou autre |
| `battles_fought` | nombre entier |
| `status` | `ring_bearer`, `corrupted`, ou neutre |
| `blessed_by_valar` | `1` (oui) ou `0` (non) |
| `power_source` | `light`, `mithril`, `lembas`, `dark_fire`, ou autre |
| `corruption_resistance` | valeur numérique (ou null) |
| `resistance_norm` | `valar_standard` ou autre |

Les **hobbits** et les **nains** sont des races ambivalentes : selon leurs autres attributs, ils peuvent être classifiés aussi bien comme membres du Conseil que comme membres hors Conseil.

---

## Les règles de scoring

Les règles sont évaluées dans l'ordre. La première règle qui s'applique est retenue — les suivantes sont ignorées pour ce membre.

### ×1.5 — Membre du Conseil, non Porteur d'Anneau, béni par les Valar

S'applique aux **membres du Conseil** :
- `race` = `homme` (ou `hobbit`/`nain`) avec `battles_fought` ≤ 9, `status` ≠ `corrupted`, `status` ≠ `ring_bearer`
- OU `race` = `elfe` (ou `hobbit`/`nain`) avec `combat_class` = `cavalier` ou `archer` et `battles_fought` ≥ 5, `status` ≠ `ring_bearer`

ET béni par les Valar : `blessed_by_valar` = 1

### ×2.5 — Porteur d'Anneau béni par les Valar

S'applique aux **guerriers confirmés porteurs d'Anneau** :
- `race` = `elfe` (ou `hobbit`/`nain`) avec `combat_class` = `cavalier` ou `archer` et `battles_fought` ≥ 5
- ET `status` = `ring_bearer`

ET béni par les Valar : `blessed_by_valar` = 1

### ×2 — Porteur d'Anneau à résistance prouvée

S'applique aux **mêmes guerriers confirmés porteurs d'Anneau** (même critère de race/classe/batailles que ×2.5) :
- ET `status` = `ring_bearer`
- ET résistance mesurée selon la norme Valar : `resistance_norm` = `valar_standard` ET (`corruption_resistance` ≤ 50 OU `power_source` ∈ {`light`, `mithril`, `lembas`})

### ×2.5 — Membre hors Conseil béni par les Valar

S'applique aux **membres hors Conseil** :
- `race` = `elfe` (ou `hobbit`/`nain`) avec `combat_class` ≠ `cavalier` ET ≠ `archer`
- OU `race` = `elfe` (ou `hobbit`/`nain`) avec `combat_class` = `cavalier` ou `archer` mais `battles_fought` < 5

ET béni par les Valar : `blessed_by_valar` = 1

### ×2 — Membre hors Conseil à résistance prouvée

S'applique aux **mêmes membres hors Conseil** (même critère que ×2.5) :
- ET résistance mesurée selon la norme Valar : `resistance_norm` = `valar_standard` ET (`corruption_resistance` ≤ 50 OU `power_source` ∈ {`light`, `mithril`, `lembas`})

### ×1 — Membre du Conseil, non Porteur d'Anneau, à résistance prouvée

S'applique aux **mêmes membres du Conseil non porteurs d'Anneau** (même critère que ×1.5) :
- ET résistance mesurée selon la norme Valar : `resistance_norm` = `valar_standard` ET (`corruption_resistance` ≤ 50 OU `power_source` ∈ {`light`, `mithril`, `lembas`})

---

## Contraintes

- L'ordre d'évaluation des règles est défini par le Conseil et ne peut pas être modifié.

---

## Nouvelle exigence

Les Ents de la Forêt de Fangorn ont accepté d'envoyer des éclaireurs à la guerre. Leurs règles de scoring sont encore en négociation avec Sylvebarbe et ne sont pas encore implémentées.
