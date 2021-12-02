import { getInputPath, readInput } from '../util';
import { Change } from './partone';

readInput(getInputPath()).then(input => {
    const depths = input.map(s => parseInt(s));
    const increaseCount = getDepthChanges(depths).filter(
        d => d == Change.increased
    ).length;
    console.log(increaseCount);
});

export const getDepthChanges = (depths: number[]): Change[] => {
    const changes = [];
    let prev: number;

    for (let i = 2; i < depths.length; i++) {
        let sum = depths[i - 2];
        sum += depths[i - 1];
        sum += depths[i];

        if (sum > prev) {
            changes.push(Change.increased);
        } else if (sum < prev) {
            changes.push(Change.decreased);
        } else changes.push(Change.na);

        prev = sum;
    }
    return changes;
};
