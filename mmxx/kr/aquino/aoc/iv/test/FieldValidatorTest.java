package kr.aquino.aoc.iv.test;

import org.junit.Assert;
import org.junit.Test;

import kr.aquino.aoc.iv.FieldValidator;

public class FieldValidatorTest {

   @Test
    public void ByrTest(){
        var fv = new FieldValidator("byr", "^(19[2-9][0-9]|200[0-2])$");
         Assert.assertEquals(false,  fv.isValid("byr", "2003"));
         Assert.assertEquals(true,  fv.isValid("byr", "2002"));
    }

    @Test
    public void iyrTest(){
        var fv = new FieldValidator("iyr", "^20(1[0-9]|20)$");
         var success = !fv.isValid("iyr", "2000");
         if(!success)
            throw new RuntimeException("2000");
         success = fv.isValid("iyr", "2012");
         if(!success)
            throw new RuntimeException("2012");
    }

    @Test
    public void eyrTest(){
        var fv = new FieldValidator("eyr", "^20(2[0-9]|30)$");
         var success = !fv.isValid("eyr", "2003");
         if(!success)
            throw new RuntimeException("2003");
         success = fv.isValid("eyr", "2022");
         if(!success)
            throw new RuntimeException("2022");
    }

   @Test
    public void hgtTest(){
        var fv = new FieldValidator("hgt", "^(1[5-9][0-9]cm|(59|6[0-9]|7[0-6])in)$");
         var success = !fv.isValid("hgt", "190in");
         if(!success)
            throw new RuntimeException("190in");
         success = fv.isValid("hgt", "155cm");
         if(!success)
            throw new RuntimeException("155cm");
         success = fv.isValid("hgt", "60in");
         if(!success)
            throw new RuntimeException("60in");
    }

   @Test
   public void hclTest(){
        var fv = new FieldValidator("hcl", "^#[0-9a-f]{6}$");
         var success = !fv.isValid("hcl", "#123abz");
         if(!success)
            throw new RuntimeException("#123abz");
         success = fv.isValid("hcl", "#123abc");
         if(!success)
            throw new RuntimeException("#123abc");
    }

   @Test
   public void eclTest(){
        var fv = new FieldValidator("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$");
         var success = !fv.isValid("ecl", "wat");
         if(!success)
            throw new RuntimeException("wat");
         success = fv.isValid("ecl", "brn");
         if(!success)
            throw new RuntimeException("brn");
    }

    @Test
    public void pidTest(){
        var fv = new FieldValidator("pid", "^\\d{9}$");
         var success = !fv.isValid("pid", "0123456789");
         if(!success)
            throw new RuntimeException("0123456789");
         success = fv.isValid("pid", "000000001");
         if(!success)
            throw new RuntimeException("000000001");
    }
}
