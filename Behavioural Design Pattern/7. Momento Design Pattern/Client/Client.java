package Client;

import CareTaker.ConfigurationCareTaker;
import Memento.ConfigurationMemento;
import Originator.ConfigurationOriginator;

public class Client {
    public static  void main(String[] args){
        ConfigurationCareTaker careTakerObject=new ConfigurationCareTaker();
        //initiate State of the Originator
        ConfigurationOriginator originatorObject=new ConfigurationOriginator(5,10);

        //save It
        ConfigurationMemento snapShot1=originatorObject.createMemento();
        //add it to History
        careTakerObject.addMemento(snapShot1);
        //originator changing to new state
        originatorObject.setHeight(12);
        originatorObject.setWeight(7);

        //save It
        ConfigurationMemento snapShot2=originatorObject.createMemento();
        //add it to History
        careTakerObject.addMemento(snapShot2);
        //originator changing to new state
        originatorObject.setHeight(14);
        originatorObject.setWeight(9);

        //UNDO
        ConfigurationMemento restoredStateMementoObj=careTakerObject.undo();
        originatorObject.restoreMemento(restoredStateMementoObj);

        System.out.println("height: "+originatorObject.height+" weight: "+originatorObject.weight);

    }
}
