<?php

declare(strict_types=1);

namespace MiddleEarth\Fellowship;

class FellowshipMemberCombatScoreService implements FellowshipScoringInterface
{
    public function computeScores(array $data): array
    {
        $scores = [];
        foreach ($data as $memberId => $memberRecords) {
            foreach ($memberRecords as $key => $record) {
                switch (true) {
                    //Membres du Conseil qui ne sont pas Porteurs d'Anneau et bénis par les Valar : x1.5
                    case
                        (
                            (($record['race'] == 'homme' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && ($record['battles_fought'] <= 9) && $record['status'] != 'corrupted' && $record['status'] != 'ring_bearer')
                            || (
                                ((($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'cavalier' && $record['battles_fought'] >= 5)
                                    || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'archer' && $record['battles_fought'] >= 5))
                                && $record['status'] != 'ring_bearer'
                            )
                        )
                        && $record['blessed_by_valar'] == 1:
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 1.5;
                        break;

                    //Membres du Conseil qui sont Porteurs d'Anneau et bénis par les Valar : x2.5
                    case
                        (
                            (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'cavalier' && $record['battles_fought'] >= 5)
                            || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'archer' && $record['battles_fought'] >= 5)
                        )
                        && $record['status'] == 'ring_bearer' && $record['blessed_by_valar'] == 1:
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 2.5;
                        break;

                    //Membres du Conseil qui sont Porteurs d'Anneau à résistance prouvée : x2
                    case
                        (
                            (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'cavalier' && $record['battles_fought'] >= 5)
                            || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'archer' && $record['battles_fought'] >= 5)
                        )
                        && $record['status'] == 'ring_bearer'
                        && (($record['corruption_resistance'] <= 50 && $record['corruption_resistance'] !== null) || ($record['power_source'] == 'light' || $record['power_source'] == 'mithril' || $record['power_source'] == 'lembas'))
                        && $record['resistance_norm'] == 'valar_standard':
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 2;
                        break;

                    //Membres hors Conseil qui ne sont pas Porteurs d'Anneau mais bénis par les Valar : x2.5
                    case
                        (
                            (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN')
                                && $record['combat_class'] != 'cavalier'
                                && $record['combat_class'] != 'archer'
                            )
                            || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN')
                                && ($record['combat_class'] == 'cavalier' || $record['combat_class'] == 'archer')
                                && $record['battles_fought'] < 5
                            )
                        )
                        && $record['blessed_by_valar'] == 1:
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 2.5;
                        break;

                    //Membres hors Conseil à résistance prouvée : x2
                    case
                        (
                            (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN')
                                && $record['combat_class'] != 'cavalier'
                                && $record['combat_class'] != 'archer'
                            )
                            || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN')
                                && ($record['combat_class'] == 'cavalier' || $record['combat_class'] == 'archer')
                                && $record['battles_fought'] < 5
                            )
                        )
                        && (
                            ($record['corruption_resistance'] <= 50 && $record['corruption_resistance'] !== null)
                            || ($record['power_source'] == 'light' || $record['power_source'] == 'mithril' || $record['power_source'] == 'lembas')
                        )
                        && $record['resistance_norm'] == 'valar_standard':
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 2;
                        break;

                    //Membres du Conseil qui ne sont pas Porteurs d'Anneau à résistance prouvée : x1
                    case
                        (
                            (($record['race'] == 'homme' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['battles_fought'] <= 9 && $record['status'] != 'corrupted' && $record['status'] != 'ring_bearer')
                            || (
                                ((($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'cavalier' && $record['battles_fought'] >= 5)
                                    || (($record['race'] == 'elfe' || strtoupper($record['race']) == 'HOBBIT' || strtoupper($record['race']) == 'NAIN') && $record['combat_class'] == 'archer' && $record['battles_fought'] >= 5))
                                && $record['status'] != 'ring_bearer'
                            )
                        )
                        && (($record['corruption_resistance'] <= 50 && $record['corruption_resistance'] !== null) || ($record['power_source'] == 'light' || $record['power_source'] == 'mithril' || $record['power_source'] == 'lembas')):
                        $scores[$memberId][$key] = $record;
                        $scores[$memberId][$key]['combat_power'] = 1;
                        break;
                }
            }
        }

        return $scores;
    }
}
