
public class ALUTest {
	ALU alu=new ALU();
	String s="";
	public void test1(){
		s=alu.integerRepresentation("25",8);
		if(s.equals("00011001"))
			System.out.println("true");
		else
			System.out.println("test1(1) is false");
		s=alu.integerRepresentation("-25",8);
		if(s.equals("11100111"))
			System.out.println("true");
		else
			System.out.println("test1(2) is false");
		s=alu.integerRepresentation("0",8);
		if(s.equals("00000000"))
			System.out.println("true");
		else
			System.out.println("test1(3) is false");	
	}
	
	public void test2(){
		s=alu.floatRepresentation("+Inf",8,6);
		if(s.equals("011111111000000"))
			System.out.println("true");
		else
			System.out.println("test2(1) is false");
		s=alu.floatRepresentation("-Inf",8,6);
		if(s.equals("111111111000000"))
			System.out.println("true");
		else
			System.out.println("test2(2) is false");
		s=alu.floatRepresentation("0",8,10);
		if(s.equals("0000000000000000000"))
			System.out.println("true");
		else
			System.out.println("test2(3) is false");
		s=alu.floatRepresentation("0.0",8,10);
		if(s.equals("0000000000000000000"))
			System.out.println("true");
		else
			System.out.println("test2(4) is false");
		s=alu.floatRepresentation("-0.0",8,10);
		if(s.equals("1000000000000000000"))
			System.out.println("true");
		else
			System.out.println("test2(5) is false");
		s=alu.floatRepresentation("NaN",8,10);
		System.out.println(s);
		if(s.equals("NaN"))
			System.out.println("true");
		else
			System.out.println("test2(6) is false");
		s=alu.floatRepresentation("385.375",8,10);
		if(s.equals("0100001111000000101"))
			System.out.println("true");
		else
			System.out.println("test2(7) is false");
		s=alu.floatRepresentation("5.375",8,8);
		if(s.equals("01000000101011000"))
			System.out.println("true");
		else
			System.out.println("test2(8) is false");
		s=alu.floatRepresentation("385",8,10);
		if(s.equals("0100001111000000100"))
			System.out.println("true");
		else
			System.out.println("test2(9) is false");
		s=alu.floatRepresentation("0.375",8,10);
		if(s.equals("0011111011000000000"))
			System.out.println("true");
		else
			System.out.println("test2(10) is false");
		s=alu.floatRepresentation(String.valueOf(Math.pow(2.0,-126)/2),8,10);
		if(s.equals("0000000001000000000"))
			System.out.println("true");
		else
			System.out.println("test2(11) is false");
		s=alu.floatRepresentation("22222222222222222222",8,23);
		System.out.println("kwe:"+s);
		if(s.equals("01011111100110100011001010011000"))
			System.out.println("true");
		else
			System.out.println("test2(12) is false");
		s=alu.floatRepresentation("222222222222222222222222222222",8,23);
		if(s.equals("01110000001100111000001001111010"))
			System.out.println("true");
		else
			System.out.println("test2(12) is false");
		s=alu.floatRepresentation("10000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000"
				+ "0000000000",11,52);
		if(s.equals("0101010010110010010010011010110100100101100101001100001101111101"))
			System.out.println("true");
		else
			System.out.println("test2(13) is false");
	}
	
	public void test3(){
		s=alu.ieee754("25.375",32);
		if(s.equals("01000001110010110000000000000000"))
			System.out.println("true");
		else
			System.out.println("test3(1) is false");
		s=alu.ieee754("-25.375",32);
		if(s.equals("11000001110010110000000000000000"))
			System.out.println("true");
		else
			System.out.println("test3(2) is false");
	}
	
	public void test4(){
		s=alu.integerTrueValue("011001");
		if(s.equals("25"))
			System.out.println("true");
		else
			System.out.println("test4(1) is false");
		s=alu.integerTrueValue("111001");
		if(s.equals("-7"))
			System.out.println("true");
		else
			System.out.println("test4(2) is false");
	}
	
	public void test5(){
		s=alu.floatTrueValue("01111111100000000",8,8);
		if(s.equals("+Inf"))
			System.out.println("true");
		else
			System.out.println("test5(1) is false");
		s=alu.floatTrueValue("11111111100000000",8,8);
		if(s.equals("-Inf"))
			System.out.println("true");
		else
			System.out.println("test5(2) is false");
		s=alu.floatTrueValue("01111111100000110",8,8);
		if(s.equals("NaN"))
			System.out.println("true");
		else
			System.out.println("test5(3) is false");
		s=alu.floatTrueValue("00000000000000000",8,8);
		if(s.equals("0.0"))
			System.out.println("true");
		else
			System.out.println("test5(4) is false");
		s=alu.floatTrueValue("01000001110010110",8,8);
		if(s.equals("25.375"))
			System.out.println("true");
		else
			System.out.println("test5(5) is false");
		s=alu.floatTrueValue("11000001110010110",8,8);
		if(s.equals("-25.375"))
			System.out.println("true");
		else
			System.out.println("test5(6) is false");
		s=alu.floatTrueValue("00000000000000110",8,8);
		double sle=Math.pow(2.0,-133)*3;
		if(s.equals(String.valueOf(sle)))
			System.out.println("true");
		else
			System.out.println("test5(7) is false");
		s=alu.floatTrueValue("10000000000000000000000000000001",8,23);
		sle=-Math.pow(2.0,-149);
		if(s.equals(String.valueOf(sle)))
			System.out.println("true");
		else
			System.out.println("test5(7) is false");
	}
	
