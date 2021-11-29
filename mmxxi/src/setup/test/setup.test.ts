import { setupTest } from "../setup";
import { readInput } from "../../util";

test("setup test", async () => {
    expect(setupTest()).toBe<string>('test');
});

test("input read test success", async () => {
    const lines = await readInput(__dirname + '/testInput.txt');
    expect(lines.length).toBe<number>(10);
});

test("input read test fail", async () => {
    expect.assertions(1);

    try {
        await readInput('fail');
    } catch (e) {
      expect(e.code).toMatch('ENOENT');
    }
});