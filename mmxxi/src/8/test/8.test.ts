import { getInputPath, readInput } from '../../util';
import { partone, parseDisplays } from '../8';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = partone(input);

    expect(result).toBe<number>(26);
});

test('part 2 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = parttwo(input);

    expect(result).toBe<number>(61229);
});

test('parse test', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = parseDisplays(input);

    expect(result.length).toBe<number>(10);
});
