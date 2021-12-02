import { getInputPath, readInput } from '../util';

readInput(getInputPath()).then(input => {
    const depths = input.map(s => parseInt(s));
    const increaseCount = getDepthChanges(depths).filter(
        d => d == Change.increased
    ).length;
    console.log(increaseCount);
});

export const getDepthChanges = (depths: number[]): Change[] => {
    const changes = [Change.na];

    for (let i = 1; i < depths.length; i++) {
        const p = depths[i - 1];
        const c = depths[i];

        if (c > p) {
            changes.push(Change.increased);
        } else if (c < p) {
            changes.push(Change.decreased);
        } else changes.push(Change.na);
    }
    return changes;
};

export enum Change {
    na,
    increased,
    decreased,
}
