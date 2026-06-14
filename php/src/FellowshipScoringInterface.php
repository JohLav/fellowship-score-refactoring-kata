<?php

declare(strict_types=1);

namespace Fellowship;

interface FellowshipScoringInterface
{
    /**
     * Calcule le combat_power de chaque fiche de membre.
     * Les membres auxquels aucune règle ne s'applique sont exclus du résultat.
     *
     * @param array<string, array<int, array<string, mixed>>> $data Fiches indexées par memberId
     * @return array<string, array<int, array<string, mixed>>> Fiches avec combat_power calculé
     */
    public function computeScores(array $data): array;
}
