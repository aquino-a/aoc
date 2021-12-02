import { readInput } from '../../util';
import { partone, parseCommands, parttwo } from '../2';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = partone(parseCommands(input));

    expect(result).toBe<number>(150);
});

test('part 2 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = parttwo(parseCommands(input));

    expect(result).toBe<number>(900);
});
