package cs472_ProjectThree;

public class IDEX {
	
	//Control Bits
	private int regDest;
	private int memRead;
	private int memToReg;
	private int ALUOp;
	private int memWrite;
	private int ALUSrc;
	private int regWrite;

	//Pipeline Variables
	private int readReg1Value;
	private int readReg2Value;
	private int writeReg_20_16;
	private int writeReg_15_11;
	private short SEOffset_15_0;
	private int function;
	private int writeData;
	private int readData1;
	private int readData2;
	private int programCounter;

	
	private String instruc;
	
	
	public IDEX(){
		regDest = 0;
		memRead = 0;
		memToReg = 0;
		ALUOp = 0;
		memWrite = 0;
		ALUSrc = 0;
		regWrite = 0;
		
		readReg1Value = 0;
		readReg2Value = 0;
		writeReg_20_16 = 0;
		writeReg_15_11 = 0;
		SEOffset_15_0 = 0;
		function = 0;
		writeData = 0;
		instruc = "nop";
		
		programCounter = 0;

	}
	
	
	
	
	
	
	public void signExtend() {
		
	}
	
	
	public void MUX() {
		
		
	}






	public int getRegDest() {
		return regDest;
	}






	public void setRegDest(int regDest) {
		this.regDest = regDest;
	}






	public int getMemRead() {
		return memRead;
	}






	public void setMemRead(int memRead) {
		this.memRead = memRead;
	}






	public int getMemToReg() {
		return memToReg;
	}






	public void setMemToReg(int memToReg) {
		this.memToReg = memToReg;
	}






	public int getALUOp() {
		return ALUOp;
	}






	public void setALUOp(int aLUOp) {
		ALUOp = aLUOp;
	}






	public int getMemWrite() {
		return memWrite;
	}






	public void setMemWrite(int memWrite) {
		this.memWrite = memWrite;
	}






	public int getALUSrc() {
		return ALUSrc;
	}






	public void setALUSrc(int aLUSrc) {
		ALUSrc = aLUSrc;
	}






	public int getRegWrite() {
		return regWrite;
	}






	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}






	public int getReadReg1Value() {
		return readReg1Value;
	}






	public void setReadReg1Value(int readReg1Value) {
		this.readReg1Value = readReg1Value;
	}






	public int getReadReg2Value() {
		return readReg2Value;
	}






	public void setReadReg2Value(int readReg2Value) {
		this.readReg2Value = readReg2Value;
	}






	public int getWriteReg_20_16() {
		return writeReg_20_16;
	}






	public void setWriteReg_20_16(int writeReg_20_16) {
		this.writeReg_20_16 = writeReg_20_16;
	}






	public int getWriteReg_15_11() {
		return writeReg_15_11;
	}






	public void setWriteReg_15_11(int writeReg_15_11) {
		this.writeReg_15_11 = writeReg_15_11;
	}






	public short getSEOffset_15_0() {
		return SEOffset_15_0;
	}






	public void setSEOffset_15_0(short sEOffset_15_0) {
		SEOffset_15_0 = sEOffset_15_0;
	}






	public int getFunction() {
		return function;
	}






	public void setFunction(int function) {
		this.function = function;
	}






	public int getWriteData() {
		return writeData;
	}






	public void setWriteData(int writeData) {
		this.writeData = writeData;
	}




	public String getInstruc() {
		return instruc;
	}






	public void setInstruc(String instruc) {
		this.instruc = instruc;
	}
	
	public int getProgramCounter() {
		return programCounter;
	}



	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}



}
