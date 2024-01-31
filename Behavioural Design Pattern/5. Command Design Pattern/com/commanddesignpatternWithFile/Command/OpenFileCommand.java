package com.commanddesignpatternWithFile.Command;

import com.commanddesignpatternWithFile.Receiver.FileSystem;

public class OpenFileCommand implements Command {
	
	FileSystem fileSystem;
	
	public OpenFileCommand(FileSystem fs) {
		this.fileSystem=fs;
	}

	@Override
	public void execute() {
		this.fileSystem.openFile();

	}

}
//we can have n number of commands like deleteFile,CopyFile,PasteFile etc..,