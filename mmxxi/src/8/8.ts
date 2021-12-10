export const partone = (input: string[]): number => {
    return countUnique(parseDisplays(input), [2, 3, 4, 7]);
};

// 2 - right side
// 3 = top side
// 4 - middle
// 7 - bottom

export const parttwo = (input: string[]): number => {};

export const countUnique = (displays: Display[], nums: number[]): number => {
    return displays
        .flatMap(d => d.output)
        .map(c => c.length)
        .filter(n => nums.includes(n)).length;
};

export const parseDisplays = (input: string[]): Display[] => {
    const regex = new RegExp('((?:[a-z]+ ?)+) \\| ((?:[a-z]+ ?)+)');
    return input.map(s => {
        const match = regex.exec(s);
        return {
            signals: match[1].split(' '),
            output: match[2].split(' '),
        };
    });
};

export interface Display {
    signals: string[];
    output: string[];
}
