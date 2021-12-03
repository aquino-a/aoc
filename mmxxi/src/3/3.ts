import { getFlipMask } from '../util';

export const partone = (input: string[]): Report => {
    const counts = new Array(input[0].length).fill(0);

    for (let i = 0; i < input.length; i++) {
        const reading = input[i];
        for (let j = 0; j < reading.length; j++) {
            const char = reading.charAt(j);
            if (char == '1') {
                counts[j]++;
            }
        }
    }

    const half = input.length / 2;
    const gammaStr = counts.reduce((g: string, c) => {
        return c > half ? g + '1' : g + '0';
    }, '');

    const mask = getFlipMask(input[0].length);
    const gamma = parseInt(gammaStr, 2);

    return {
        gamma: gamma,
        epsilon: gamma ^ mask,
    };
};

export const parttwo = (input: string[]): number => {
    const highResult = rateHigh(input, 0);
    const lowResult = rateLow(input, 0);

    return highResult * lowResult;
};

export const rateHigh = (input: string[], index: number): number => {
    if (input.length == 1) {
        return parseInt(input[0], 2);
    }

    const separated = separate(input, index);

    if (
        separated[1].length == separated[0].length ||
        separated[1].length > separated[0].length
    ) {
        return rateHigh(separated[1], index + 1);
    } else {
        return rateHigh(separated[0], index + 1);
    }
};

export const rateLow = (input: string[], index: number): number => {
    if (input.length == 1) {
        return parseInt(input[0], 2);
    }

    const separated = separate(input, index);

    if (
        separated[1].length == separated[0].length ||
        separated[0].length < separated[1].length
    ) {
        return rateLow(separated[0], index + 1);
    } else {
        return rateLow(separated[1], index + 1);
    }
};

const separate = (input: string[], index: number) => {
    return input.reduce(
        (p: { 1: string[]; 0: string[] }, c) => {
            if (c.charAt(index) == '1') {
                p[1].push(c);
            } else p[0].push(c);

            return p;
        },
        { 1: [], 0: [] }
    );
};

export interface Report {
    gamma: number;
    epsilon: number;
}
