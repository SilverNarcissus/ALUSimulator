
public class ALUtest3 {
	public static void main(String[] args) {
		ALU alu = new ALU();
		// 0 0001 0001
		System.out.println(alu.integerDivision("0100", "0011", 4));
		// 0 00000001 00000001
		System.out.println(alu.integerDivision("0100", "0011", 8));
		// 溢出
		System.out.println(alu.integerDivision("0100", "0000", 4));
		// 0 0000 1110
		System.out.println(alu.integerDivision("0110", "1101", 4));
		// 溢出
		System.out.println(alu.integerDivision("1000", "1111", 4));
		// 一种无法计算的情况 0 1101 1111
		System.out.println(alu.integerDivision("1010", "0011", 4));
		// 0 1111 0001
		System.out.println(alu.integerDivision("1100", "1101", 4));
		// 32个0
		System.out.println(alu.floatRepresentation("0.0", 8, 23));
		//
		System.out.println(alu.floatRepresentation("7", 8, 23));
		// 正无穷
		System.out.println(alu.floatRepresentation("2000", 4, 10));
		// System.out.println(alu.toBinString(234));
	}
}
