import org.junit.After;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SCellTest {
    SCell sCell = new SCell("");
    @Test
    void isForm()
    {
        String s1 = "=123+456+789";
        String s2= "=1+(2+(3+4))";
        String s3 = "=0+0-1+2-1-2-34-5";
        String s4 = "=1/2/3/4/5/6/7/8";
        assertTrue(SCell.is_form(s1));
        assertTrue(SCell.is_form(s2));
        assertTrue(SCell.is_form(s3));
        assertTrue(SCell.is_form(s4));
        String s5 = "=1y78odifj/2/3/4/5/6/7/8";
        String s6 = "=_____+!!!!1/2/3/4/5/6/7/8";
        String s7 = "=1/2/3/4/5/6/7../8";
        assertFalse(SCell.is_form(s5));
        assertFalse(SCell.is_form(s6));
        assertFalse(SCell.is_form(s7));

    }

    @Test
    void isNumber()
    {
        String s1 = "111.0";
        String s2 = "100000111.00001";
        String s3 = "111.010101010101010";
        assertTrue(SCell.isNumber(s1));
        assertTrue(SCell.isNumber(s2));
        assertTrue(SCell.isNumber(s3));
    }
    @Test
    void isText()
    {
        String s1 = "47yu85gv";
        String s3 = "}}}///////..........v";
        String s2 = "A2+A3+S3+A1+22";
        assertTrue(SCell.isText(s1));
        assertTrue(SCell.isText(s3));
        assertTrue(SCell.isText(s2));
    }

}