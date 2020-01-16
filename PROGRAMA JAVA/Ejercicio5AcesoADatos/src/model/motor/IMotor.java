package model.motor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IMotor {
	public Connection connect();

    public int execute(PreparedStatement pst);

    public ResultSet executeQuery(PreparedStatement pst);

    public void disconnect();

}

