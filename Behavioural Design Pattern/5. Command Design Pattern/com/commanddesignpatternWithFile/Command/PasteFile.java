package com.commanddesignpatternWithFile.Command;

import com.commanddesignpatternWithFile.Receiver.FileSystem;

public class PasteFile implements Command {
	
	FileSystem fs;
	 
	public PasteFile(FileSystem fileSystem) {
		this.fs=fileSystem;
	}
	

	@Override
	public void execute() {
		this.fs.pasteFile();

	}

}
