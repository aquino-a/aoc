import { readInput } from '../../util';
import { partone, rateHigh, rateLow } from '../3';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const report = partone(input);

    expect(report.epsilon * report.gamma).toBe<number>(198);
});

test('part 2 test rate high', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const lifeSupport = rateHigh(input, 0);

    expect(lifeSupport).toBe<number>(23);
});

test('part 2 test rate low', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const lifeSupport = rateLow(input, 0);

    expect(lifeSupport).toBe<number>(10);
});
