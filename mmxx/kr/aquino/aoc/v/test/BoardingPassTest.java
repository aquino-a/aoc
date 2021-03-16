package kr.aquino.aoc.v.test;

import org.junit.Assert;
import org.junit.Test;

import kr.aquino.aoc.v.BoardingPass;

public class BoardingPassTest {
    
    @Test
    public void TestOne(){
        var bp = new BoardingPass(128, 8, "FBFBBFFRLR");
        Assert.assertEquals(357, bp.getSeatId());

    }
}
