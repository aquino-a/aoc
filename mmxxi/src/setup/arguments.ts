import { setupTest } from './setup';

const args = require('minimist')(process.argv.slice(2)); // eslint-disable-line

console.log(args);
console.log(setupTest());