	public void test6(){
		s=alu.negation("0101010101");
		if(s.equals("1010101010"))
			System.out.println("true");
		else
			System.out.println("test6(1) is false");
		s=alu.negation("111001101");
		if(s.equals("000110010"))
			System.out.println("true");
		else
			System.out.println("test6(2) is false");
	}
	
	public void test7(){
		s=alu.leftShift("00111100",4);
		if(s.equals("11000000"))
			System.out.println("true");
		else
			System.out.println("test7(1) is false");
		s=alu.leftShift("00111100",8);
		if(s.equals("00000000"))
			System.out.println("true");
		else
			System.out.println("test7(2) is false");
	}
	
	public void test8(){
		s=alu.logRightShift("00111100",4);
		if(s.equals("00000011"))
			System.out.println("true");
		else
			System.out.println("test8(1) is false");
	}
	
	public void test9(){
		s=alu.ariRightShift("00111100",4);
		if(s.equals("00000011"))
			System.out.println("true");
		else
			System.out.println("test9(1) is false");
		s=alu.ariRightShift("11001100",4);
		if(s.equals("11111100"))
			System.out.println("true");
		else
			System.out.println("test9(2) is false");
	}
	
	public void test10(){
		s=alu.fullAdder('0', '0', '0');
		if(s.equals("00"))
			System.out.println("true");
		else
			System.out.println("test10(1) is false");
		s=alu.fullAdder('0', '0', '1');
		if(s.equals("01"))
			System.out.println("true");
		else
			System.out.println("test10(2) is false");
		s=alu.fullAdder('0', '1', '0');
		if(s.equals("01"))
			System.out.println("true");
		else
			System.out.println("test10(3) is false");
		s=alu.fullAdder('1', '0', '0');
		if(s.equals("01"))
			System.out.println("true");
		else
			System.out.println("test10(4) is false");
		s=alu.fullAdder('0', '1', '1');
		if(s.equals("10"))
			System.out.println("true");
		else
			System.out.println("test10(5) is false");
		s=alu.fullAdder('1', '0', '1');
		if(s.equals("10"))
			System.out.println("true");
		else
			System.out.println("test10(6) is false");
		s=alu.fullAdder('1', '1', '0');
		if(s.equals("10"))
			System.out.println("true");
		else
			System.out.println("test10(7) is false");
		s=alu.fullAdder('1', '1', '1');
		if(s.equals("11"))
			System.out.println("true");
		else
			System.out.println("test10(8) is false");
	}
	
	public void test11(){
		s=alu.claAdder("1010","0110",'1');
		if(s.equals("10001"))
			System.out.println("true");
		else
			System.out.println("test11(1) is false");
		s=alu.claAdder("1010","0111",'1');
		if(s.equals("10010"))
			System.out.println("true");
		else
			System.out.println("test11(2) is false");
		s=alu.claAdder("1110","0111",'1');
		if(s.equals("10110"))
			System.out.println("true");
		else
			System.out.println("test11(3) is false");
	}
	
	public void test12(){
		s=alu.oneAdder("100101");
		if(s.equals("0100110"))
			System.out.println("true");
		else
			System.out.println("test12(1) is false");
		s=alu.oneAdder("111111");
//		System.out.println("swo:"+s);
		if(s.equals("0000000"))
			System.out.println("true");
		else
			System.out.println("test12(2) is false");
	}
	
	public void test13(){
		s=alu.adder("0101","0011",'1',4);
		if(s.equals("11001"))
			System.out.println("true");
		else
			System.out.println("test13(1) is false");
		s=alu.adder("0101","0011",'1',8);
		if(s.equals("000001001"))
			System.out.println("true");
		else
			System.out.println("test13(2) is false");
		s=alu.adder("1101","0011",'1',8);
		if(s.equals("000000001"))
			System.out.println("true");
		else
			System.out.println("test13(3) is false");
		s=alu.adder("1101","0011",'0',4);
		if(s.equals("00000"))
			System.out.println("true");
		else
			System.out.println("test13(4) is false");
	}
	
	public void test14(){
	}
	public void test15(){	
	}

	public void test16(){
		s=alu.integerMultiplication("0011","0100",8);
		if(s.equals("000001100"))
			System.out.println("true");
		else
			System.out.println("test16(1) is false");
		s=alu.integerMultiplication("00110011","0010",8);
		if(s.equals("001100110"))
			System.out.println("true");
		else
			System.out.println("test16(2) is false");
		s=alu.integerMultiplication("01111111","0100",8);
		if(s.equals("111111100"))
			System.out.println("true");
		else
			System.out.println("test16(3) is false");
		s=alu.integerMultiplication("0011","0000",8);
		if(s.equals("000000000"))
			System.out.println("true");
		else
			System.out.println("test16(4) is false");
	}
	
