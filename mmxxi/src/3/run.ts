import { getInputPath, readInput } from '../util';
import { partone, parttwo } from './3';

readInput(getInputPath()).then(input => {
    const report = partone(input);
    console.log(report.epsilon * report.gamma);
});

readInput(getInputPath()).then(input => {
    const result = parttwo(input);
    console.log(result);
});
