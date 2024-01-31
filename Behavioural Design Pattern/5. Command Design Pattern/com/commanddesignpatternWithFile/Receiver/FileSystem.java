package com.commanddesignpatternWithFile.Receiver;


//Receiver
public class FileSystem {
	
	boolean isOpen;
	
	public void openFile() {
		isOpen=true;
		System.out.println("file is opened");
	}
	
	public void closeFile() {
		isOpen=false;
		System.out.println("file is closed");
	}

	public void copyFile() {
		System.out.println("file is copied");
		
	}
	
	public void pasteFile() {
		System.out.println("file is pasted");
		
	}

}
