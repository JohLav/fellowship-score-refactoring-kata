package fellowship;

import java.util.Map;

public interface FellowshipScoringInterface {
  /**
   * Calcule le combat_power de chaque fiche de membre. Les membres auxquels aucune règle ne
   * s'applique sont exclus du résultat.
   *
   * @param data Fiches indexées par memberId
   * @return Fiches avec combat_power calculé
   */
  Map<String, Map<Integer, Map<String, Object>>> computeScores(
      Map<String, Map<Integer, Map<String, Object>>> data);
}
