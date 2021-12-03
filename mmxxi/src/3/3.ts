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

export const parttwo = (
    compare: (separated: { 1: string[]; 0: string[] }, index: number) => number,
    input: string[],
    index: number
): number => {
    if (input.length == 1) {
        return parseInt(input[0], 2);
    }

    const separated = input.reduce(
        (p: { 1: string[]; 0: string[] }, c) => {
            if (c.charAt(index) == '1') {
                p[1].push(c);
            } else p[0].push(c);

            return p;
        },
        { 1: [], 0: [] }
    );

    return compare(separated, index);
};

export const high = (
    separated: { 1: string[]; 0: string[] },
    index: number
): number => {
    if (
        separated[1].length == separated[0].length ||
        separated[1].length > separated[0].length
    ) {
        return parttwo(high, separated[1], index + 1);
    } else {
        return parttwo(high, separated[0], index + 1);
    }
};

export const low = (
    separated: { 1: string[]; 0: string[] },
    index: number
): number => {
    if (
        separated[1].length == separated[0].length ||
        separated[0].length < separated[1].length
    ) {
        return parttwo(low, separated[0], index + 1);
    } else {
        return parttwo(low, separated[1], index + 1);
    }
};

export interface Report {
    gamma: number;
    epsilon: number;
}
