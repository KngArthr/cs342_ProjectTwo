package cs472_ProjectThree;

import java.util.Scanner;


public class PlDriver {
	
	
	//Both chunks
	private int opcodeChunk = 0b11111100000000000000000000000000;
	private int src1regChunk = 0b00000011111000000000000000000000;
		
	//R Chunks
	private int src2regChunk = 0b00000000000111110000000000000000;
	private int destregChunk = 0b00000000000000001111100000000000;
	private int functionChunk = 0b00000000000000000000000000111111;
		
	//I chunks
	private int srcdestChunk = 0b00000000000111110000000000000000;
	private int offsetChunk = 0b00000000000000001111111111111111;
	
	
	private IFID ifidW;
	private IFID ifidR;
	private IDEX idexW;
	private IDEX idexR;
	private EXMEM exmemW;
	private EXMEM exmemR;
	private MEMWB memwbW;
	private MEMWB memwbR;
	
	private String rtn;
	
	//Intialize main memory
	short[] Main_Mem = generateMainMemory(1024);
	
	//Intialize Registers
	int[] Regs = generateRegs(32);
	
	int programCounter = 0x7A000;
	
	
	

	
	//array of instructions
	int[] instrucArray = {0xA1020000, 0x810AFFFC, 0x00831820, 0x01263820, 0x01224820, 0x81180000, 0x81510010, 0x00624022, 0x00000000, 0x00000000, 0x00000000, 0x00000000};
	
	//array to map program counter to instructions for easy tracking
	int[] pcArray = new int[instrucArray.length];
	

	
	
	public static void main(String[] args) {
		
		
		PlDriver driver = new PlDriver();
		
		driver.runProgram();

	}
	


	public void runProgram(){
		
		ifidW = new IFID();
		ifidR = new IFID();
		idexW = new IDEX();
		idexR = new IDEX();
		exmemW = new EXMEM();
		exmemR = new EXMEM();
		memwbW = new MEMWB();
		memwbR = new MEMWB();
		
		
		int tempPC = programCounter;
		
		for (int i = 0; i < instrucArray.length; i ++) {
			
			pcArray[i] = tempPC;
			
			
			
		}
		
		
		//program loop
		for(int i = 0; i < instrucArray.length; i++) {
			
			int clockCycle = i + 1;
			
			System.out.println("\nClock Cycle " + clockCycle + " (Before we copy the write side of pipeline registers to the read side)");
			
			IF_stage(instrucArray[i]);
			
			ID_stage();
			
			EX_stage();
			
			MEM_stage();
			
			WB_stage();
			
			Print_out_everything();
			
			Copy_write_to_read();
			
		}
		
		
		
	}
	
