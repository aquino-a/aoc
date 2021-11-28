import { setupTest } from "../test";
import { readInput } from "../../util";

test("setup test", async () => {
    expect(setupTest()).toBe<string>('test');
});

test("input read test", async () => {
    try {
        const lines = await readInput(__dirname + '/testInput.txt');
        expect(lines.length).toBe<number>(10);
    } catch (error) {
        fail(error.message);
    }
});