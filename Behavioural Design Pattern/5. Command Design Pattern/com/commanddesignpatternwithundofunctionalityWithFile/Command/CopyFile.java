package com.commanddesignpatternwithundofunctionalityWithFile.Command;

import com.commanddesignpatternwithundofunctionalityWithFile.Receiver.FileSystem;

public class CopyFile implements Command {

	FileSystem fileSystem;
	
	public CopyFile(FileSystem fs) {
		this.fileSystem=fs;
		}
	
	@Override
	public void execute() {
		this.fileSystem.copyFile();

	}

	@Override
	public void undo() {
		this.fileSystem.pasteFile();
		
	}

}
