import static org.junit.jupiter.api.Assertions.*;

class Ex2SheetTest {
    Ex2Sheet tester = new Ex2Sheet(26,99);
    void rst (){
        for(int i =0; i<tester.width();i++)
        {
            for (int j= 0; j< tester.height();j++)
                tester.set(i,j,"");
        }
    }
    @org.junit.jupiter.api.Test
    void value() {
        tester.set(0,0,"1.0");// 1.0
        tester.set(0,1,"=1+1+1+1"); //4.0
        tester.set(12,30,"=++=");// ERR_From
        tester.set(25,1,"=1+A0");//2.0
        tester.set(1,1,"=222587831.0==18A...");//Err_Form
        assertEquals(tester.value(0,0),"1.0");
        assertEquals(tester.value(0,1),"4.0");
        assertEquals(tester.value(12,30),"ERR_Form_Format");
        assertEquals(tester.value(25,1),"2.0");
        assertEquals(tester.value(1,1),"ERR_Form_Format");


    }


    @org.junit.jupiter.api.Test
    void eValuate() {
        rst();
        tester.set(0,0,"1.0");// 1.0
        tester.set(0,1,"=1+1+1+1"); //4.0
        tester.set(12,30,"=1+A0+A1");// M30 -> 5.0
        tester.set(25,1,"=(1+1+1)/0");//Infinity
        tester.set(1,1,"1234543");//1234543
        assertEquals(tester.value(0,0),"1.0");
        assertEquals(tester.value(0,1),"4.0");
        assertEquals(tester.value(12,30),"6.0");
        assertEquals(Double.parseDouble(tester.value(25,1)),Double.POSITIVE_INFINITY);
        assertEquals(tester.value(1,1),"1234543.0");



    }

    @org.junit.jupiter.api.Test
    void computeFrom() {
        String str = "7+(8*(3+4)/1)+2";
        String str1 = "14/2+4+4*(1234)";
        String str2 = "((1+2))";
        String str3 = "(7+9)*12-3+(20-12+5+6/2)+4-9+12/2";
        String str4 = "123456789-87654";
        String str5 = "23+23+89+45-(12345+3456/8)+(67)";
        assertEquals(Ex2Sheet.computeFrom(str),65);
        assertEquals(Ex2Sheet.computeFrom(str1),4947);
        assertEquals(Ex2Sheet.computeFrom(str2),3);
        assertEquals(Ex2Sheet.computeFrom(str3),206);
        assertEquals(Ex2Sheet.computeFrom(str4),123369135);
        assertEquals(Ex2Sheet.computeFrom(str5),-12530);

    }
    @org.junit.jupiter.api.Test
    void getSubCells(){
        rst();
        tester.set(0,0,"1.0");
        tester.set(0,1,"A0");
        tester.set(0,2,"A1");
        assertEquals(tester.getSubCells(tester.get(0,0)),null);
        assertEquals(tester.getSubCells(tester.get(0,1)).getName(),"a0");
        assertEquals(tester.getSubCells(tester.get(0,2)).getName(),"a1");

    }


}