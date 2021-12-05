export const partone = (input: string[]): number => {
    const drawNums = input
        .shift()
        .split(',')
        .map(n => +n);
    const boards = parseBoards(input);

    for (let i = 0; i < drawNums.length; i++) {
        const n = drawNums[i];
        for (let j = 0; j < boards.length; j++) {
            const b = boards[j];
            if (b.mark(n)) {
                return n * Array.from(b.remaining).reduce((p, c) => p + c);
            }
        }
    }

    throw new Error('No match');
};

export const parttwo = (input: string[]): number => {
    const drawNums = input
        .shift()
        .split(',')
        .map(n => +n);
    const boards = parseBoards(input);

    for (let i = 0; i < drawNums.length; i++) {
        const n = drawNums[i];
        for (let j = boards.length - 1; j >= 0; j--) {
            const b = boards[j];
            if (b.mark(n)) {
                if (boards.length == 1) {
                    return n * Array.from(b.remaining).reduce((p, c) => p + c);
                } else {
                    boards.splice(j, 1);
                }
            }
        }
    }

    throw new Error('No match');
};

export class Board {
    remaining: Set<number>;
    rows: Set<number>[] = [];
    columns: Set<number>[];

    constructor(numbers: number[][]) {
        this.remaining = new Set(numbers.flatMap(s => s));
        this.columns = new Array(numbers[0].length);

        for (let i = 0; i < numbers.length; i++) {
            const row = numbers[i];
            this.rows.push(new Set(row));
            for (let j = 0; j < row.length; j++) {
                const num = row[j];
                if (this.columns[j] === undefined) {
                    this.columns[j] = new Set<number>();
                }
                this.columns[j].add(num);
            }
        }
    }

    public mark(num: number): boolean {
        if (!this.remaining.has(num)) {
            return false;
        }

        this.remaining.delete(num);

        if (this.checkSet(this.rows, num)) {
            return true;
        }

        if (this.checkSet(this.columns, num)) {
            return true;
        }

        return false;
    }

    private checkSet = (sets: Set<number>[], num: number): boolean => {
        for (let i = 0; i < sets.length; i++) {
            const set = sets[i];
            if (set.has(num)) {
                set.delete(num);
                if (set.size == 0) {
                    return true;
                }
            }
        }

        return false;
    };
}

export const parseBoards = (input: string[]): Board[] => {
    const boards: Board[] = [];
    let currentNums: number[][] = [];

    for (let i = 0; i < input.length; i++) {
        const line = input[i];

        if (line != '') {
            currentNums.push(
                line
                    .split(' ')
                    .filter(s => s != '')
                    .map(n => +n)
            );
        }

        if (line == '' || i == input.length - 1) {
            if (currentNums.length > 0) {
                boards.push(new Board(currentNums));
                currentNums = [];
            }
        }
    }

    return boards;
};
