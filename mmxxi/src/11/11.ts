export const partone = (input: string[]): number => {
    return simulate(parseGrid(input), 100);
};

export const parttwo = (input: string[]): number => {
    const grid = parseGrid(input);
    for (let i = 1; i < Number.MAX_SAFE_INTEGER; i++) {
        const flashed = simulate(grid, 1);
        if (flashed == grid.length * grid[0].length) {
            return i;
        }
    }
};

export const simulate = (grid: Octopus[][], steps: number): number => {
    let count = 0;

    for (let i = 0; i < steps; i++) {
        count += step(grid);
    }

    return count;
};

export const step = (grid: Octopus[][]): number => {
    const flashing = new Set<Octopus>();
    const toFlash: Octopus[] = [];

    //increment
    grid.forEach(row => row.forEach(o => increment(o, flashing, toFlash)));

    //flash
    while (toFlash.length > 0) {
        const octopus = toFlash.pop();
        const surrounding = getSurrounding(octopus, grid);
        surrounding.forEach(o => increment(o, flashing, toFlash));
    }

    flashing.forEach(o => (o.energy = 0));

    return flashing.size;
};

export const increment = (
    o: Octopus,
    flashing: Set<Octopus>,
    toFlash: Octopus[]
) => {
    o.energy++;
    if (o.energy > 9 && !flashing.has(o)) {
        flashing.add(o);
        toFlash.push(o);
    }
};

export const getSurrounding = (o: Octopus, grid: Octopus[][]) => {
    const surrounding: Octopus[] = [];

    for (let i = -1; i < 2; i++) {
        for (let j = -1; j < 2; j++) {
            if (i == 0 && j == 0) {
                continue;
            }
            const x = o.x + i;
            const y = o.y + j;

            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                continue;
            } else {
                surrounding.push(grid[x][y]);
            }
        }
    }

    return surrounding;
};

export const parseGrid = (input: string[]): Octopus[][] => {
    return input
        .map(s => s.split(''))
        .map((a, i) =>
            a.map(
                (n, j): Octopus => ({
                    x: i,
                    y: j,
                    energy: +n,
                })
            )
        );
};

export interface Octopus {
    x: number;
    y: number;
    energy: number;
}
