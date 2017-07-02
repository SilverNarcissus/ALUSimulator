
public class ALUtest2 {
	public static void main(String[] args) {
		final int TotalNumbers = 100;
		ALU one = new ALU();
		boolean[] results = new boolean[TotalNumbers];
		int index = 0;
		String[] s = new String[TotalNumbers];

		for (int i = 0; i < 100; i++) {
			results[i] = false;
		}
		// 相当友好的test文件(-v-)
		// 如果结果不对的话可以把控制台输出的注释取消来看
		// 1
		System.out.println(one.integerMultiplication("0100", "0100", 8));
		s[index] = one.integerRepresentation("-1", 4);
		if (s[index].equals("1111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		// System.out.println(s[index]);
		index += 1;
		// System.out.println(s[index]);
		// 2
		s[index] = one.integerRepresentation("0", 4);
		if (s[index].equals("0000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		index += 1;
		/* System.out.println(s[index]); */
		// 3
		s[index] = one.integerRepresentation("9", 8);
		if (s[index].equals("00001001")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		index += 1;
		/* System.out.println(s[index]); */
		// 4
		s[index] = one.floatRepresentation("+1.5", 8, 8);
		if (s[index].equals("00111111110000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		// System.out.println(s[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 5
		s[index] = one.floatRepresentation("+Inf", 8, 8);
		if (s[index].equals("01111111100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 6
		s[index] = one.floatRepresentation("+11.375", 8, 11);
		if (s[index].equals("01000001001101100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 7
		Double x1 = Math.pow(2, -128);
		s[index] = one.floatRepresentation(String.valueOf(x1), 8, 23);
		if (s[index].equals("00000000001000000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		// System.out.println(s[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 8
		s[index] = one.ieee754("11.375", 32);
		if (s[index].equals("01000001001101100000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 9
		s[index] = one.ieee754("-Inf", 32);
		if (s[index].equals("11111111100000000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 10
		s[index] = one.ieee754("1.5", 64);
		if (s[index].equals("0011111111111000000000000000000000000000000000000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 11
		s[index] = one.integerTrueValue("1101");
		if (s[index].equals("-3")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 12
		s[index] = one.integerTrueValue("0000");
		if (s[index].equals("0")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 13
		s[index] = one.integerTrueValue("010000001");
		if (s[index].equals("129")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 14
		s[index] = one.floatTrueValue("011110000", 4, 4);
		if (s[index].equals("+Inf")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 15
		Double x2 = Math.pow(2, -127);
		s[index] = one.floatTrueValue("00000000010000000", 8, 8);
		if (s[index].equals(String.valueOf(x2))) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 16
		s[index] = one.negation("1001");
		if (s[index].equals("0110")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 17
		s[index] = one.negation("00001001");
		if (s[index].equals("11110110")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 18
		s[index] = one.leftShift("0001", 2);
		if (s[index].equals("0100")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 19
		s[index] = one.leftShift("00001001", 2);
		if (s[index].equals("00100100")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 20
		s[index] = one.logRightShift("11110110", 2);
		if (s[index].equals("00111101")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 21
		s[index] = one.ariRightShift("11110110", 2);
		if (s[index].equals("11111101")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 22
		s[index] = one.fullAdder('1', '1', '0');
		if (s[index].equals("10")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 23
		s[index] = one.fullAdder('1', '0', '1');
		if (s[index].equals("10")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 24
		s[index] = one.claAdder("1001", "0001", '1');
		if (s[index].equals("01011")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 25
		s[index] = one.claAdder("1111", "1111", '1');
		if (s[index].equals("11111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 26
		s[index] = one.oneAdder("00001001");
		if (s[index].equals("000001010")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 27
		s[index] = one.oneAdder("11111111");
		if (s[index].equals("000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 28
		s[index] = one.adder("0100", "0011", '0', 8);
		if (s[index].equals("000000111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 29
		s[index] = one.adder("1000", "1000", '0', 8);
		if (s[index].equals("011110000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 30
		s[index] = one.adder("1111", "0011", '1', 8);
		if (s[index].equals("000000011")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 31
		s[index] = one.integerAddition("0100", "0011", 8);
		if (s[index].equals("000000111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 32
		s[index] = one.integerAddition("1000", "1111", 4);
		if (s[index].equals("10111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 33
		s[index] = one.integerSubtraction("0100", "1111", 8);
		if (s[index].equals("000000101")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 34
		s[index] = one.integerSubtraction("0100", "0011", 8);
		if (s[index].equals("000000001")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 35
		s[index] = one.integerSubtraction("1000", "1111", 8);
		if (s[index].equals("011111001")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 36
		s[index] = one.integerMultiplication("0100", "0011", 8);
		if (s[index].equals("000001100")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 37
		s[index] = one.integerMultiplication("1111", "0011", 8);
		if (s[index].equals("011111101")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 38
		s[index] = one.integerMultiplication("0100", "0000", 8);
		if (s[index].equals("000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 39
		s[index] = one.integerMultiplication("1000", "1111", 8);
		if (s[index].equals("000001000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 40
		s[index] = one.integerDivision("0100", "0011", 8);
		if (s[index].equals("00000000100000001")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 41
		s[index] = one.integerDivision("1000", "0011", 4);
		if (s[index].equals("011101110")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 42
		s[index] = one.integerDivision("0110", "1101", 4);
		if (s[index].equals("011100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 43,这条不过很正常
		s[index] = one.integerDivision("1010", "1101", 4);
		if (s[index].equals("000100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 44
		s[index] = one.signedAddition("1100", "1011", 8);
		if (s[index].equals("0100000111")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 45
		s[index] = one.signedAddition("01000", "01000", 4);
		if (s[index].equals("100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 46
		s[index] = one.signedAddition("01000", "11000", 4);
		if (s[index].equals("000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 47
		s[index] = one.signedAddition("01001", "11000", 4);
		if (s[index].equals("000001")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 48
		s[index] = one.floatAddition("00111111010100000", "00111111001000000", 8, 8, 4);
		// System.out.println(s[index]);
		if (s[index].equals("000111111101110000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 49
		s[index] = one.floatAddition("00111111010100000", "10111111010100000", 8, 8, 4);
		if (s[index].equals("000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 50
		s[index] = one.floatAddition("00111111110000000", "10111111010100000", 8, 8, 4);
		if (s[index].equals("000111111001100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 51,反规格
		s[index] = one.floatAddition("00000000010000000", "00000000001000000", 8, 8, 4);
		if (s[index].equals("000000000011000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 52
		s[index] = one.floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 4);
		if (s[index].equals("000111110010000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 53
		s[index] = one.floatAddition("00000000010000000", "00000000100000000", 8, 8, 4);
		if (s[index].equals("000000000110000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 54
		s[index] = one.floatAddition("00000000010000000", "00111111100000000", 8, 8, 4);
		if (s[index].equals("000111111100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 55
		s[index] = one.floatMultiplication("00111110111000000", "00111111000000000", 8, 8);
		if (s[index].equals("000111110011000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 56
		s[index] = one.floatMultiplication("00111110111000000", "10111111000000000", 8, 8);
		if (s[index].equals("010111110011000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 57
		s[index] = one.floatMultiplication("00000000011000000", "00111111000000000", 8, 8);
		if (s[index].equals("000000000001100000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		System.out.println(s[index]);
		index += 1;
		// 58
		s[index] = one.floatMultiplication("00000000011000000", "00000000011000000", 8, 8);
		if (s[index].equals("000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		System.out.println(s[index]);
		index += 1;
		// 59
		s[index] = one.floatDivision("00111110111000000", "00111111000000000", 8, 8);
		if (s[index].equals("000111111011000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 60
		s[index] = one.floatDivision("00000000000000000", "00111111000000000", 8, 8);
		if (s[index].equals("000000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 61
		s[index] = one.floatDivision("00111111100000000", "00000000000000000", 8, 8);
		if (s[index].equals("001111111100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 62
		s[index] = one.floatDivision("10111111100000000", "00000000000000000", 8, 8);
		if (s[index].equals("011111111100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 63
		System.out.println(one.floatTrueValue("00111111000000000", 8, 8));
		s[index] = one.floatDivision("00000000010000000", "00111111000000000", 8, 8);
		System.out.println("Last:" + s[index]);
		if (s[index].equals("000000000100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 64
		s[index] = one.floatDivision("00000000010000000", "00000000010000000", 8, 8);
		if (s[index].equals("000111111100000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 65
		s[index] = one.floatDivision("00111111100000000", "00111111000000000", 8, 8);
		if (s[index].equals("001000000000000000")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 66
		s[index] = one.floatTrueValue("01111111100000000000000000000001", 8, 23);
		if (s[index].equals("NaN")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 67
		s[index] = one.floatTrueValue(one.floatRepresentation("23873462.23213", 8, 23), 8, 23);
		System.out.println(s[index]);
		if (s[index].equals("")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 68
		double op1 = Math.pow(2, -100);
		double op2 = Math.pow(2, -27);
		System.out.println("kkkkkkkkkkkkkkkkkkk");
		s[index] = one.floatMultiplication(one.floatRepresentation(String.valueOf(op1), 8, 23),
				one.floatRepresentation(String.valueOf(op2), 8, 23), 8, 23);
		// System.out.println("main"+one.floatRepresentation("2", 8, 23));
		System.out.println(s[index]);
		System.out.println(one.floatTrueValue(s[index].substring(1), 8, 23));
		if (s[index].equals("")) {
			results[index] = true;
		}
		System.out.println((index + 1) + ":" + results[index]);
		/* System.out.println(s[index]); */
		index += 1;
		// 带符号加减的检测
		// System.out.println(one.signedAddition(one.integerRepresentation("6",
		// 5), one.integerRepresentation("-1", 5), 4));
		//
		int countSuccess = 0;
		for (int i = 0; i < index; i++) {
			if (results[i]) {
				countSuccess += 1;
			}
		}
		System.out.println("\nTotal:" + index + " Success:" + countSuccess);
		double op3 = -6;
		double op4 = -4;
		System.out.println(one.floatAddition(one.floatRepresentation(String.valueOf(op3), 8, 23),
				one.floatRepresentation(String.valueOf(op4), 8, 23), 8, 23, 8));
		System.out.println(one.integerMultiplication("0010", "0100", 4));
		System.out.println(one.signedAddition("01", "11", 4));
		//
		//
		//
		//
		// 李果的测试用例
		ALU alu = new ALU();
		 //String integerRepresentation (String number, int length)
		 System.out.println(alu.integerRepresentation("9", 8));
		 System.out.println(alu.integerRepresentation("0", 8));
		 System.out.println(alu.integerRepresentation("-29", 32));
		 System.out.println(alu.integerRepresentation("-9", 8));
		
		// //String negation (String operand)
		 System.out.println(alu.negation("00001001"));
		 System.out.println(alu.negation("00000000"));
		 System.out.println(alu.negation("111111111111"));
		 System.out.println(alu.negation("11011000101"));
		
		// //String integerTrueValue (String operand)
		 System.out.println(alu.integerTrueValue ("000"));
		 System.out.println(alu.integerTrueValue ("100"));
		 System.out.println(alu.integerTrueValue ("1000"));
		 System.out.println(alu.integerTrueValue ("0111"));
		 System.out.println(alu.integerTrueValue ("10011"));

		// String floatRepresentation (String number, int eLength, int sLength)
		 System.out.println(alu.floatRepresentation ("0.0", 8, 11));
		 System.out.println(alu.floatRepresentation("-0.0", 8, 11));
		 System.out.println(alu.floatRepresentation("0.3", 8, 11));
		 System.out.println(alu.floatRepresentation("0.00012075", 8, 23));
		 System.out.println(alu.floatRepresentation("0.000120750002679415047168731689453",
		 8, 23));
		 System.out.println(alu.floatRepresentation("5.0", 2, 4));
		 System.out.println(alu.floatRepresentation("-5.0", 2, 4));
		 System.out.println(alu.floatRepresentation ("5.0", 8, 11));
		 System.out.println(alu.floatRepresentation ("-5.0", 8, 11));
		 System.out.println(alu.floatRepresentation ("+Inf", 8, 11));
		 System.out.println(alu.floatRepresentation ("-Inf", 8, 23));
		 System.out.println(alu.floatRepresentation ("11.375", 8, 11));
		 System.out.println(alu.floatRepresentation ("11.375", 8, 23));

		// String floatTrueValue (String operand, int eLength, int sLength)
		 System.out.println(alu.floatTrueValue
		 ("00111111100000000000000000000000", 8, 23));
		 System.out.println(alu.floatTrueValue
		 ("00111111101100000000000000000000", 8, 23));
		 System.out.println(alu.floatTrueValue
		 ("00111011000001111111110010111001", 8, 23));
		 System.out.println(alu.floatTrueValue
		 ("10111011000001111111110010111001", 8, 23));
		 System.out.println(alu.floatTrueValue
		 ("00000000000010101110001110010111", 8, 23));

		// String fullAdder (char x, char y, char c)
		 System.out.println(alu.fullAdder ('0', '0','0'));
		 System.out.println(alu.fullAdder ('1', '0','0'));
		 System.out.println(alu.fullAdder ('0', '1','0'));
		 System.out.println(alu.fullAdder ('0', '0','1'));
		 System.out.println(alu.fullAdder ('1', '1','0'));
		 System.out.println(alu.fullAdder ('0', '1','1'));
		 System.out.println(alu.fullAdder ('1', '0','1'));
		 System.out.println(alu.fullAdder ('1', '1','1'));

		// String claAdder (String operand1, String operand2, char c)
		 System.out.println(alu.claAdder ("1010","0101" , '0'));
		 System.out.println(alu.claAdder ("0000","1111" , '0'));
		 System.out.println(alu.claAdder ("1111","1111" , '0'));
		 System.out.println(alu.claAdder ("1110","1101" , '0'));
		 System.out.println(alu.claAdder ("1001","0001" , '0'));
		 System.out.println(alu.claAdder ("0111","0001" , '0'));

		// String oneAdder (String operand)
		 System.out.println(alu.oneAdder ("00"));
		 System.out.println(alu.oneAdder ("01"));
		 System.out.println(alu.oneAdder ("1111"));
		 System.out.println(alu.oneAdder ("0111"));
		 System.out.println(alu.oneAdder ("0110"));
		 System.out.println(alu.oneAdder ("010011"));
		 System.out.println(alu.oneAdder ("011111111"));

		// String adder (String operand1, String operand2, char c, int length)
		 System.out.println(alu.adder("0111", "01",'0' , 4));
		 System.out.println(alu.adder("10000", "01101",'0' , 12));
		 System.out.println(alu.adder("10000", "01101",'1' , 12));
		 System.out.println(alu.adder("01100", "1110100",'0' , 8));

		// String integerAddition (String operand1, String operand2, int length)
		 System.out.println(alu.integerAddition("0111", "01", 4));
		 System.out.println(alu.integerAddition("10000", "01101", 12));
		 System.out.println(alu.integerAddition("10000", "01110", 12));
		 System.out.println(alu.integerAddition("01100", "1110100", 8));

		// String integerSubtraction (String operand1, String operand2, int
		// length)
		 System.out.println(alu.integerSubtraction("01111111", "010", 8));

		// String integerMultiplication (String operand1, String operand2, int
		// length)
		 System.out.println(alu.integerMultiplication("0110", "0100", 4));
		 System.out.println(alu.integerMultiplication("0010", "0001", 4));
		 System.out.println(alu.integerMultiplication("0011", "0010", 8));
		 System.out.println(alu.integerMultiplication("1110", "0100", 8));
		 System.out.println(alu.integerMultiplication("0100", "0010", 8));
		 System.out.println(alu.integerMultiplication("1101", "1110", 8));
		 System.out.println(alu.integerMultiplication("1110", "1101", 8));

		// String integerDivision (String operand1, String operand2, int length)
		 System.out.println(alu.integerDivision("0000", "1100", 4));
		 System.out.println(alu.integerDivision("0110", "0100", 4));
		 System.out.println(alu.integerDivision("1010", "0100", 4));
		 System.out.println(alu.integerDivision("1010", "1100", 4));
		 System.out.println(alu.integerDivision("0110", "1100", 4));
		 System.out.println(alu.integerDivision("0110", "1101", 4));
		 System.out.println(alu.integerDivision("1010", "0011", 4));
		 System.out.println(alu.integerDivision("0110", "0110", 4));

		// String signedAddition (String operand1, String operand2, int length)
		 System.out.println(alu.signedAddition ("01","11" ,4));
		 System.out.println(alu.signedAddition("11", "01", 4));
		 System.out.println(alu.signedAddition("01101", "01010", 4));
		 System.out.println(alu.signedAddition("01010", "11101", 4));
		 System.out.println(alu.signedAddition("01101", "11010", 4));

		// String floatAddition (String operand1, String operand2, int eLength,
		// int sLength, int gLength)
		 System.out.println(alu.floatAddition("10001111","10001111",3,4,4));
		 System.out.println(alu.floatAddition("00111111000000000000000000000000","10111110111000000000000000000000",8,23,4));
		 System.out.println(alu.floatAddition("00000000011000000000000000000000","10000000011000000000000000000000",8,23,4));
		 System.out.println(alu.floatAddition("00000000011111000000000000000000","10000000010100000000000000000000",8,23,4));
		//
		// String floatSubtraction (String operand1, String operand2, int
		// eLength, int sLength, int gLength)
		 System.out.println(alu.floatSubtraction("00000000011111000000000000000000","00000000010100000000000000000000",8,23,4));

		// String floatMultiplication (String operand1, String operand2, int
		// eLength, int sLength) {
		 System.out.println(alu.floatMultiplication("00111111000000000000000000000000","00111110111000000000000000000000",8,23));

		// String floatDivision (String operand1, String operand2, int eLength,
		// int sLength)
		 System.out.println(alu.floatDivision("00111110111000000000000000000000",
		 "00111111000000000000000000000000", 8, 23));
		//
		// 官方测试用例
		// System.out.println("官方测试");
		// System.out.println(alu.integerRepresentation("9", 8));
		// System.out.println(alu.floatRepresentation("11.375", 8, 11));
		// System.out.println(alu.ieee754("11.375", 32));
		// System.out.println(alu.integerTrueValue("00001001"));
		// System.out.println(alu.floatTrueValue("01000001001101100000", 8,
		// 11));
		// System.out.println(alu.negation("00001001"));
		// System.out.println(alu.leftShift("00001001", 2));
		// System.out.println(alu.logRightShift("11110110", 2));
		// System.out.println(alu.ariRightShift("11110110", 2));
		// System.out.println(alu.fullAdder('1', '1', '0'));
		// System.out.println(alu.claAdder("1001", "0001", '1'));
		// System.out.println(alu.oneAdder("00001001"));
		// System.out.println(alu.adder("0100", "0011", '0', 8));
		// System.out.println(alu.integerAddition("0100", "0011", 8));
		// System.out.println(alu.integerSubtraction("0100", "0011", 8));
		// System.out.println(alu.integerMultiplication("0100", "0011", 8));
		// System.out.println(alu.integerDivision("0100", "0011", 8));
		// System.out.println(alu.signedAddition("1100", "1011", 8));
		// System.out.println(alu.floatAddition("00111111010100000",
		// "00111111001000000", 8, 8, 4));
		// System.out.println(alu.floatSubtraction("00111111010100000",
		// "00111111001000000", 8, 8, 4));
		// System.out.println(alu.floatMultiplication("00111110111000000",
		// "00111111000000000", 8, 8));
		// System.out.println(alu.floatDivision("00111110111000000",
		// "00111111000000000", 8, 8));
	}
}

