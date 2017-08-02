package server.dao.conf;

import common.UserImpl;
import server.dao.UserDAO;
import server.dao.queries.UserQueries;
import server.dao.utils.StatementBuilderFactory;
import server.dao.utils.StatementDDLBuilder;
import server.dao.utils.StatementDMLBuilder;

import java.sql.SQLException;

public class DB {

    public static void restart() throws SQLException {
        StatementDMLBuilder dml = StatementBuilderFactory.getDMLBuilderInstance();
        StatementDDLBuilder ddl = StatementBuilderFactory.getDDLBuilderInstance();

        restart(ddl, dml);
    }

    public static void restart(StatementDDLBuilder ddl, StatementDMLBuilder dml) throws SQLException {
        dml = StatementBuilderFactory.getDMLBuilderInstance();
        ddl = StatementBuilderFactory.getDDLBuilderInstance();

        try {
            runDeletes(dml);
            createAdmins(ddl, dml);

        }finally {
            dml.close();
            ddl.close();
        }
    }

    private static void runDeletes(StatementDMLBuilder dml) throws SQLException {
        dml.addSQL(UserQueries.deleteAll()).build();
    }

    private static void createAdmins(StatementDDLBuilder ddl, StatementDMLBuilder dml) throws SQLException {
        new UserDAO(ddl, dml).create(new UserImpl("ADMIN", "JOSE ADMIN", "ACTIVE", "Y"));
        new UserDAO(ddl, dml).create(new UserImpl("ALMOST_ADMIN", "ADMIN JR", "ACTIVE", "Y"));
    }
}
