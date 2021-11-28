import { setupTest } from "../test";


test("setup test", async () => {
    expect(setupTest()).toBe<string>('test');
});