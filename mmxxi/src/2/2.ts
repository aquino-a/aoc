export const partone = (commands: Command[]): number => {
    const finalPosition = commands.reduce(
        (prev, current) => {
            switch (current.direction) {
                case Direction.forward:
                    return {
                        horizontal: prev.horizontal + current.amount,
                        depth: prev.depth,
                    };
                case Direction.down:
                    return {
                        horizontal: prev.horizontal,
                        depth: prev.depth + current.amount,
                    };
                case Direction.up:
                    return {
                        horizontal: prev.horizontal,
                        depth: prev.depth - current.amount,
                    };
                default:
                    throw new Error(`invalid argument: ${current}`);
            }
        },
        { horizontal: 0, depth: 0 }
    );

    return finalPosition.depth * finalPosition.horizontal;
};

export const parttwo = (commands: Command[]): number => {
    const finalPosition = commands.reduce(
        (prev, current) => {
            switch (current.direction) {
                case Direction.forward:
                    return {
                        horizontal: prev.horizontal + current.amount,
                        depth: prev.depth + current.amount * prev.aim,
                        aim: prev.aim,
                    };
                case Direction.down:
                    return {
                        horizontal: prev.horizontal,
                        depth: prev.depth,
                        aim: prev.aim + current.amount,
                    };
                case Direction.up:
                    return {
                        horizontal: prev.horizontal,
                        depth: prev.depth,
                        aim: prev.aim - current.amount,
                    };
                default:
                    throw new Error(`invalid argument: ${current}`);
            }
        },
        { horizontal: 0, depth: 0, aim: 0 }
    );

    return finalPosition.depth * finalPosition.horizontal;
};

export const parseCommands = (input: string[]): Command[] => {
    return input.map(c => {
        const pieces = c.split(' ');
        return {
            direction: Direction[pieces[0]],
            amount: parseInt(pieces[1]),
        };
    });
};

export interface Command {
    direction: Direction;
    amount: number;
}

export enum Direction {
    forward,
    down,
    up,
}
