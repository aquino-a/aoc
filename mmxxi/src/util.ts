import { createReadStream } from "fs";
import { createInterface } from "readline";

export const readInput = async (path: string): Promise<string[]> => {
    const lines: string[] = [];

    return new Promise((resolve, reject) => {
        const rs = createReadStream(path);
        rs.on('error', reject);

        createInterface({
            input: createReadStream(path),
            terminal: false
        }).on('line', (line: string) => {
            lines.push(line);
        }).on('close', () => {
            resolve(lines);
        });
    })
};
