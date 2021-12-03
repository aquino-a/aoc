import { getInputPath, readInput } from '../util';
import { partone, parttwo, high, low } from './3';

readInput(getInputPath()).then(input => {
    const report = partone(input);
    console.log(report.epsilon * report.gamma);
});

readInput(getInputPath()).then(input => {
    const highResult = parttwo(high, input, 0);
    const lowResult = parttwo(low, input, 0);

    console.log(highResult * lowResult);
});
