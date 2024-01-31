package com.commanddesignpatternwithundofunctionalityWithFile.Command;


import com.commanddesignpatternwithundofunctionalityWithFile.Receiver.FileSystem;

public class CloseFileCommand implements Command {

	FileSystem fileSystem;
	public CloseFileCommand(FileSystem fs) {
		this.fileSystem=fs;
		}
	@Override
	public void execute() {
	this.fileSystem.closeFile();

	}
	@Override
	public void undo() {
	this.fileSystem.openFile();
		
	}

}


//we can have n number of commands like deleteFile,CopyFile,PasteFile etc..,