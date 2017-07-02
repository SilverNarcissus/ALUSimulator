
/**
 * 模拟ALU进行整数和浮点数的四则运算
 * 
 * @author [151250176_薛恺丰]
 *
 */

public class ALU {

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * 
	 * @param number
	 *            十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
	 */
	public String integerRepresentation(String number, int length) {
		String result = "";
		if (number.charAt(0) == '-') {
			// return reverseAddOne(number.substring(1), 8);
			result = toBinString(Integer.parseInt(number.substring(1)));
			result = reverseAddOne(result);
			for (int i = length - result.length(); i > 0; i--) {
				result = "1" + result;
			}
		} else {
			result = toBinString(Integer.parseInt(number));
			for (int i = length - result.length(); i > 0; i--) {
				result = "0" + result;
			}
		}
		return result;
	}

	/**
	 * 生成十进制浮点数的二进制表示。 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation(String number, int eLength, int sLength) {
		// 先排除字符的无穷，数字的无法表示稍后考虑
		if (number.equals("+Inf")) {
			return "0" + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		if (number.equals("-Inf")) {
			return "1" + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		//
		int basis = (int) Math.pow(2, eLength - 1) - 1;
		// 确定符号位
		char sign;
		if (number.charAt(0) == '-') {
			sign = '1';
			number = number.substring(1);
		} else {
			sign = '0';
		}
		//
		// 排除NaN
		if (number.equals("NaN")) {
			return sign + integerRepresentation("-1", eLength) + integerRepresentation("1", sLength);
		}
		//
		double full = Double.parseDouble(number);
		double fraction = full - (long) full;
		// 排除0
		if (full == 0) {
			return sign + integerRepresentation("0", eLength) + integerRepresentation("0", sLength);
		}
		//
		String fractionPart = "";
		// 计算小数部分
		int count = 0;
		boolean flag = false;
		// 确保循环可以跳出设计的
		int protect = 0;
		//
		while ((fraction != 0 && count < 2 * sLength) && protect < 10000) {
			protect++;
			fraction = fraction * 2;
			if (fraction >= 1) {
				fraction = fraction - 1;
				fractionPart += "1";
				flag = true;
			} else {
				fractionPart += "0";
			}
			if (flag) {
				count++;
			}
		}
		for (int i = 0; i < sLength; i++) {
			fractionPart = fractionPart + "0";
		}
		//
		String fixedNoPoint = toBinString((long) full) + fractionPart;
		String fixedPoint = toBinString((long) full) + "." + fractionPart;
		int temp = fixedPoint.indexOf('1');
		int bias = fixedPoint.indexOf('.') - temp;
		if (bias > 0) {
			bias = bias - 1;
		} else {
			temp--;
		}
		int exponent = basis + bias;
		// 判断是否为非规格化数
		if (exponent <= 0) {
			return sign + integerRepresentation("0", eLength)
					+ fixedNoPoint.substring(-bias + exponent, -bias + exponent + sLength);
		}
		//
		fixedNoPoint = fixedNoPoint.substring(temp + 1);
		// 判断是否为无穷
		if (exponent >= (int) Math.pow(2, eLength) - 1) {
			return sign + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		//
		//
		return sign + integerRepresentation(String.valueOf(exponent), eLength + 1).substring(1)
				+ fixedNoPoint.substring(0, sLength);
	}

	/**
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int)
	 * floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754(String number, int length) {
		if (length == 32) {
			return floatRepresentation(number, 8, 23);
		} else {
			return floatRepresentation(number, 11, 52);
		}
	}

	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 */
	public String integerTrueValue(String operand) {
		boolean flag = false;
		int result = 0;
		if (operand.charAt(0) == '1') {
			operand = reverseAddOne(operand);
			flag = true;
		}
		// 转化为10进制
		for (int i = operand.length() - 1, x = 0; i >= 0; i--, x++) {
			if (operand.charAt(i) == '1') {
				result += Math.pow(2, x);
			}
		}
		if (flag) {
			return "-" + String.valueOf(result);
		} else {
			return String.valueOf(result);
		}
	}