	public  void IF_stage(int instruction){
		
		//set instruction variable based on what was read from memory
		ifidW.setInstructionInitial(instruction);
		
		//increment program counter and set for ifid Write
		ifidW.setProgramCounter(programCounter += 4);
		
		
		
	}
	
	
	public void ID_stage() {
		
		//Both format variables
		int opcode_31_26;
		int src1reg_25_21;//read reg 1
		int src2reg_20_16;//read reg 2
		String instString = "";
		
		//R format variables
		int funcInt_5_0 = 0;
		
		
		
		//I format variables
		short offset_15_0;
		
		//string for decoded instruction
		rtn = "";
		
		//determine op code first, if opcode is 0 then it is R format. If op code is greater than 0 then it is I format
		opcode_31_26 = ((ifidR.getInstructionInitial() & opcodeChunk) >>>26);//bitwise anding, then shifting right by a number of digits
		
		//I format isolating chunks and setting control bits, and setting offset
		if(opcode_31_26 > 0) {
			
			//isolate the chunks for src/dest
			src1reg_25_21 = (ifidR.getInstructionInitial() & src1regChunk)>>>21;//bitwise anding, then shifting right by a number of digits
			src2reg_20_16 = (ifidR.getInstructionInitial() & srcdestChunk)>>>16;
			
			
			//isolate the chunks for write regs
			idexW.setWriteReg_20_16(((ifidR.getInstructionInitial() & src2regChunk)>>>16));
			idexW.setWriteReg_15_11((ifidR.getInstructionInitial() & destregChunk)>>>11);
			
			
			offset_15_0 = (short) (ifidR.getInstructionInitial() & offsetChunk);
			
			//determine the instString and set Controls
			if(opcode_31_26==0x20) {
				//controls for a load
				instString = "lb";
				
				idexW.setRegDest(0);
				idexW.setALUSrc(1);;
				idexW.setMemRead(1);
				idexW.setMemWrite(0);
				idexW.setMemToReg(1);
				idexW.setRegWrite(1);
				
				//setting ALUop to binary 00 (add) for load
				idexW.setALUOp(0b00);

				
			}
			//controls for a store
			if(opcode_31_26==0x28) {
				instString = "sb";
				
				idexW.setRegDest(-1);
				idexW.setALUSrc(1);;
				idexW.setMemRead(0);
				idexW.setMemWrite(1);
				idexW.setMemToReg(-1);
				idexW.setRegWrite(0);
				
				//setting ALUop to binary 00 (add) for store
				idexW.setALUOp(0b00);
				

			}
			
			//setting offset
			//idexW.setSEOffset_15_0(signExtend(offset_15_0, 16));//how to sign extend? // google this 
			idexW.setSEOffset_15_0(offset_15_0);

			//function set to garbage for I format;
			idexW.setFunction(-1);
			
			//place decoded I instruction in a string
			rtn += Integer.toHexString(ifidR.getInstructionInitial()) + "\t" + instString + " $" + src2reg_20_16 + ", " + offset_15_0 + " ($" + src1reg_25_21 + ")";              

			
		//R format isolating chunks and setting control bits
		}else {
			
			//isolate the chunks
			
			
			src1reg_25_21 = (ifidR.getInstructionInitial() & src1regChunk)>>>21;//bitwise anding, then shifting right by a number of digits
			src2reg_20_16 = (ifidR.getInstructionInitial() & src2regChunk)>>>16;
			
			idexW.setWriteReg_15_11((ifidR.getInstructionInitial() & destregChunk)>>>11);
			idexW.setWriteReg_20_16(((ifidR.getInstructionInitial() & src2regChunk)>>>16));
			
			funcInt_5_0 = (ifidR.getInstructionInitial() & functionChunk);
			

			
			//determine the instString and set Controls
			if(funcInt_5_0==0x00) {
				//everything to 0 for a nop
				instString = "nop";
		
				idexW.setRegDest(0);
				idexW.setALUSrc(0);;
				idexW.setMemRead(0);
				idexW.setMemWrite(0);
				idexW.setMemToReg(0);
				idexW.setRegWrite(0);
				idexW.setALUOp(0b10); 
				idexW.setSEOffset_15_0((short) 0);

				
				
			}
			
			//controls for add
			if(funcInt_5_0==0x20) {
				instString = "add";
				
				idexW.setRegDest(1);
				idexW.setALUSrc(0);;
				idexW.setMemRead(0);
				idexW.setMemWrite(0);
				idexW.setMemToReg(0);
				idexW.setRegWrite(1);
				
				
				
			}
			//controls for subtract
			if(funcInt_5_0==0x22) {
				instString = "sub";
				
				idexW.setRegDest(1);
				idexW.setALUSrc(0);;
				idexW.setMemRead(0);
				idexW.setMemWrite(0);
				idexW.setMemToReg(0);
				idexW.setRegWrite(1);
				
				
			}
			
			
			//set function to whatever it is for the R format
			idexW.setFunction(funcInt_5_0);

			//Set ALUop to binary 10 for R format instruction
			idexW.setALUOp(0b10);
			
			//place decoded R instruction in a string
			rtn += Integer.toHexString(ifidR.getInstructionInitial()) + "\t" + instString + " $" + idexW.getWriteReg_15_11() + ", $" + src1reg_25_21 + ", $" + src2reg_20_16; 

			
		}
		
		
		
		//Reading Registers
		idexW.setReadReg1Value(Regs[src1reg_25_21]);
		idexW.setReadReg2Value(Regs[src2reg_20_16]);
		//passing program counter
		idexW.setProgramCounter(ifidR.getProgramCounter());
		
		
		
		
	}
	
