package com.commanddesignpatternwithundofunctionalityWithFile.Command;

import com.commanddesignpatternwithundofunctionalityWithFile.Receiver.FileSystem;

public class PasteFile implements Command {
	
	FileSystem fs;
	 
	public PasteFile(FileSystem fileSystem) {
		this.fs=fileSystem;
	}
	

	@Override
	public void execute() {
		this.fs.pasteFile();

	}


	@Override
	public void undo() {
		this.fs.copyFile();
		
	}

}
