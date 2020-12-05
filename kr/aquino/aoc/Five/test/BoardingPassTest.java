package kr.aquino.aoc.Five.test;

import org.junit.Assert;
import org.junit.Test;

import kr.aquino.aoc.Five.BoardingPass;

public class BoardingPassTest {
    
    @Test
    public void TestOne(){
        var bp = new BoardingPass(128, 8, "FBFBBFFRLR");
        Assert.assertEquals(357, bp.getSeatId());

    }
}
