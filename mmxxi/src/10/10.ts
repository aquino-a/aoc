export const partone = (input: string[]): number => {
    return input
        .map(s => firstError(s))
        .filter(c => c != undefined)
        .map(c => points[c])
        .reduce((p, c) => p + c, 0);
};

export const parttwo = (input: string[]): number => {
    const scores = input
        .filter(s => firstError(s) == undefined)
        .map(s => getRemaining(s))
        .map(n => score(n))
        .sort((a, b) => a - b);

    return scores[Math.floor(scores.length / 2)];
};

export const getRemaining = (input: string): string[] => {
    const stack: string[] = [];

    for (let i = 0; i < input.length; i++) {
        const char = input[i];
        if ({}.hasOwnProperty.call(matches, char)) {
            stack.push(matches[char]);
        } else {
            stack.pop();
        }
    }

    return stack.reverse();
};

export const score = (remaining: string[]): number => {
    return remaining.reduce((p, c) => p * 5 + points2[c], 0);
};

export const firstError = (input: string): string => {
    const stack: string[] = [];

    for (let i = 0; i < input.length; i++) {
        const char = input[i];
        if ({}.hasOwnProperty.call(matches, char)) {
            stack.push(matches[char]);
        } else {
            const expected = stack.pop();
            if (char != expected) {
                return char;
            }
        }
    }

    return undefined;
};

export const matches = {
    '<': '>',
    '(': ')',
    '[': ']',
    '{': '}',
};

export const points = {
    '>': 25137,
    ')': 3,
    ']': 57,
    '}': 1197,
};

export const points2 = {
    '>': 4,
    ')': 1,
    ']': 2,
    '}': 3,
};
