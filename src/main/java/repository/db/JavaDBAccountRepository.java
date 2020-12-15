package repository.db;

import model.Account;
import model.AccountStatus;
import repository.AccountRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JavaDBAccountRepository implements AccountRepository {
    private final static String DB_TABLE = "account";
    private static String SQL;

    public Account save(Account val) throws Exception {
        val.setAccountStatus(AccountStatus.ACTIVE);
        SQL = String.format("INSERT INTO %s (name, AccountStatus) VALUES ('%s', '%s')", DB_TABLE,
                val.getName(), val.getAccountStatus());
        DatabaseHandler.getStatement(SQL).execute();
        SQL = String.format("select* from %s where name='%s' order by idAccount", DB_TABLE, val.getName());
        return sqlToAccount(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public void deleteById(Long id) throws Exception {
        SQL = String.format("delete from %s where idAccount=%s", DB_TABLE, id);
        DatabaseHandler.getStatement(SQL).execute();
    }

    public Account getByID(Long id) throws Exception {
        SQL = String.format("select* from %s where idAccount=%s order by idAccount", DB_TABLE, id);
        return sqlToAccount(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public List<Account> getAll() throws Exception {
        SQL = String.format("select* from %s order by idAccount", DB_TABLE);
        return sqlToAccounts(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    public Account update(Account val) throws Exception {
        SQL = String.format("UPDATE %s SET name = '%s' where idAccount =%s", DB_TABLE, val.getName(), val.getId());
        DatabaseHandler.getStatement(SQL).executeUpdate();
        SQL = String.format("select* from %s where name='%s' order by idAccount", DB_TABLE, val.getName());
        return sqlToAccount(DatabaseHandler.getStatement(SQL).executeQuery());
    }

    private Account sqlToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        while (resultSet.next()){
            account.setId((long)resultSet.getInt(1));
            account.setName(resultSet.getString(2));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString(3)));
        }
        return account;
    }

    private List<Account> sqlToAccounts(ResultSet resultSet) throws SQLException{
        Account account;
        List<Account> list = new ArrayList<Account>();
        while (resultSet.next()){
            account = new Account();
            account.setId((long)resultSet.getInt(1));
            account.setName(resultSet.getString(2));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString(3)));
            list.add(account);
        }
        return list;
    }
}