	public void EX_stage() {
		
		
		
		//value from mux based on ALUSrc
		short valueFromMux = 0;
		
		
		
		//RegDest Mux
		if(idexR.getRegDest() == 0) {
			//lb
			exmemW.setWriteRegNum(idexR.getWriteReg_20_16());
			
		}else if(idexR.getRegDest() == 1) {
			

			//R-type
			exmemW.setWriteRegNum(idexR.getWriteReg_15_11());
		}
		
		
		
		//ALUSrcMux
		if(idexR.getALUSrc() == 0) {
			//R-types
			valueFromMux = (short) idexR.getReadReg2Value();
			

			
		}else if(idexR.getALUSrc() == 1) {
			//lb or sb
			valueFromMux = idexR.getSEOffset_15_0();
			
			
		}
		
		
		//ALU Control and ALU
		if(idexR.getALUOp() == 0b00) {
			//if load or store then add with offset
			exmemW.setALUResult( (short) (idexR.getReadReg1Value() + valueFromMux));
				
		}//if R format then use the function to fettermine what to do
		else if(idexR.getALUOp() == 0b10){
				
				
			//nop
			if(idexR.getFunction() == 0x00) {
				//everything is 0
				exmemW.setALUResult((short) 0);
					
			//add
			}else if(idexR.getFunction() == 0x20) {
				//add reg 1 value with reg 2 value
				exmemW.setALUResult((short) (idexR.getReadReg1Value() + valueFromMux));

					
			//sub
			}else if(idexR.getFunction() == 0x22) {
				//subtract reg 2 value from reg 1 value
				exmemW.setALUResult((short) (idexR.getReadReg1Value() - valueFromMux));

					
			}
				
				
		}
		
		exmemW.setSWValue(idexR.getReadReg2Value());
		exmemW.setMemRead(idexR.getMemRead());
		exmemW.setMemWrite(idexR.getMemWrite());
		exmemW.setMemToReg(idexR.getMemToReg());
		exmemW.setRegWrite(idexR.getRegWrite());
		exmemW.setProgramCounter(idexR.getProgramCounter());

			
		
	}
	
	public void MEM_stage() {
		
		//MemWrite
		if(exmemR.getMemWrite() == 0) {//lb
			
			//No writing to memory with sb
			
			
		}else if(exmemR.getMemWrite() == 1) {//sb
			
			for(int i = 0; i < Main_Mem.length; i++ ) {
				if(i == exmemR.getALUResult()) {
					
					//walks memory array to store SWvalue using the address in ALUResult
					Main_Mem[i] = (short) exmemR.getSWValue();
					
				}
				
				
			}
			
		}
		
		//MemRead
		if(exmemR.getMemRead() == 0) {//sb
			
			//No reading from memory with sb
			
		}else if(exmemR.getMemRead() == 1) {//lb
			
			for(int i = 0; i < Main_Mem.length; i++ ) {
				if(i == exmemR.getALUResult()) {
					
					//walks memory array for value using the address in ALUResult
					memwbW.setLWDataValue(Main_Mem[i]);
					
				}
				
				
			}
		
			
			
			
		}		
		
		memwbW.setALUResult(exmemR.getALUResult());
		memwbW.setMemToReg(exmemR.getMemToReg());
		memwbW.setRegWrite(exmemR.getRegWrite());
		memwbW.setWriteRegNum(exmemR.getWriteRegNum());
		memwbW.setProgramCounter(exmemR.getProgramCounter());
		
		
	}
	
	public void WB_stage() {
		
		
		
		if(memwbR.getRegWrite() == 0) {
			
			//no writing to reg (sb)
			
			
		}else if(memwbR.getRegWrite() == 1) {
			//R-types and lb
			
			
			//MemtoReg Mux
			if(memwbR.getMemToReg() == 0) {
				//R-Types
				for(int i = 0; i < Regs.length; i++) {
					if(i == memwbR.getWriteRegNum()) {
						Regs[i] = memwbR.getALUResult();
					}
					
				}
				
				
			}else if(memwbR.getMemToReg() == 1) {
				//lb
				
				for(int i = 0; i < Regs.length; i++) {
					if(i == memwbR.getWriteRegNum()) {
						Regs[i] = memwbR.getLWDataValue();
					}
					
				}
				
				
				
			}		
			
			
		}
		
		
		
	
	}
	
