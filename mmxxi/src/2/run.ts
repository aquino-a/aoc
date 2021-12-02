import { getInputPath, readInput } from '../util';
import { partone, parseCommands, parttwo } from './2';

readInput(getInputPath()).then(input => {
    console.log(partone(parseCommands(input)));
});

readInput(getInputPath()).then(input => {
    console.log(parttwo(parseCommands(input)));
});
