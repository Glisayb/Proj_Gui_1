package Exceptions;


public class IrresponsibleSenderWithDangerousGoodsException extends Exception{
    public IrresponsibleSenderWithDangerousGoodsException(String id){
          super(String.format
                ("W zwiazku z decyzjami celniczymi dot. bezpieczenstwa składowania i magazynowania towarów\n " +
                                "Kontener o id : '%s' zostaje zutylizowany poprzez wywiezienie z terenu portu,",
                        id));
    }
}

