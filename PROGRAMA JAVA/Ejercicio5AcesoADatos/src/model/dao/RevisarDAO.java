package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Revisar;
import model.connectionFactory.ConnectionFactory;
import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class RevisarDAO implements DAO<Revisar, String[]> {

	private PreparedStatement pst;
	private Motor motor;

	public RevisarDAO() {
		motor = ConnectionFactory.getInstance();
	}

	@Override
	public int add(Revisar bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "INSERT INTO SYS.REVISAR (d_cod, d_mat, d_fecha) VALUES (?,?,?)";
		} else if (motor instanceof MotorMySQL) {
			sql = "INSERT INTO `revisar`(`d_cod`, `d_mat`, `d_fecha`) VALUES (?,?,?)";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, bean.getD_cod());
			pst.setString(2, bean.getD_mat());
			pst.setString(3, bean.getD_fecha());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(String[] id) {
		String d_fecha = id[0];
		String d_mat = id[1];

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "DELETE FROM SYS.REVISAR WHERE d_fecha = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "DELETE FROM `revisar` WHERE `d_fecha`= ? AND `d_mat`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, d_fecha);
			pst.setString(2, d_mat);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(Revisar bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "UPDATE SYS.REVISAR SET d_cod = ? WHERE d_fecha = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "UPDATE `revisar` SET `d_cod`= ? WHERE `d_fecha`= ? AND `d_mat`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, bean.getD_fecha());
			pst.setInt(2, bean.getD_cod());
			pst.setString(5, bean.getD_mat());

			resp = this.motor.execute(pst);
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public ArrayList<Revisar> findAll() {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.REVISAR";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `revisar`";
		}
		ArrayList<Revisar> lstRevisar = null;
		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstRevisar = new ArrayList<>();

			while (rs.next()) {
				Revisar revisar = new Revisar();

				revisar.setD_cod(rs.getInt(1));
				revisar.setD_mat(rs.getString(2));
				revisar.setD_fecha(rs.getString(3));

				lstRevisar.add(revisar);

			}
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return lstRevisar;
	}

	@Override
	public Revisar findById(String[] id) {
		String d_fecha = id[0];
		String d_mat = id[1];

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.REVISAR WHERE d_fecha = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `revisar` WHERE `d_fecha`= ? AND `d_mat`= ?";
		}
		ArrayList<Revisar> lstRevisar = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, d_fecha);
			pst.setString(2, d_mat);
			ResultSet rs = this.motor.executeQuery(pst);
			lstRevisar = new ArrayList<>();

			while (rs.next()) {
				Revisar revisar = new Revisar();

				revisar.setD_cod(rs.getInt(1));
				revisar.setD_mat(rs.getString(2));
				revisar.setD_fecha(rs.getString(3));

				lstRevisar.add(revisar);
			}
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		Revisar revisar = null;
		if (!lstRevisar.isEmpty()) {
			revisar = lstRevisar.get(0);
		}
		return revisar;
	}

}
