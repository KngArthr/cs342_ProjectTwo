package cs472_ProjectThree;

public class MEMWB {
	
	private int memToReg;
	private int regWrite;
	
	private int LWDataValue;
	private short ALUResult;
	private int WriteRegNum;
	private int programCounter;


	private String instruc;
	
	
	public MEMWB() {
		
		memToReg = 0;
		regWrite = 0;
		LWDataValue = 0;
		ALUResult = 0;
		WriteRegNum = 0;
		programCounter = 0;

		
		
	}
	
	
	public void dataMemory() {
		
	}
	
	public void MUX() {
		
	}


	public int getMemToReg() {
		return memToReg;
	}


	public void setMemToReg(int memToReg) {
		this.memToReg = memToReg;
	}


	public int getRegWrite() {
		return regWrite;
	}


	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}


	public int getLWDataValue() {
		return LWDataValue;
	}


	public void setLWDataValue(int lWDataValue) {
		LWDataValue = lWDataValue;
	}


	public short getALUResult() {
		return ALUResult;
	}


	public void setALUResult(short aLUResult) {
		ALUResult = aLUResult;
	}


	public int getWriteRegNum() {
		return WriteRegNum;
	}


	public void setWriteRegNum(int writeRegNum) {
		WriteRegNum = writeRegNum;
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