	public void Print_out_everything() {
		
		
		
		//prints all registers
		System.out.println();
		System.out.println("The 32 Registers: ");
		for(int i = 0; i < Regs.length; i++) {
			
			if(i % 8 == 0) {
				System.out.println();
			}
			System.out.printf("%-13s", "$" + i + " = 0x" + Integer.toHexString(Regs[i]));
		}
	
		
		//IF/ID Write
		System.out.println();
		System.out.println();
		System.out.println("IF/ID Write (written to by the IF stage)");
		System.out.printf("%-25s", "Instruction: 0x" + Integer.toHexString(ifidW.getInstructionInitial()));
		System.out.printf("%-15s %n", "Program Counter: 0x" + Integer.toHexString(ifidW.getProgramCounter()));
		
		//IF/ID Read
		System.out.println();
		System.out.println("IF/ID Read (read by the ID stage)");
		System.out.printf("%-25s", "Instruction: 0x" + Integer.toHexString(ifidR.getInstructionInitial()));
		System.out.printf("%-25s %n", "Program Counter: 0x" + Integer.toHexString(ifidR.getProgramCounter()));
		
		//ID/EX Write
		System.out.println();
		System.out.println("ID/EX Write (written to by the ID stage)");
		System.out.printf("%-15s %n", "Control: RegDst=" + idexW.getRegDest() + ", ALUSrc=" + idexW.getALUSrc() + ", ALUOP=0b" + Integer.toBinaryString(idexW.getALUOp()) + ", MemRead=" + idexW.getMemRead() + ", MemWrite=" + idexW.getMemWrite() + ", MemToReg=" + idexW.getMemToReg() + ", RegWrite=" + idexW.getRegWrite());
		System.out.printf("%-25s", "ReadReg1Value = 0x" + Integer.toHexString(idexW.getReadReg1Value()));
		System.out.printf("%-25s %n", "ReadReg2Value = 0x" + Integer.toHexString(idexW.getReadReg2Value()));
		System.out.printf("%-30s", "SEOffset = 0x" + Integer.toHexString(idexW.getSEOffset_15_0()));
		System.out.printf("%-30s", "WriteReg_20_16 = $" + idexW.getWriteReg_20_16());
		System.out.printf("%-30s", "WriteReg_15_11 = $" + idexW.getWriteReg_15_11());
		if(idexW.getFunction() == -1) {
			System.out.printf("%-25s %n", "Function = " + idexW.getFunction());
		}else {
			System.out.printf("%-25s %n", "Function = 0x" + Integer.toHexString(idexW.getFunction()));
		}
		//System.out.println();

		//System.out.println(rtn);
		
		//ID/EX Read
		System.out.println();
		System.out.println("ID/EX Read (read by the EX stage)");
		System.out.printf("%-15s %n", "Control: RegDst=" + idexR.getRegDest() + ", ALUSrc=" + idexR.getALUSrc() + ", ALUOP=0b" + Integer.toBinaryString(idexR.getALUOp()) + ", MemRead=" + idexR.getMemRead() + ", MemWrite=" + idexR.getMemWrite() + ", MemToReg=" + idexR.getMemToReg() + ", RegWrite=" + idexR.getRegWrite());
		System.out.printf("%-25s", "ReadReg1Value = 0x" + Integer.toHexString(idexR.getReadReg1Value()));
		System.out.printf("%-25s %n", "ReadReg2Value = 0x" + Integer.toHexString(idexR.getReadReg2Value()));
		System.out.printf("%-25s", "SEOffset = 0x" + Integer.toHexString(idexR.getSEOffset_15_0()));
		System.out.printf("%-30s", "WriteReg_20_16 = $" + idexR.getWriteReg_20_16());
		System.out.printf("%-30s", "WriteReg_15_11 = $" + idexR.getWriteReg_15_11());
		if(idexR.getFunction() == -1) {
			System.out.printf("%-25s %n", "Function = " + idexR.getFunction());
		}else {
			System.out.printf("%-25s %n", "Function = 0x" + Integer.toHexString(idexR.getFunction()));
		}
		
		
		//EX/MEM Write
		System.out.println();
		System.out.println("EX/MEM Write (written to by the EX stage)");
		System.out.printf("%-15s %n", "Control: MemRead=" + exmemW.getMemRead() + ", MemWrite=" + exmemW.getMemWrite() + ", MemToReg=" + exmemW.getMemToReg() + ", RegWrite=" + exmemW.getRegWrite());
		System.out.printf("%-20s", "ALUResult = 0x" + Integer.toHexString(exmemW.getALUResult()));
		System.out.printf("%-20s", "SWValue = 0x" + Integer.toHexString(exmemW.getSWValue()));
		System.out.printf("%-20s %n", "WriteRegNum = $" + exmemW.getWriteRegNum());

		
		//EX/MEM Read
		System.out.println();
		System.out.println("EX/MEM Read (read by the MEM stage)");
		System.out.printf("%-15s %n", "Control: MemRead=" + exmemR.getMemRead() + ", MemWrite=" + exmemR.getMemWrite() + ", MemToReg=" + exmemR.getMemToReg() + ", RegWrite=" + exmemR.getRegWrite());
		System.out.printf("%-20s", "ALUResult = 0x" + Integer.toHexString(exmemR.getALUResult()));
		System.out.printf("%-20s", "SWValue = 0x" + Integer.toHexString(exmemR.getSWValue()));
		System.out.printf("%-20s %n", "WriteRegNum = $" + exmemR.getWriteRegNum());

		//MEM/WB Write
		System.out.println();
		System.out.println("MEM/WB Write (written to by the MEM stage)");
		System.out.printf("%-15s %n", "Control: MemToReg=" + memwbW.getMemToReg() + ", RegWrite=" + memwbW.getRegWrite());
		System.out.printf("%-40s", "LWDataValue = 0x" + Integer.toHexString(memwbW.getLWDataValue()));
		System.out.printf("%-20s", "ALUResult = 0x" + Integer.toHexString(memwbW.getALUResult()));
		System.out.printf("%-20s %n", "WriteRegNum = $" + memwbW.getWriteRegNum());

		
		//MEM/WB Read
		System.out.println();
		System.out.println("MEM/WB Read (read by the WB stage)");
		System.out.printf("%-15s %n", "Control: MemToReg=" + memwbR.getMemToReg() + ", RegWrite=" + memwbR.getRegWrite());
		System.out.printf("%-40s", "LWDataValue = 0x" + Integer.toHexString(memwbR.getLWDataValue()));
		System.out.printf("%-20s", "ALUResult = 0x" + Integer.toHexString(memwbR.getALUResult()));
		System.out.printf("%-20s %n", "WriteRegNum = $" + memwbR.getWriteRegNum());

		System.out.println();
		System.out.println();

		//printMainMemory(Main_Mem);
		
	}
	
