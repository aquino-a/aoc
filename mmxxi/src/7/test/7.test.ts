import { readInput } from '../../util';
import { partone, parttwo } from '../7';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = partone(input);

    expect(result).toBe<number>(37);
});

test('part 2 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = parttwo(input);

    expect(result).toBe<number>(168);
});
