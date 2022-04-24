package Exceptions;


import Models.Sender;

public class IrresponsibleSenderWithDangerousGoodsException extends Exception{
    public IrresponsibleSenderWithDangerousGoodsException(String id) {
        super(String.format
                ("W zwiazku z decyzjami celniczymi dot. bezpieczenstwa składowania i magazynowania towarów\n " +
                                "Kontener o id : '%s' zostaje zutylizowany poprzez wywiezienie z terenu portu,",
                        id));
    }
    public IrresponsibleSenderWithDangerousGoodsException(String id, Sender sender){
                super(String.format("W zwiazku z decyzjami celniczymi dot. bezpieczenstwa składowania i magazynowania towarów\n " +
                        "Kontener o id : '%s' zostaje zutylizowany poprzez wywiezienie z terenu portu,"+
                        "%s %s otrzymuje ostrzezenie",
                id,
                sender.getTitle(),
                sender.toString()));
        sender.addWarnings();
    }
}

