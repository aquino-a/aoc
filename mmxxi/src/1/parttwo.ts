import { getInputPath, readInput } from '../util';
import { change } from './partone';

readInput(getInputPath()).then(input => {
    const depths = input.map(s => parseInt(s));
    const increaseCount = getDepthChanges(depths).filter(
        d => d == change.increased
    ).length;
    console.log(increaseCount);
});

export const getDepthChanges = (depths: number[]): change[] => {
    const changes = [change.na];
    let prev = Number.MAX_SAFE_INTEGER;

    for (let i = 2; i < depths.length; i++) {
        let sum = depths[i - 2];
        sum += depths[i - 1];
        sum += depths[i];

        if (sum > prev) {
            changes.push(change.increased);
        } else if (sum < prev) {
            changes.push(change.decreased);
        } else changes.push(change.na);

        prev = sum;
    }
    return changes;
};
