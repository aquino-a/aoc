import { readInput } from '../../util';
import { partone, parttwo, score } from '../10';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = partone(input);

    expect(result).toBe<number>(26397);
});

test('part 2 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = parttwo(input);

    expect(result).toBe<number>(288957);
});

test('score test', () => {
    const result = score([']', ')', '}', '>']);

    expect(result).toBe<number>(294);
});
