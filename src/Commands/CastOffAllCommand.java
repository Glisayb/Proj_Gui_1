package Commands;

import Commands.ICommand;
import com.company.Main;
import com.company.StaticClasses;


public class CastOffAllCommand implements ICommand {

    public CastOffAllCommand(){

    }

    @Override
    public void execute() {
        while(Main.ships.size()>0){
            StaticClasses.farewell(Main.ships.get(Main.ships.size()-1).shipId);
        }
    }
}