	public void test17(){
		s=alu.integerDivision("1001","0011",4);
		if(s.equals("011101111"))
			System.out.println("true");
		else
			System.out.println("test17(1) is false");
		s=alu.integerDivision("1001","0011",8);
		if(s.equals("01111111011111111"))
			System.out.println("true");
		else
			System.out.println("test17(2) is false");
		s=alu.integerDivision("0110","0010",4);
		if(s.equals("000110000"))
			System.out.println("true");
		else
			System.out.println("test17(3) is false");
		s=alu.integerDivision("0010","0110",4);
		if(s.equals("000000010"))
			System.out.println("true");
		else
			System.out.println("test17(4) is false");
		s=alu.integerDivision("1110","0110",4);
		if(s.equals("000001110"))
			System.out.println("true");
		else
			System.out.println("test17(5) is false");
	}
	
	public void test18(){
		s=alu.signedAddition("0011","0111",4);
		if(s.equals("001010"))			
			System.out.println("true");
		else
			System.out.println("test18(1) is false");
		s=alu.signedAddition("0011","011",4);
		if(s.equals("000110"))			
			System.out.println("true");
		else
			System.out.println("test18(2) is false");
		s=alu.signedAddition("01111","01",4);
		if(s.equals("100000"))			
			System.out.println("true");
		else
			System.out.println("test18(3) is false");
		s=alu.signedAddition("01111","11",4);
		if(s.equals("001110"))			
			System.out.println("true");
		else
			System.out.println("test18(4) is false");
	}
	
	public void test19(){
		s=alu.floatAddition("00111011000001000","00000000000000000",8,8,8);
		if(s.equals("000111011000001000"))	
			System.out.println("true");
		else
			System.out.println("test19(1) is false");
		s=alu.floatAddition("00111011000001000","00111011000000100",8,8,8);
		if(s.equals("000111011100000110"))	
			System.out.println("true");
		else
			System.out.println("test19(2) is false");
		s=alu.floatAddition("10111011000001000","10111011000000100",8,8,8);
		if(s.equals("010111011100000110"))	
			System.out.println("true");
		else
			System.out.println("test19(3) is false");
		s=alu.floatAddition("00111011000000000","10111011000000000",8,8,8);
		if(s.equals("000000000000000000"))	
			System.out.println("true");
		else
			System.out.println("test19(4) is false");
		s=alu.floatAddition("10111011000001000","10111011000000100",8,8,8);
		if(s.equals("010111011100000110"))	
			System.out.println("true");
		else
			System.out.println("test19(5) is false");
		s=alu.floatAddition("00111011010000000","10111011000000000",8,8,8);
		if(s.equals("000111010100000000"))	
			System.out.println("true");
		else
			System.out.println("test19(6) is false");
		s=alu.floatAddition("00000000100000010","10000000100000000",8,8,8);
		if(s.equals("000000000000000010"))	
			System.out.println("true");
		else
			System.out.println("test19(7) is false");
	}
	
	public void test20(){
		s=alu.floatMultiplication("00111111000000000","00111110111000000",8,8);
		if(s.equals("000111110011000000"))	
			System.out.println("true");
		else
			System.out.println("test20(1) is false");
		s=alu.floatMultiplication("00000000000000100","00000000000000100",8,8);
		if(s.equals("000000000000000000"))	
			System.out.println("true");
		else
			System.out.println("test20(2) is false");
		s=alu.floatMultiplication("00111111000000000","00000000010000000",8,8);
//		System.out.println(s);
		if(s.equals("000000000001000000"))	
			System.out.println("true");
		else
			System.out.println("test20(3) is false");
		s=alu.floatMultiplication("01000001000000000","00000000010000000",8,8);
//		System.out.println(s);
//		System.out.println("213213:"+s);
		if(s.equals("000000001100000000"))	
			System.out.println("true");
		else
			System.out.println("test20(4) is false");
	}
	
	public void test21(){
		s=alu.floatDivision("00111110111000000","00111111000000000",8,8);
		System.out.println(s);
		if(s.equals("000111111011000000"))
			System.out.println("true");
		else
			System.out.println("test21(1) is false");
		s=alu.floatDivision("00000000100000000","00"
				+ "000000010000000",8,8);
		System.out.println(s);
		if(s.equals("001000000000000000"))
			System.out.println("true");
		else
			System.out.println("test21(2) is false");
		s=alu.floatDivision("00000000100000000","00"
				+ "000000001000000",8,8);
		System.out.println(s);
	}
	
	public static void main(String args[]){
		ALUTest test=new ALUTest();
		test.test1();
		test.test2();
		test.test3();
		test.test4();
		test.test5();
		test.test6();
		test.test7();
		test.test8();
		test.test9();
		test.test10();
		test.test11();
		test.test12();
		test.test13();
		test.test16();
		test.test17();
		test.test18();
		test.test19();
		test.test20();
		test.test21();
	}
}
