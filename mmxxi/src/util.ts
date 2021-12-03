import { createReadStream } from 'fs';
import { createInterface } from 'readline';
import { ParsedArgs } from 'minimist';
import { isNullOrUndefined } from 'util';

export const readInput = async (path: string): Promise<string[]> => {
    const lines: string[] = [];

    return new Promise((resolve, reject) => {
        const rs = createReadStream(path);
        rs.on('error', reject);

        createInterface({
            input: rs,
            terminal: false,
        })
            .on('line', (line: string) => {
                lines.push(line);
            })
            .on('close', () => {
                resolve(lines);
            });
    });
};

export const getInputPath = (): string => {
    const args: ParsedArgs = require('minimist')(process.argv.slice(2)); // eslint-disable-line
    if (isNullOrUndefined(args['i'])) {
        return './input.txt';
    } else return args['i'];
};

export const getFlipMask = (size: number): number => {
    const str = new Array(size).fill(1).reduce((p, c) => p + c, '');
    return parseInt(str, 2);
};
