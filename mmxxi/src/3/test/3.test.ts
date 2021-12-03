import { readInput } from '../../util';
import { partone, parttwo, high, low } from '../3';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const report = partone(input);

    expect(report.epsilon * report.gamma).toBe<number>(198);
});

test('part 2 test input high', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const lifeSupport = parttwo(high, input, 0);

    expect(lifeSupport).toBe<number>(23);
});

test('part 2 test input low', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const lifeSupport = parttwo(low, input, 0);

    expect(lifeSupport).toBe<number>(10);
});
