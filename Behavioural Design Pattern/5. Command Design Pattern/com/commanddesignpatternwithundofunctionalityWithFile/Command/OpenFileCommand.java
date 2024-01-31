package com.commanddesignpatternwithundofunctionalityWithFile.Command;

import com.commanddesignpatternwithundofunctionalityWithFile.Receiver.FileSystem;

public class OpenFileCommand implements Command {
	
	FileSystem fileSystem;
	
	public OpenFileCommand(FileSystem fs) {
		this.fileSystem=fs;
	}

	@Override
	public void execute() {
		this.fileSystem.openFile();

	}

	@Override
	public void undo() {
		this.fileSystem.closeFile();
		
	}

}
//we can have n number of commands like deleteFile,CopyFile,PasteFile etc..,