package model.motor;

public class MotorOracle extends Motor {

	private final String DRIVER = "jdbc:oracle";
	private final String DRIVERTYPE = "thin";
	private final String URL = "localhost";
	private final String PORT = "1521";
	private final String BDNAME = "xe";

	public MotorOracle() {
		urlDB = DRIVER + ":" + DRIVERTYPE + ":" + "@" + URL + ":" + PORT + ":" + BDNAME;

		userDB = "Guillermo";
		passDB = "Guillermo";
		driverDB = "oracle.jdbc.driver.OracleDriver";
	}

}
