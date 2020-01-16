package model.connectionFactory;

import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class ConnectionFactory {

	private final static int ORACLE = 0;
	private final static int MYSQL = 1;

	private static int bdEscogida;

	public ConnectionFactory() {

	}

	public static void setDb(int bd) {
		if (bd == ORACLE) {
			bdEscogida = ORACLE;
		} else if (bd == MYSQL) {
			bdEscogida = MYSQL;
		}
	}

	public static Motor getInstance() {
		switch (bdEscogida) {
		case ORACLE:
			return new MotorOracle();
		case MYSQL:
			return new MotorMySQL();
		default:
			return null;
		}
	}
}