	/**
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”，
	 *         NaN表示为“NaN”
	 */
	public String floatTrueValue(String operand, int eLength, int sLength) {
		char sign = operand.charAt(0);
		String exponent = operand.substring(1, eLength + 1);
		String fraction = operand.substring(eLength + 1);
		int basis = (int) Math.pow(2, eLength - 1) - 1;
		// 正负无穷
		if (!exponent.contains("0") && !fraction.contains("1")) {
			return sign == '0' ? "+Inf" : "-Inf";
		}
		// 非数
		if (!exponent.contains("0")) {
			return "NaN";
		}
		// 考虑规格化数
		if (exponent.contains("1")) {
			double exponentPart = Math.pow(2, Integer.parseInt(integerTrueValue("0" + exponent)) - basis);
			double fractionPart = 0;
			for (int i = 0; i < fraction.length(); i++) {
				if (fraction.charAt(i) == '1') {
					fractionPart += Math.pow(2, -i - 1);
				}
			}

			if (sign == '0') {
				return String.valueOf(exponentPart * (1 + fractionPart));
			} else {
				return "-" + String.valueOf(exponentPart * (1 + fractionPart));
			}
		}
		// 考虑非规格化数
		else {
			double exponentPart = Math.pow(2, -basis + 1);
			double fractionPart = 0;
			for (int i = 0; i < fraction.length(); i++) {
				if (fraction.charAt(i) == '1') {
					fractionPart += Math.pow(2, -i - 1);
				}
			}
			if (sign == '0') {
				return String.valueOf(exponentPart * (fractionPart));
			} else {
				return "-" + String.valueOf(exponentPart * (fractionPart));
			}
		}
	}

	/**
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @return operand按位取反的结果
	 */
	public String negation(String operand) {
		String result = "";
		for (int i = 0; i < operand.length(); i++) {
			result = result + notGate(operand.charAt(i));
		}
		return result;
	}

	/**
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            左移的位数
	 * @return operand左移n位的结果
	 */
	public String leftShift(String operand, int n) {
		String result = "";
		if (n >= operand.length()) {
			for (int i = 0; i < operand.length(); i++) {
				result += "0";
			}
			return result;
		}
		for (int i = n; i < operand.length(); i++) {
			result += operand.charAt(i);
		}
		for (int i = 0; i < n; i++) {
			result += "0";
		}
		return result;
	}

