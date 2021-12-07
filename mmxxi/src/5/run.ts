import { getInputPath, readInput } from '../util';
import { partone, parttwo } from './5';

readInput(getInputPath()).then(input => {
    const result = partone(input);
    console.log(result);
});

readInput(getInputPath()).then(input => {
    const result = parttwo(input);
    console.log(result);
});
