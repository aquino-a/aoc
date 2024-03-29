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
    const str = new Array(size).fill('1').join('');
    return parseInt(str, 2);
};

export const gcd = (a: number, b: number): number => {
    if (b == 0) {
        return a;
    }

    return gcd(b, a % b);
};

export const triangleNum = (n: number): number => {
    return (n * (n + 1)) / 2;
};
