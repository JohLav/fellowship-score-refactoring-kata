package fellowship;

import java.util.HashMap;
import java.util.Map;

public class FellowshipMemberCombatScoreService implements FellowshipScoringInterface {

  @Override
  public Map<String, Map<Integer, Map<String, Object>>> computeScores(
      Map<String, Map<Integer, Map<String, Object>>> data) {
    Map<String, Map<Integer, Map<String, Object>>> scores = new HashMap<>();
    for (Map.Entry<String, Map<Integer, Map<String, Object>>> memberEntry : data.entrySet()) {
      String memberId = memberEntry.getKey();
      Map<Integer, Map<String, Object>> memberRecords = memberEntry.getValue();
      for (Map.Entry<Integer, Map<String, Object>> recordEntry : memberRecords.entrySet()) {
        Integer key = recordEntry.getKey();
        Map<String, Object> record = recordEntry.getValue();

        // Membres du Conseil qui ne sont pas Porteurs d'Anneau et bénis par les Valar : x1.5
        if (((("homme".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && (Integer) record.get("battles_fought") <= 9
                    && !"corrupted".equals(record.get("status"))
                    && !"ring_bearer".equals(record.get("status")))
                || (((("elfe".equals(record.get("race"))
                                || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                                || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                            && "cavalier".equals(record.get("combat_class"))
                            && (Integer) record.get("battles_fought") >= 5)
                        || (("elfe".equals(record.get("race"))
                                || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                                || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                            && "archer".equals(record.get("combat_class"))
                            && (Integer) record.get("battles_fought") >= 5))
                    && !"ring_bearer".equals(record.get("status"))))
            && Integer.valueOf(1).equals(record.get("blessed_by_valar"))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 1.5);
          scores.get(memberId).put(key, scored);
        }
        // Membres du Conseil qui sont Porteurs d'Anneau et bénis par les Valar : x2.5
        else if (((("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && "cavalier".equals(record.get("combat_class"))
                    && (Integer) record.get("battles_fought") >= 5)
                || (("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && "archer".equals(record.get("combat_class"))
                    && (Integer) record.get("battles_fought") >= 5))
            && "ring_bearer".equals(record.get("status"))
            && Integer.valueOf(1).equals(record.get("blessed_by_valar"))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 2.5);
          scores.get(memberId).put(key, scored);
        }
        // Membres du Conseil qui sont Porteurs d'Anneau à résistance prouvée : x2
        else if (((("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && "cavalier".equals(record.get("combat_class"))
                    && (Integer) record.get("battles_fought") >= 5)
                || (("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && "archer".equals(record.get("combat_class"))
                    && (Integer) record.get("battles_fought") >= 5))
            && "ring_bearer".equals(record.get("status"))
            && ((record.get("corruption_resistance") != null
                    && (Integer) record.get("corruption_resistance") <= 50)
                || ("light".equals(record.get("power_source"))
                    || "mithril".equals(record.get("power_source"))
                    || "lembas".equals(record.get("power_source"))))
            && "valar_standard".equals(record.get("resistance_norm"))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 2.0);
          scores.get(memberId).put(key, scored);
        }
        // Membres hors Conseil qui ne sont pas Porteurs d'Anneau mais bénis par les Valar : x2.5
        else if (((("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && !"cavalier".equals(record.get("combat_class"))
                    && !"archer".equals(record.get("combat_class")))
                || (("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && ("cavalier".equals(record.get("combat_class"))
                        || "archer".equals(record.get("combat_class")))
                    && (Integer) record.get("battles_fought") < 5))
            && Integer.valueOf(1).equals(record.get("blessed_by_valar"))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 2.5);
          scores.get(memberId).put(key, scored);
        }
        // Membres hors Conseil à résistance prouvée : x2
        else if (((("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && !"cavalier".equals(record.get("combat_class"))
                    && !"archer".equals(record.get("combat_class")))
                || (("elfe".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && ("cavalier".equals(record.get("combat_class"))
                        || "archer".equals(record.get("combat_class")))
                    && (Integer) record.get("battles_fought") < 5))
            && ((record.get("corruption_resistance") != null
                    && (Integer) record.get("corruption_resistance") <= 50)
                || ("light".equals(record.get("power_source"))
                    || "mithril".equals(record.get("power_source"))
                    || "lembas".equals(record.get("power_source"))))
            && "valar_standard".equals(record.get("resistance_norm"))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 2.0);
          scores.get(memberId).put(key, scored);
        }
        // Membres du Conseil qui ne sont pas Porteurs d'Anneau à résistance prouvée : x1
        else if (((("homme".equals(record.get("race"))
                        || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                        || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                    && (Integer) record.get("battles_fought") <= 9
                    && !"corrupted".equals(record.get("status"))
                    && !"ring_bearer".equals(record.get("status")))
                || (((("elfe".equals(record.get("race"))
                                || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                                || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                            && "cavalier".equals(record.get("combat_class"))
                            && (Integer) record.get("battles_fought") >= 5)
                        || (("elfe".equals(record.get("race"))
                                || "HOBBIT".equals(((String) record.get("race")).toUpperCase())
                                || "NAIN".equals(((String) record.get("race")).toUpperCase()))
                            && "archer".equals(record.get("combat_class"))
                            && (Integer) record.get("battles_fought") >= 5))
                    && !"ring_bearer".equals(record.get("status"))))
            && ((record.get("corruption_resistance") != null
                    && (Integer) record.get("corruption_resistance") <= 50)
                || ("light".equals(record.get("power_source"))
                    || "mithril".equals(record.get("power_source"))
                    || "lembas".equals(record.get("power_source"))))) {
          scores.computeIfAbsent(memberId, k -> new HashMap<>());
          Map<String, Object> scored = new HashMap<>(record);
          scored.put("combat_power", 1.0);
          scores.get(memberId).put(key, scored);
        }
      }
    }

    return scores;
  }
}
