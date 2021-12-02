import { readInput } from '../../util';
import { getDepthChanges } from '../parttwo';
import { Change } from '../partone';

test('part two test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const depths = input.map(s => parseInt(s));
    const increaseCount = getDepthChanges(depths).filter(
        d => d == Change.increased
    ).length;

    expect(increaseCount).toBe<number>(5);
});
