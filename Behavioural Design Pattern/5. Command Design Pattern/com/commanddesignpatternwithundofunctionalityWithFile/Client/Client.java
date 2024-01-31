package com.commanddesignpatternwithundofunctionalityWithFile.Client;

import com.commanddesignpatternwithundofunctionalityWithFile.Command.CopyFile;
import com.commanddesignpatternwithundofunctionalityWithFile.Receiver.FileSystem;
import com.commanddesignpatternwithundofunctionalityWithFile.Invoker.Invoker;
import com.commanddesignpatternwithundofunctionalityWithFile.Command.OpenFileCommand;

public class Client {

	public static void main(String[] args) {
		
		FileSystem fs=new FileSystem();
		Invoker ivk=new Invoker();
		//command for opening file
		ivk.setCommand(new OpenFileCommand(fs));
		ivk.performAction();
		ivk.performAction();//just think like a button, performed by client
		
		//undoing of previously performed action
		ivk.undoAction();
		ivk.undoAction();
		ivk.undoAction();
		
		//command for coping the file
		ivk.setCommand(new CopyFile(fs));
		ivk.performAction();
		
		//undoing the performed action
		ivk.undoAction();
	}
	
	

}