	public void Copy_write_to_read() {
		
		//ifidR = ifidW
		ifidR.setInstructionInitial(ifidW.getInstructionInitial());
		ifidR.setProgramCounter(ifidW.getProgramCounter());
		
		//idexR = idexW
		idexR.setRegDest(idexW.getRegDest());
		idexR.setALUSrc(idexW.getALUSrc());
		idexR.setALUOp(idexW.getALUOp());
		idexR.setMemRead(idexW.getMemRead());
		idexR.setMemWrite(idexW.getMemWrite());
		idexR.setMemToReg(idexW.getMemToReg());
		idexR.setRegWrite(idexW.getRegWrite());
		idexR.setReadReg1Value(idexW.getReadReg1Value());
		idexR.setReadReg2Value(idexW.getReadReg2Value());
		idexR.setSEOffset_15_0(idexW.getSEOffset_15_0());
		idexR.setWriteReg_20_16(idexW.getWriteReg_20_16());
		idexR.setWriteReg_15_11(idexW.getWriteReg_15_11());
		idexR.setFunction(idexW.getFunction());
		idexR.setReadReg1Value(idexW.getReadReg1Value());
		idexR.setReadReg2Value(idexW.getReadReg2Value());
		idexR.setProgramCounter(idexW.getProgramCounter());
		
		
		
		//exmemR = exmemW
		exmemR.setMemRead(exmemW.getMemRead());
		exmemR.setMemWrite(exmemW.getMemWrite());
		exmemR.setMemToReg(exmemW.getMemToReg());
		exmemR.setRegWrite(exmemW.getRegWrite());
		exmemR.setALUResult(exmemW.getALUResult());
		exmemR.setSWValue(exmemW.getSWValue());
		exmemR.setWriteRegNum(exmemW.getWriteRegNum());
		exmemR.setProgramCounter(exmemW.getProgramCounter());

		
		//memwbR = memwbW
		memwbR.setMemToReg(memwbW.getMemToReg());
		memwbR.setRegWrite(memwbW.getRegWrite());
		memwbR.setALUResult(memwbW.getALUResult());
		memwbR.setLWDataValue(memwbW.getLWDataValue());
		memwbR.setWriteRegNum(memwbW.getWriteRegNum());
		memwbR.setProgramCounter(memwbW.getProgramCounter());
		
		
	}
	
