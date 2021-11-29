import { setupTest } from "./setup";

const args = require('minimist')(process.argv.slice(2));

console.log(args);
console.log(setupTest());