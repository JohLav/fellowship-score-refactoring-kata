import { FellowshipScoringInterface } from './FellowshipScoringInterface';

type MemberRecord = Record<string, unknown>;
type MemberRecords = Record<string, MemberRecord>;
type MembersData = Record<string, MemberRecords>;

export class FellowshipMemberCombatScoreService implements FellowshipScoringInterface {
    computeScores(data: MembersData): MembersData {
        const scores: MembersData = {};
        for (const memberId in data) {
            const memberRecords = data[memberId];
            for (const key in memberRecords) {
                const record = memberRecords[key];
                switch (true) {
                    //Membres du Conseil qui ne sont pas Porteurs d'Anneau et bénis par les Valar : x1.5
                    case (
                        (
                            ((record['race'] == 'homme' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && (record['battles_fought'] as number) <= 9 && record['status'] != 'corrupted' && record['status'] != 'ring_bearer')
                            || (
                                ((((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'cavalier' && (record['battles_fought'] as number) >= 5)
                                    || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'archer' && (record['battles_fought'] as number) >= 5))
                                    && record['status'] != 'ring_bearer'
                                )
                            )
                        )
                        && record['blessed_by_valar'] == 1
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 1.5;
                        break;

                    //Membres du Conseil qui sont Porteurs d'Anneau et bénis par les Valar : x2.5
                    case (
                        (
                            ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'cavalier' && (record['battles_fought'] as number) >= 5)
                            || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'archer' && (record['battles_fought'] as number) >= 5)
                        )
                        && record['status'] == 'ring_bearer' && record['blessed_by_valar'] == 1
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 2.5;
                        break;

                    //Membres du Conseil qui sont Porteurs d'Anneau à résistance prouvée : x2
                    case (
                        (
                            ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'cavalier' && (record['battles_fought'] as number) >= 5)
                            || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'archer' && (record['battles_fought'] as number) >= 5)
                        )
                        && record['status'] == 'ring_bearer'
                        && (((record['corruption_resistance'] as number) <= 50 && record['corruption_resistance'] !== null) || (record['power_source'] == 'light' || record['power_source'] == 'mithril' || record['power_source'] == 'lembas'))
                        && record['resistance_norm'] == 'valar_standard'
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 2;
                        break;

                    //Membres hors Conseil qui ne sont pas Porteurs d'Anneau mais bénis par les Valar : x2.5
                    case (
                        (
                            ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN')
                                && record['combat_class'] != 'cavalier'
                                && record['combat_class'] != 'archer'
                            )
                            || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN')
                                && (record['combat_class'] == 'cavalier' || record['combat_class'] == 'archer')
                                && (record['battles_fought'] as number) < 5
                            )
                        )
                        && record['blessed_by_valar'] == 1
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 2.5;
                        break;

                    //Membres hors Conseil à résistance prouvée : x2
                    case (
                        (
                            ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN')
                                && record['combat_class'] != 'cavalier'
                                && record['combat_class'] != 'archer'
                            )
                            || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN')
                                && (record['combat_class'] == 'cavalier' || record['combat_class'] == 'archer')
                                && (record['battles_fought'] as number) < 5
                            )
                        )
                        && (
                            ((record['corruption_resistance'] as number) <= 50 && record['corruption_resistance'] !== null)
                            || (record['power_source'] == 'light' || record['power_source'] == 'mithril' || record['power_source'] == 'lembas')
                        )
                        && record['resistance_norm'] == 'valar_standard'
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 2;
                        break;

                    //Membres du Conseil qui ne sont pas Porteurs d'Anneau à résistance prouvée : x1
                    case (
                        (
                            ((record['race'] == 'homme' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && (record['battles_fought'] as number) <= 9 && record['status'] != 'corrupted' && record['status'] != 'ring_bearer')
                            || (
                                ((((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'cavalier' && (record['battles_fought'] as number) >= 5)
                                    || ((record['race'] == 'elfe' || String(record['race']).toUpperCase() == 'HOBBIT' || String(record['race']).toUpperCase() == 'NAIN') && record['combat_class'] == 'archer' && (record['battles_fought'] as number) >= 5))
                                    && record['status'] != 'ring_bearer'
                                )
                            )
                        )
                        && (((record['corruption_resistance'] as number) <= 50 && record['corruption_resistance'] !== null) || (record['power_source'] == 'light' || record['power_source'] == 'mithril' || record['power_source'] == 'lembas'))
                    ):
                        if (!scores[memberId]) scores[memberId] = {};
                        scores[memberId][key] = { ...record };
                        scores[memberId][key]['combat_power'] = 1;
                        break;
                }
            }
        }

        return scores;
    }
}