	//generates main memory array
	public static short[] generateMainMemory(int mmSize) {
		
		
		
		short[] Main_Mem = new short[mmSize];
		
		int j = -1;
		
		for(int i = 0 ; i < mmSize; i++) {
			
			j++;
			if( j > 0xFF) {
				j = 0;
			}
		
			Main_Mem[i] = (short) j;
			
			//System.out.println(Integer.toHexString(i) + " gets " + Integer.toHexString(j));
		}
		return Main_Mem;

		
	}
	
	//generates the reg array
	public static int[] generateRegs(int regSize) {
		
		int[] Regs = new int[regSize];
		
		
		for(int i = 0; i < regSize; i++) {
			
			if(i==0) {
				
				Regs[i] = 0;
			}else {
				Regs[i] = 0x100 + i;
			}
			
			
			
		}
		
		
		return Regs;
		
	}
	
	//prints the reg array
	public static void printRegs(int[] Regs) {
		

		for(int k = 0; k < Regs.length; k++) {
			
			
			System.out.println("Reg $" + k + ": "+ Integer.toHexString(Regs[k]));

			
		}
		
	}
	
	
	
	//prints the main memory array
	public static void printMainMemory(short[] Main_Mem) {
		

		for(int k = 0; k < Main_Mem.length; k++) {
			
			
			System.out.println("Slot " + Integer.toHexString(k) + ": "+ Integer.toHexString(Main_Mem[k]));

			
		}
		
	}
	
	
	
	//method used to decode instructions (unused)
	public static String decodeInstruction(int instruction) {
		
		String rtn = "";
		
		//Both format variables
		int opcode_31_26;
		int src1reg_25_21;//read reg 1
		int src2reg_20_16;//read reg 2
		String instString = "";
				
		//R format variables
		int funcInt_5_0 = 0;
				
		int destreg_15_11 = 0;
				
		//I format variables
		short offset_15_0;
		
		//Both chunks
		int opcodeChunk = 0b11111100000000000000000000000000;
		int src1regChunk = 0b00000011111000000000000000000000;
		
		//R Chunks
		int src2regChunk = 0b00000000000111110000000000000000;
		int destregChunk = 0b00000000000000001111100000000000;
		int functionChunk = 0b00000000000000000000000000111111;
		
		//I chunks
		int srcdestChunk = 0b00000000000111110000000000000000;
		int offsetChunk = 0b00000000000000001111111111111111;
		
		
		opcode_31_26 = ((instruction & opcodeChunk) >>>26);//bitwise anding, then shifting right by a number of digits
		
		//I format isolating chunks and setting control bits, and setting offset
		if(opcode_31_26 > 0) {
			
			//isolate the chunks
			src1reg_25_21 = (instruction & src1regChunk)>>>21;//bitwise anding, then shifting right by a number of digits
			src2reg_20_16 = (instruction & srcdestChunk)>>>16;
			
			

			
			offset_15_0 = (short) (instruction & offsetChunk);
			
			//determine the instString and set Controls
			if(opcode_31_26==0x20) {
				instString = "lb";
				
				

				
			}
			if(opcode_31_26==0x28) {
				instString = "sb";
				
				
				

			}
			


			//place decoded I instruction in a string
			rtn += "0x" + Integer.toHexString(instruction) + "\t" + instString + " $" + src2reg_20_16 + ", " + offset_15_0 + " ($" + src1reg_25_21 + ")";              

			
		//R format isolating chunks and setting control bits
		}else {
			
			//isolate the chunks
			
			
			src1reg_25_21 = (instruction & src1regChunk)>>>21;//bitwise anding, then shifting right by a number of digits
			src2reg_20_16 = (instruction & src2regChunk)>>>16;
			destreg_15_11 = (instruction & destregChunk)>>>11;
			funcInt_5_0 = (instruction & functionChunk);
			

			
			//determine the instString and set Controls
			if(funcInt_5_0==0x00) {
				instString = "nop";
				

				
				
			}
			
			
			if(funcInt_5_0==0x20) {
				instString = "add";
				

				
				
			}
			if(funcInt_5_0==0x22) {
				instString = "sub";
				
				

				
				
			}
			
			
			
			//place decoded R instruction in a string
			rtn += "0x" + Integer.toHexString(instruction) + "\t" + instString + " $" + destreg_15_11 + ", $" + src1reg_25_21 + ", $" + src2reg_20_16; 

			
		}
		
		
		
		
		
		return rtn;

	}
	

}
