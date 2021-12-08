//organize numbers by their timer and shift the numbers each increment
export const partone = (input: string[]): number => {
    return simulate(parseFish(input), 80);
};

export const parttwo = (input: string[]): number => {
    return simulate(parseFish(input), 256);
};

export const simulate = (fish: LanternFish[], days: number): number => {
    for (let i = 0; i < days; i++) {
        const restartFish = removeTime(fish, 0);

        fish.forEach(f => {
            f.time--;
        });

        if (restartFish != undefined) {
            fish.push({ count: restartFish.count, time: 8 });
            const six = fish.find(f => f.time == 6);

            if (six == undefined) {
                restartFish.time = 6;
                fish.push(restartFish);
            } else {
                six.count += restartFish.count;
            }
        }
    }

    return fish.map(f => f.count).reduce((p, c) => p + c);
};

const removeTime = (fish: LanternFish[], time: number): LanternFish => {
    const index = fish.findIndex(f => f.time == time);
    if (index > -1) {
        return fish.splice(index, 1)[0];
    } else {
        return undefined;
    }
};

export const parseFish = (input: string[]): LanternFish[] => {
    return input
        .shift()
        .split(',')
        .map(n => +n)
        .reduce((p: LanternFish[], c: number) => {
            if (p[c] == undefined) {
                p[c] = { count: 1, time: c };
            } else {
                p[c].count++;
            }
            return p;
        }, [])
        .filter(f => f != undefined);
};

export interface LanternFish {
    count: number;
    time: number;
}
