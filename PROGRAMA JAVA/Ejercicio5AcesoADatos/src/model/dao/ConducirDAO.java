package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Conducir;
import model.connectionFactory.ConnectionFactory;
import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class ConducirDAO implements DAO<Conducir, String[]> {

	private PreparedStatement pst;
	private Motor motor;

	public ConducirDAO() {
		motor = ConnectionFactory.getInstance();
	}

	@Override
	public int add(Conducir bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "INSERT INTO SYS.CONDUCIR (d_dni, d_mat, d_fecha, d_hora_inicio, d_hora_fin) VALUES (?,?,?,?,?)";
		} else if (motor instanceof MotorMySQL) {
			sql = "INSERT INTO `conducir`(`d_dni`, `d_mat`, `d_fecha`, `d_hora_inicio`, `d_hora_fin`) VALUES(?,?,?,?,?)";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, bean.getD_dni());
			pst.setString(2, bean.getD_mat());
			pst.setString(3, bean.getD_fecha());
			pst.setString(5, bean.getD_hora_inicio());
			pst.setString(4, bean.getD_hora_fin());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(String[] id) {
		String d_dni = id[0];
		String d_mat = id[1];

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "DELETE FROM SYS.CONDUCIR WHERE d_dni = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "DELETE FROM `conducir` WHERE `d_dni`= ? AND `d_mat`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, d_dni);
			pst.setString(2, d_mat);

			resp = this.motor.execute(pst);
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(Conducir bean) {

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "UPDATE SYS.CONDUCIR SET d_fecha = ?, d_hora_inicio = ?, d_hora_fin = ? WHERE d_dni = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "UPDATE `conducir` SET `d_fecha`= ?,`d_hora_inicio`= ?,`d_hora_fin`= ? WHERE `d_dni`= ? AND `d_mat`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, bean.getD_fecha());
			pst.setString(2, bean.getD_hora_inicio());
			pst.setString(3, bean.getD_hora_fin());
			pst.setString(4, bean.getD_dni());
			pst.setString(5, bean.getD_mat());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public ArrayList<Conducir> findAll() {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.CONDUCIR";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `conducir`";
		}
		ArrayList<Conducir> lstConducir = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstConducir = new ArrayList<>();

			while (rs.next()) {
				Conducir conducir = new Conducir();

				conducir.setD_dni(rs.getString(1));
				conducir.setD_mat(rs.getString(2));
				conducir.setD_fecha(rs.getString(3));
				conducir.setD_hora_inicio(rs.getString(4));
				conducir.setD_hora_fin(rs.getString(5));

				lstConducir.add(conducir);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return lstConducir;
	}

	@Override
	public Conducir findById(String[] id) {
		String d_dni = id[0];
		String d_mat = id[1];

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.CONDUCIR WHERE d_dni = ? AND d_mat = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `conducir` WHERE `d_dni`= ? AND `d_mat`= ?";
		}
		ArrayList<Conducir> lstConducir = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, d_dni);
			pst.setString(2, d_mat);

			ResultSet rs = this.motor.executeQuery(pst);
			lstConducir = new ArrayList<>();

			while (rs.next()) {
				Conducir conducir = new Conducir();

				conducir.setD_dni(rs.getString(1));
				conducir.setD_mat(rs.getString(2));
				conducir.setD_fecha(rs.getString(3));
				conducir.setD_hora_inicio(rs.getString(4));
				conducir.setD_hora_fin(rs.getString(5));

				lstConducir.add(conducir);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		Conducir conducir = null;
		if (!lstConducir.isEmpty()) {
			conducir = lstConducir.get(0);
		}

		return conducir;
	}

}
