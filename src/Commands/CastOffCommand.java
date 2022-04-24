package Commands;

import Commands.ICommand;
import com.company.StaticClasses;

public class CastOffCommand implements ICommand {

    public String id;

    public CastOffCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        StaticClasses.farewell(id);
    }
}


