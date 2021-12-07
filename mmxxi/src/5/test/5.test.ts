import { readInput } from '../../util';
import { partone } from '../5';

test('part 1 test input', async () => {
    const input = await readInput(__dirname + '/testInput.txt');
    const result = partone(input);

    expect(result).toBe<number>(5);
});
