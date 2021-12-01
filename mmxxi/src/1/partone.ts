import { getInputPath, readInput } from '../util';

readInput(getInputPath()).then(input => {
    const depths = input.map(s => parseInt(s));
    const increaseCount = getDepthChanges(depths).filter(
        d => d == change.increased
    ).length;
    console.log(increaseCount);
});

export const getDepthChanges = (depths: number[]): change[] => {
    const changes = [change.na];

    for (let i = 1; i < depths.length; i++) {
        const p = depths[i - 1];
        const c = depths[i];

        if (c > p) {
            changes.push(change.increased);
        } else if (c < p) {
            changes.push(change.decreased);
        } else changes.push(change.na);
    }
    return changes;
};

export enum change {
    na,
    increased,
    decreased,
}
