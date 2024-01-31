package com.commanddesignpatternWithFile.Command;


import com.commanddesignpatternWithFile.Receiver.FileSystem;

public class CloseFileCommand implements Command {

	FileSystem fileSystem;
	public CloseFileCommand(FileSystem fs) {
		this.fileSystem=fs;
		}
	@Override
	public void execute() {
	this.fileSystem.closeFile();

	}

}


//we can have n number of commands like deleteFile,CopyFile,PasteFile etc..,