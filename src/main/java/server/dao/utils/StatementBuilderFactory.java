package server.dao.utils;

public class StatementBuilderFactory {
    public static StatementDDLBuilder getDDLBuilderInstance(){
        return new StatementDDLBuilder();
    }

    public static StatementDMLBuilder getDMLBuilderInstance(){  return new StatementDMLBuilder();  }

}
