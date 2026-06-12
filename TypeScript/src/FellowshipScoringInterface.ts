/**
 * Calcule le combat_power de chaque fiche de membre.
 * Les membres auxquels aucune règle ne s'applique sont exclus du résultat.
 *
 * @param data Fiches indexées par memberId
 * @returns Fiches avec combat_power calculé
 */
export interface FellowshipScoringInterface {
    computeScores(data: Record<string, Record<number, Record<string, unknown>>>): Record<string, Record<number, Record<string, unknown>>>;
}
