import { triangleNum } from '../util';

export const partone = (input: string[]) => {
    return getCheapestMove(parseCrabs(input), getCost);
};

export const parttwo = (input: string[]) => {
    return getCheapestMove(parseCrabs(input), getCrabCost);
};

export const parseCrabs = (input: string[]) => {
    return input
        .shift()
        .split(',')
        .map(n => +n)
        .sort((a, b) => a - b);
};

export const getCheapestMove = (
    nums: number[],
    cost: (nums: number[], num: number) => number
) => {
    const median = getMedian(nums);
    const medianCost = cost(nums, median);

    const leftCost = cost(nums, median - 1);
    const rightCost = cost(nums, median + 1);

    if (medianCost < leftCost && medianCost < rightCost) {
        return medianCost;
    }

    let increment = 0;
    if (leftCost < rightCost) {
        increment = -1;
    } else increment = 1;

    let lowest = Math.min(medianCost, leftCost, rightCost);
    for (
        let i = increment * 2 * increment, current = cost(nums, median + i);
        i < nums.length, current <= lowest;
        i += increment, current = cost(nums, median + i)
    ) {
        if (current < lowest) {
            lowest = current;
        }
    }

    return lowest;
};

export const getMedian = (nums: number[]): number => {
    const isEven = nums.length % 2 == 0;
    const half = Math.floor(nums.length / 2) - 1;
    if (isEven) {
        return Math.floor((nums[half] + nums[half + 1]) / 2);
    } else {
        return nums[half];
    }
};

export const getCost = (nums: number[], target: number): number => {
    return nums.reduce((p, c) => p + Math.abs(target - c), 0);
};

export const getCrabCost = (nums: number[], target: number): number => {
    return nums.reduce((p, c) => p + triangleNum(Math.abs(target - c)), 0);
};