	/**
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand逻辑右移n位的结果
	 */
	public String logRightShift(String operand, int n) {
		String result = "";
		if (n >= operand.length()) {
			for (int i = 0; i < operand.length(); i++) {
				result += "0";
			}
			return result;
		}
		for (int i = 0; i < operand.length() - n; i++) {
			result += operand.charAt(i);
		}
		for (int i = 0; i < n; i++) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand算术右移n位的结果
	 */
	public String ariRightShift(String operand, int n) {
		String result = "";
		if (n >= operand.length()) {
			if (operand.charAt(0) == '0') {
				for (int i = 0; i < operand.length(); i++) {
					result += "0";
				}
			} else {
				for (int i = 0; i < operand.length(); i++) {
					result += "1";
				}
			}
			return result;
		}
		for (int i = 0; i < operand.length() - n; i++) {
			result += operand.charAt(i);
		}
		if (operand.charAt(0) == '0') {
			for (int i = 0; i < n; i++) {
				result = "0" + result;
			}
		} else {
			for (int i = 0; i < n; i++) {
				result = "1" + result;
			}
		}
		return result;
	}

	/**
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * 
	 * @param x
	 *            被加数的某一位，取0或1
	 * @param y
	 *            加数的某一位，取0或1
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder(char x, char y, char c) {
		return orGate(orGate(andGate(x, y), andGate(x, c)), andGate(y, c)) + ""
				+ exclusiveORGate(exclusiveORGate(x, y), c);
	}

	/**
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * 
	 * @param operand1
	 *            4位二进制表示的被加数
	 * @param operand2
	 *            4位二进制表示的加数
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
	 */
	public String claAdder(String operand1, String operand2, char c) {
		char c1 = orGate(calculateG(operand1.charAt(3), operand2.charAt(3)),
				andGate(calculateP(operand1.charAt(3), operand2.charAt(3)), c));
		char c2 = orGate(
				orGate(calculateG(operand1.charAt(2), operand2.charAt(2)),
						andGate(calculateP(operand1.charAt(2), operand2.charAt(2)),
								calculateG(operand1.charAt(3), operand2.charAt(3)))),
				andGate(calculateP(operand1.charAt(2), operand2.charAt(2)),
						andGate(calculateP(operand1.charAt(3), operand2.charAt(3)), c)));
		char c3 = orGate(
				calculateG(operand1.charAt(1),
						operand2.charAt(
								1)),
				orGate(andGate(calculateP(operand1.charAt(1), operand2.charAt(1)),
						calculateG(operand1.charAt(2), operand2.charAt(2))), orGate(
								andGate(calculateP(operand1.charAt(1), operand2.charAt(1)),
										andGate(calculateP(operand1.charAt(2), operand2.charAt(2)),
												calculateG(operand1.charAt(3), operand2.charAt(3)))),
								andGate(calculateP(operand1.charAt(1), operand2.charAt(1)),
										andGate(calculateP(operand1.charAt(2), operand2.charAt(2)),
												andGate(calculateP(operand1.charAt(3), operand2.charAt(3)), c))))));
		char c4 = orGate(calculateG(operand1.charAt(0), operand2.charAt(0)), orGate(andGate(
				calculateP(operand1.charAt(0), operand2.charAt(0)),
				calculateG(operand1.charAt(1), operand2.charAt(1))), orGate(
						andGate(calculateP(operand1.charAt(0), operand2.charAt(0)),
								andGate(calculateP(operand1.charAt(1), operand2.charAt(1)),
										calculateG(operand1.charAt(2), operand2.charAt(2)))),
						orGate(andGate(calculateP(operand1.charAt(0), operand2.charAt(0)),
								andGate(calculateP(operand1.charAt(1), operand2.charAt(1)),
										andGate(calculateP(operand1.charAt(2), operand2.charAt(2)),
												calculateG(operand1.charAt(3), operand2.charAt(3))))),
								andGate(calculateP(operand1.charAt(0), operand2.charAt(0)),
										andGate(calculateP(operand1.charAt(1), operand2.charAt(1)), andGate(
												calculateP(operand1.charAt(2), operand2.charAt(2)), andGate(
														calculateP(operand1.charAt(3), operand2.charAt(3)), c))))))));
		char s1 = fullAdder(operand1.charAt(3), operand2.charAt(3), c).charAt(1);
		char s2 = fullAdder(operand1.charAt(2), operand2.charAt(2), c1).charAt(1);
		char s3 = fullAdder(operand1.charAt(1), operand2.charAt(1), c2).charAt(1);
		char s4 = fullAdder(operand1.charAt(0), operand2.charAt(0), c3).charAt(1);
		return "" + c4 + s4 + s3 + s2 + s1;
	}

	/**
	 * 加一器，实现操作数加1的运算。 需要采用与门、或门、异或门等模拟， 不可以直接调用
	 * {@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
	 */
	public String oneAdder(String operand) {
		String result = "";
		char c = '1';
		for (int i = operand.length() - 1; i >= 0; i--) {
			result = exclusiveORGate(operand.charAt(i), c) + result;
			c = andGate(operand.charAt(i), c);
		}
		if (operand.charAt(0) == '0' && result.charAt(0) == '1') {
			return "1" + result;
		}
		return "0" + result;
	}

	/**
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param c
	 *            最低位进位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String adder(String operand1, String operand2, char c, int length) {
		// 补符号位
		for (int i = length - operand1.length(); i > 0; i--) {
			if (operand1.charAt(0) == '0') {
				operand1 = "0" + operand1;
			} else {
				operand1 = "1" + operand1;
			}
		}
		for (int i = length - operand2.length(); i > 0; i--) {
			if (operand2.charAt(0) == '0') {
				operand2 = "0" + operand2;
			} else {
				operand2 = "1" + operand2;
			}
		}
		//
		String result = "";
		char cn = c;
		for (int i = 4; i <= length; i = i + 4) {
			String cache = claAdder(operand1.substring(length - i, length - i + 4),
					operand2.substring(length - i, length - i + 4), cn);
			result = cache.substring(1) + result;
			cn = cache.charAt(0);
		}
		// 判断是否溢出
		if (orGate(andGate(operand1.charAt(0), andGate(operand2.charAt(0), notGate(result.charAt(0)))),
				andGate(notGate(operand1.charAt(0)), andGate(notGate(operand2.charAt(0)), result.charAt(0)))) == '1') {
			return "1" + result;
		} else {
			return "0" + result;
		}
		//
	}

	/**
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition(String operand1, String operand2, int length) {
		return adder(operand1, operand2, '0', length);
	}

	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被减数
	 * @param operand2
	 *            二进制补码表示的减数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction(String operand1, String operand2, int length) {
		return adder(operand1, negation(operand2), '1', length);
	}

	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int) adder}等方法。
	 * <br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被乘数
	 * @param operand2
	 *            二进制补码表示的乘数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
	 */
	public String integerMultiplication(String operand1, String operand2, int length) {
		// 补位
		for (int i = length - operand1.length(); i > 0; i--) {
			operand1 = operand1.charAt(0) + operand1;
		}
		for (int i = length - operand2.length(); i > 0; i--) {
			operand2 = operand2.charAt(0) + operand2;
		}
		//
		operand2 = operand2 + "0";
		String result = integerRepresentation("0", length);
		String behind = integerRepresentation("0", length);
		for (int i = operand2.length() - 1; i > 0; i--) {
			if (operand2.charAt(i) == '0' && operand2.charAt(i - 1) == '1') {
				result = integerSubtraction(result, operand1, length).substring(1);
			}
			if (operand2.charAt(i) == '1' && operand2.charAt(i - 1) == '0') {
				result = integerAddition(result, operand1, length).substring(1);
			}
			String full = ariRightShift(result + behind, 1);
			result = full.substring(0, length);
			behind = full.substring(length, 2 * length);
		}
		// 判断是否溢出
		for (char temp : result.toCharArray()) {
			if (temp != behind.charAt(0)) {
				return "1" + behind;
			}
		}
		return "0" + behind;
	}

	/**
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被除数
	 * @param operand2
	 *            二进制补码表示的除数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，
	 *         最后length位为余数
	 */
	public String integerDivision(String operand1, String operand2, int length) {
		// 补位
		for (int i = length - operand1.length(); i > 0; i--) {
			operand1 = operand1.charAt(0) + operand1;
		}
		for (int i = length - operand2.length(); i > 0; i--) {
			operand2 = operand2.charAt(0) + operand2;
		}
		//
		String remainder;
		if (operand1.charAt(0) == '0') {
			remainder = integerRepresentation("0", length);
		} else {
			remainder = integerRepresentation("-1", length);
		}
		String quotinent = operand1;
		String full = "";
		boolean flag = remainder.charAt(0) == operand2.charAt(0);
		//
		for (int i = 0; i < length; i++) {
			if (flag) {
				remainder = integerSubtraction(remainder, operand2, length).substring(1);
			} else {
				remainder = integerAddition(remainder, operand2, length).substring(1);
			}
			// 判断下一次是加还是减
			flag = remainder.charAt(0) == operand2.charAt(0);
			//
			// 判断添加位
			if (remainder.charAt(0) == operand2.charAt(0)) {
				quotinent += "1";
			} else {
				quotinent += "0";
			}
			// 左移
			full = leftShift(remainder + quotinent, 1);
			remainder = full.substring(0, length);
			quotinent = full.substring(length, 2 * length);
		}
		//
		if (flag) {
			remainder = integerSubtraction(remainder, operand2, length).substring(1);
		} else {
			remainder = integerAddition(remainder, operand2, length).substring(1);
		}
		if (remainder.charAt(0) == operand2.charAt(0)) {
			quotinent += "1";
		} else {
			quotinent += "0";
		}
		// 对余数结果修正
		if (remainder.charAt(0) != operand1.charAt(0)) {
			if (operand1.charAt(0) == operand2.charAt(0)) {
				remainder = integerAddition(remainder, operand2, length).substring(1);
			} else {
				remainder = integerSubtraction(remainder, operand2, length).substring(1);
			}
		}
		// 对商结果修正
		quotinent = leftShift(quotinent, 1).substring(0, length);
		if (quotinent.charAt(0) == '1') {
			quotinent = oneAdder(quotinent).substring(1);
		}
		// 判断是否溢出
		char overflow;
		if (integerTrueValue(operand2).equals("0") || ((integerTrueValue(operand2).equals("-1"))
				&& (integerAddition(operand1, operand2, length).charAt(0) == '1'))) {
			overflow = '1';
		} else {
			overflow = '0';
		}
		return overflow + quotinent + remainder;
	}

	/**
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int)
	 * integerAddition}、 {@link #integerSubtraction(String, String, int)
	 * integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * 
	 * @param operand1
	 *            二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2
	 *            二进制原码表示的加数，其中第1位为符号位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，
	 *            需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，
	 *         后length位是相加结果
	 */
	public String signedAddition(String operand1, String operand2, int length) {
		// 拆分符号位和数字
		char sign1 = operand1.charAt(0);
		char sign2 = operand2.charAt(0);
		operand1 = operand1.substring(1);
		operand2 = operand2.substring(1);
		String result = "";
		//
		// 补位
		for (int i = length - operand1.length(); i > 0; i--) {
			operand1 = "0" + operand1;
		}
		for (int i = length - operand2.length(); i > 0; i--) {
			operand2 = "0" + operand2;
		}
		//
		if (sign1 == sign2) {
			// 同号相加
			result = adder("0000" + operand1, "0000" + operand2, '0', length + 4).substring(4);
			return result.charAt(0) + "" + sign1 + result.substring(1);
		} else {
			// 异号相减
			result = adder("0000" + operand1, "0000" + negation(operand2), '1', length + 4).substring(4);
			if (result.charAt(0) == '1') {
				// 有进位 去进位 符号与被减数相同
				return "0" + sign1 + result.substring(1);
			} else {
				// 无进位 取反加一 符号与减数相同
				return "0" + sign2 + reverseAddOne(result.substring(1));
			}
		}
	}

	/**
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int) signedAddition}
	 * 等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被加数
	 * @param operand2
	 *            二进制表示的加数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatAddition(String operand1, String operand2, int eLength, int sLength, int gLength) {
		int basis = (int) Math.pow(2, eLength - 1) - 1;
		// 判断是否有0存在
		if (!operand1.substring(1).contains("1")) {
			return "0" + operand2;
		}
		if (!operand2.contains("1")) {
			return "0" + operand1;
		}
		//
		// 完全化小数部分
		int exponent1 = Integer.parseInt(integerTrueValue(("0" + operand1.substring(1, eLength + 1))));
		int exponent2 = Integer.parseInt(integerTrueValue(("0" + operand2.substring(1, eLength + 1))));
		String fraction1 = "";
		String fraction2 = "";
		if (exponent1 == 0) {
			fraction1 = "0" + operand1.substring(eLength + 1) + integerRepresentation("0", gLength);
		} else {
			fraction1 = "1" + operand1.substring(eLength + 1) + integerRepresentation("0", gLength);
		}
		if (exponent2 == 0) {
			fraction2 = "0" + operand2.substring(eLength + 1) + integerRepresentation("0", gLength);
		} else {
			fraction2 = "1" + operand2.substring(eLength + 1) + integerRepresentation("0", gLength);
		}
		//
		// 对齐指数
		int resultExponent = 0;
		if (exponent1 > exponent2) {
			for (int i = exponent2; i < exponent1; i++) {
				// 非规格化数第一次移动的时候小数部分不动，实质上指数并未加一
				if (i == 0) {
					continue;
				}
				fraction2 = logRightShift(fraction2, 1);
			}
			resultExponent = exponent1;
			if (!fraction2.contains("1")) {
				return "0" + operand1;
			}
		}
		if (exponent2 > exponent1) {
			for (int i = exponent1; i < exponent2; i++) {
				// 非规格化数第一次移动的时候小数部分不动，实质上指数并未加一
				if (i == 0) {
					continue;
				}
				fraction1 = logRightShift(fraction1, 1);
			}
			resultExponent = exponent2;
			if (!fraction1.contains("1")) {
				return "0" + operand2;
			}
		}
		if (exponent1 == exponent2) {
			resultExponent = exponent1;
		}
		//
		String resultFraction = signedAddition(operand1.charAt(0) + fraction1, operand2.charAt(0) + fraction2,
				fraction1.length() / 4 * 4 + 4);
		char sign = resultFraction.charAt(1);
		resultFraction = resultFraction.substring(1 + fraction1.length() / 4 * 4 + 4 - fraction1.length());
		// 若溢出，右移小数部分
		if (resultFraction.charAt(0) == '1') {
			resultFraction = logRightShift(resultFraction, 1);
			resultExponent++;
			// 指数上溢
			if (resultExponent == 2 * basis + 1) {
				return "1" + sign + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
			}
			//
		}
		//
		// 判断结果是否规格化
		resultFraction = resultFraction.substring(1);
		// 确保循环可以跳出设计的
		int protect = 0;
		//
		while (resultFraction.charAt(0) != '1' && protect < 10000) {
			protect++;
			// 输出反规格化的数
			if (resultExponent == 0) {
				return "0" + sign + integerRepresentation("0", eLength) + resultFraction.substring(1, 1 + sLength);
			}
			//
			resultExponent--;
			if (resultExponent == 0) {
				return "0" + sign + integerRepresentation("0", eLength) + resultFraction.substring(1, 1 + sLength);
			}
			resultFraction = leftShift(resultFraction, 1);
		}
		//
		// 输出结果
		return "0" + sign + integerRepresentation(String.valueOf(resultExponent), eLength)
				+ resultFraction.substring(1, 1 + sLength);
	}

	/**
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int)
	 * floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被减数
	 * @param operand2
	 *            二进制表示的减数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatSubtraction(String operand1, String operand2, int eLength, int sLength, int gLength) {
		// 改变减数的符号，做加法
		operand2 = notGate(operand2.charAt(0)) + operand2.substring(1);
		return floatAddition(operand1, operand2, eLength, sLength, gLength);
	}

	/**
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int)
	 * integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被乘数
	 * @param operand2
	 *            二进制表示的乘数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatMultiplication(String operand1, String operand2, int eLength, int sLength) {
		// 确定符号
		char sign = exclusiveORGate(operand1.charAt(0), operand2.charAt(0));
		// 判断0
		char overflow = '0';
		int basis = (int) Math.pow(2, eLength - 1) - 1;
		if (!operand1.substring(1).contains("1") || !operand2.substring(1).contains("1")) {
			return overflow + floatRepresentation("0", eLength, sLength);
		}
		//

		// 指数相加减偏值
		int exponent = Integer.parseInt(integerTrueValue(("0" + operand1.substring(1, eLength + 1))))
				+ Integer.parseInt(integerTrueValue(("0" + operand2.substring(1, eLength + 1)))) - basis;
		// 尾数相乘
		String firstAdd = "01";
		String secondAdd = "01";
		if (!operand1.substring(1, eLength + 1).contains("1")) {
			if (exponent >= 0) {
				exponent++;
			}
			firstAdd = "00";
		}
		if (!operand2.substring(1, eLength + 1).contains("1")) {
			if (exponent >= 0) {
				exponent++;
			}
			secondAdd = "00";
		}
		// 后面复杂的计算式是用来确保长度为4的倍数
		String fraction = integerMultiplication(firstAdd + operand1.substring(eLength + 1),
				secondAdd + operand2.substring(eLength + 1), (2 * (sLength + 2)) / 4 * 4 + 4)
						.substring(1 + 2 + ((2 * (sLength + 2)) / 4 * 4 - 2 * sLength));
		int temp = fraction.indexOf("1");
		exponent = exponent + 1 - temp;
		// 判断上溢
		if (exponent >= 2 * basis + 1) {
			overflow = '1';
			return overflow + "" + sign + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		//
		// 判断下溢
		if (exponent <= 0) {
			fraction = fraction.substring(temp);
			if (exponent == 0) {
				if (temp == 1) {
					return overflow + "" + sign + integerRepresentation("0", eLength) + fraction;
				} else {
					return overflow + "" + sign + integerRepresentation("1", eLength) + fraction.substring(1);
				}
			}
			for (int i = exponent; i < 0; i++) {
				fraction = "0" + fraction;
				exponent++;
			}
			fraction = fraction.substring(1, sLength + 1);
			if (temp == 1) {
				return overflow + "" + sign + integerRepresentation("0", eLength) + "0"
						+ fraction.substring(0, sLength - 1);
			}
			return overflow + "" + sign + integerRepresentation("0", eLength) + fraction;
		}
		//
		return overflow + "" + sign + integerRepresentation(String.valueOf(exponent), eLength + 1).substring(1)
				+ fraction.substring(temp + 1, temp + sLength + 1);
	}

	/**
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int) integerDivision}
	 * 等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被除数
	 * @param operand2
	 *            二进制表示的除数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatDivision(String operand1, String operand2, int eLength, int sLength) {
		int basis = (int) Math.pow(2, eLength - 1) - 1;
		// 确定符号
		char sign = exclusiveORGate(operand1.charAt(0), operand2.charAt(0));
		// 判断除以0
		if (!operand2.substring(1).contains("1")) {
			return "0" + sign + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		//
		// 判断被除数是否为0
		if (!operand1.substring(1).contains("1")) {
			return "0" + sign + integerRepresentation("0", eLength + sLength);
		}
		// 指数相减加偏值
		int exponent = Integer.parseInt(integerTrueValue(("0" + operand1.substring(1, eLength + 1))))
				- Integer.parseInt(integerTrueValue(("0" + operand2.substring(1, eLength + 1)))) + basis;
		//
		// 确定小数部分
		int exponent1 = Integer.parseInt(integerTrueValue(("0" + operand1.substring(1, eLength + 1))));
		int exponent2 = Integer.parseInt(integerTrueValue(("0" + operand2.substring(1, eLength + 1))));
		String fraction1 = "";
		String fraction2 = "";
		if (exponent1 == 0) {
			fraction1 = "0" + operand1.substring(eLength + 1);
			exponent++;
		} else {
			fraction1 = "1" + operand1.substring(eLength + 1);
		}
		if (exponent2 == 0) {
			exponent--;
			fraction2 = "0" + operand2.substring(eLength + 1);
		} else {
			fraction2 = "1" + operand2.substring(eLength + 1);
		}
		//
		// 小数部分相除
		String resultFraction = integerRepresentation("0", sLength + 1);
		for (int i = 0; i < sLength + 1; i++) {
			if (binToInt(fraction1) >= binToInt(fraction2)) {
				// 修正除法的无法计算的情况
				if (binToInt(fraction1) % binToInt(fraction2) == 0 && binToInt(fraction1) / binToInt(fraction2) > 1) {
					// 源码整数在计算整除的时候无法计算，故需要修正
					exponent = exponent + (int) (Math.log((double) (binToInt(fraction1) / binToInt(fraction2)))
							/ Math.log((double) 2));
					return "0" + sign + integerRepresentation(String.valueOf(exponent), eLength)
							+ integerRepresentation("0", sLength);
				}
				//
				resultFraction = resultFraction + "1";
				resultFraction = leftShift(resultFraction, 1);
				resultFraction = resultFraction.substring(0, sLength + 1);
				fraction1 = Long.toBinaryString(binToInt(fraction1) - binToInt(fraction2));
				if (fraction1.length() < sLength + 1) {
					fraction1 = "0" + fraction1;
				}
				fraction1 = fraction1.charAt(0) + leftShift(fraction1, 1);
			} else {
				resultFraction = resultFraction + "0";
				resultFraction = leftShift(resultFraction, 1);
				resultFraction = resultFraction.substring(0, sLength + 1);
				fraction1 = fraction1.charAt(0) + leftShift(fraction1, 1);
			}
		}
		//
		// 判断指数是否上溢
		if (exponent >= basis * 2 + 1) {
			return "1" + sign + integerRepresentation("-1", eLength) + integerRepresentation("0", sLength);
		}
		// 规格化结果
		// 确保循环可以跳出设计的
		int protect = 0;
		//
		while (resultFraction.charAt(0) != '1' && protect < 10000) {
			protect++;
			// 输出反规格化的数
			if (exponent == 0) {
				return "0" + sign + integerRepresentation("0", eLength) + resultFraction.substring(1, 1 + sLength);
			}
			//
			exponent--;
			if (exponent == 0) {
				return "0" + sign + integerRepresentation("0", eLength) + resultFraction.substring(1, 1 + sLength);
			}
			resultFraction = leftShift(resultFraction, 1);
		}
		//
		// 输出结果
		return "0" + sign + integerRepresentation(String.valueOf(exponent), eLength) + resultFraction.substring(1);
	}

	private String reverseAddOne(String binNumber) {
		return oneAdder(negation(binNumber)).substring(1);
	}

	private char andGate(char first, char second) {
		if (first == second && first == '1') {
			return '1';
		}
		return '0';
	}

	private char orGate(char first, char second) {
		if (first == second && first == '0') {
			return '0';
		}
		return '1';
	}

	private char notGate(char c) {
		if (c == '1') {
			return '0';
		} else {
			return '1';
		}
	}

	private char exclusiveORGate(char first, char second) {
		if (first == second) {
			return '0';
		}
		return '1';
	}

	private char calculateP(char first, char second) {
		return orGate(first, second);
	}

	private char calculateG(char first, char second) {
		return andGate(first, second);
	}

	private String toBinString(double number) {
		String result = "";
		if (number == 0) {
			return "0";
		}
		for (; number != 0; number = (long) number / 2) {
			result = String.valueOf(((long) number) % 2) + result;
		}
		return result;
	}

	private long binToInt(String bin) {
		long result = 0;
		for (int i = 0; i < bin.length(); i++) {
			if (bin.charAt(bin.length() - 1 - i) == '1') {
				result = result + (long) Math.pow(2, i);
			}
		}
		return result;
	}
}
