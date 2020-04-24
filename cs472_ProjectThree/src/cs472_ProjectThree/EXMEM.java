package cs472_ProjectThree;

public class EXMEM {
	
	//Control Bits
		private int regDest;
		private int memRead;
		private int memToReg;
		private int memWrite;
		private int regWrite;
	
	//Pipeline Variables
		private int SWValue;
		private int writeRegNum;
		private short ALUResult;
		private int programCounter;

		
		private String instruc;
	
	
	public EXMEM() {
		regDest = 0;
		memRead = 0;
		memToReg = 0;
		memWrite = 0;
		regWrite = 0;

		ALUResult = 0;
		SWValue = 0;
		writeRegNum = 0;
		programCounter = 0;

	}
	
	
	
	
	public void MUX() {
		
		
		
		
	}
	
	
	public void ALU() {
		
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




	public int getMemWrite() {
		return memWrite;
	}




	public void setMemWrite(int memWrite) {
		this.memWrite = memWrite;
	}




	public int getRegWrite() {
		return regWrite;
	}




	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}




	public int getSWValue() {
		return SWValue;
	}




	public void setSWValue(int sWValue) {
		SWValue = sWValue;
	}




	public int getWriteRegNum() {
		return writeRegNum;
	}




	public void setWriteRegNum(int writeRegNum) {
		this.writeRegNum = writeRegNum;
	}




	public short getALUResult() {
		return ALUResult;
	}




	public void setALUResult(short aLUResult) {
		ALUResult = aLUResult;
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
