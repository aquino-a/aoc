import { gcd } from '../util';

export const partone = (input: string[]): number => {
    const diagram = new Diagram();
    parseInput(input)
        .filter(l => l.from.x == l.to.x || l.from.y == l.to.y)
        .forEach(diagram.drawLine);
    return diagram.getPointsOver(2);
};

export const parttwo = (input: string[]): number => {
    const diagram = new Diagram();
    parseInput(input).forEach(diagram.drawLine);
    return diagram.getPointsOver(2);
};

export const parseInput = (input: string[]): Line[] => {
    const regex = new RegExp('(\\d+),(\\d+) -> (\\d+),(\\d+)');
    return input.map(s => {
        const match = regex.exec(s);
        return {
            from: new Point(+match[1], +match[2]),
            to: new Point(+match[3], +match[4]),
        };
    });
};

export class Diagram {
    private diagram: number[][] = [];

    getPointsOver = (threshold: number): number => {
        const numbersOver = this.diagram
            .filter(r => r != undefined)
            .flatMap(n => n)
            .filter(n => n >= threshold);
        return numbersOver.length;
    };

    drawLine = (line: Line) => {
        //
        const range = this.getRange(line);
        range.forEach(p => {
            if (this.diagram[p.x] == undefined) {
                this.diagram[p.x] = [];
            }
            if (this.diagram[p.x][p.y] == undefined) {
                this.diagram[p.x][p.y] = 1;
            } else {
                this.diagram[p.x][p.y]++;
            }
        });
    };

    private getRange = (line: Line): Point[] => {
        const x = line.to.x - line.from.x;
        const y = line.to.y - line.from.y;

        const divideBy = Math.abs(gcd(x, y));
        const increment: Point = new Point(x / divideBy, y / divideBy);

        const range = [line.from, line.to];
        for (
            let current = line.from.add(increment);
            !current.equals(line.to);
            current = current.add(increment)
        ) {
            range.push(current);
        }

        return range;
    };
}

export interface Line {
    from: Point;
    to: Point;
}

export class Point {
    x: number;
    y: number;

    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }

    add = (point: Point): Point => {
        return new Point(this.x + point.x, this.y + point.y);
    };

    equals = (point: Point): boolean => {
        return (
            point != null &&
            point != undefined &&
            this.x == point.x &&
            this.y == point.y
        );
    };
}
