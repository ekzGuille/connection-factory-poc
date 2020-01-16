package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Taxi;
import model.connectionFactory.ConnectionFactory;
import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class TaxiDAO implements DAO<Taxi, String> {

	private PreparedStatement pst;
	private Motor motor;

	public TaxiDAO() {
		motor = ConnectionFactory.getInstance();
	}

	@Override
	public int add(Taxi bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "INSERT INTO SYS.TAXI (d_mat, d_tfno, d_km) VALUES (?,?,?)";
		} else if (motor instanceof MotorMySQL) {
			sql = "INSERT INTO `taxi`(`d_mat`, `d_tfno`, `d_km`) VALUES (?,?,?)";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, bean.getD_mat());
			pst.setInt(2, bean.getD_tfno());
			pst.setInt(3, bean.getD_km());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(String id) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "DELETE FROM SYS.TAXI WHERE d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "DELETE FROM `taxi` WHERE `d_mat`= ?";
		}
		int resp = 0;
		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, id);

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public int update(Taxi bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "UPDATE SYS.TAXI SET d_tfno = ?, d_km = ? WHERE d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "UPDATE `taxi` SET `d_tfno`= ?,`d_km`= ? WHERE `d_mat`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, bean.getD_tfno());
			pst.setInt(2, bean.getD_km());
			pst.setString(3, bean.getD_mat());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public ArrayList<Taxi> findAll() {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TAXI";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taxi`";
		}

		ArrayList<Taxi> lstTaxi = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);

			lstTaxi = new ArrayList<>();

			while (rs.next()) {

				Taxi taxi = new Taxi();

				taxi.setD_mat(rs.getString(1));
				taxi.setD_tfno(rs.getInt(2));
				taxi.setD_km(rs.getInt(3));

				lstTaxi.add(taxi);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return lstTaxi;
	}

	@Override
	public Taxi findById(String id) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TAXI WHERE d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taxi` WHERE `d_mat` = ?";
		}

		ArrayList<Taxi> lstTaxi = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, id);

			ResultSet rs = this.motor.executeQuery(pst);

			lstTaxi = new ArrayList<>();

			while (rs.next()) {

				Taxi taxi = new Taxi();

				taxi.setD_mat(rs.getString(1));
				taxi.setD_tfno(rs.getInt(2));
				taxi.setD_km(rs.getInt(3));

				lstTaxi.add(taxi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.motor.disconnect();
		}

		Taxi taxi = null;
		if(!lstTaxi.isEmpty()) {
			taxi = lstTaxi.get(0);
		}
			
		return taxi;
	}

}
