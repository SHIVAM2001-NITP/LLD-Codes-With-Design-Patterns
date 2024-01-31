package CareTaker;

import Memento.ConfigurationMemento;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


public class ConfigurationCareTaker {
    List<ConfigurationMemento> history=new ArrayList<>();
    public void addMemento(ConfigurationMemento memento){
        history.add(memento);
    }

    public ConfigurationMemento undo(){
        if(!history.isEmpty()){
            int lastMementoIndex=history.size();
            //get the last memento from the list
            ConfigurationMemento lastMemento=history.get(lastMementoIndex-1);
            //remove the last memento from the list now
            history.remove(lastMementoIndex-1);
            return lastMemento;
        }
        return null;
    }
}
