package com.commanddesignpatternWithFile.Command;

import com.commanddesignpatternWithFile.Receiver.FileSystem;

public class CopyFile implements Command {

	FileSystem fileSystem;
	
	public CopyFile(FileSystem fs) {
		this.fileSystem=fs;
		}
	
	@Override
	public void execute() {
		this.fileSystem.copyFile();

	}

}
