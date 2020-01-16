package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Taller;
import model.connectionFactory.ConnectionFactory;
import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class TallerDAO implements DAO<Taller, Integer> {

	private PreparedStatement pst;
	private Motor motor;

	public TallerDAO() {
		motor = ConnectionFactory.getInstance();
	}

	@Override
	public int add(Taller bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "INSERT INTO SYS.TALLER (d_cod, d_nom1, d_tfno, d_nom) VALUES (?,?,?,?)";
		} else if (motor instanceof MotorMySQL) {
			sql = "INSERT INTO `taller`(`d_cod`, `d_nom1`, `d_tfno`, `d_nom`) VALUES (?,?,?,?)";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, bean.getD_cod());
			pst.setString(2, bean.getD_nom1());
			pst.setInt(3, bean.getD_tfno());
			pst.setString(4, bean.getD_nom());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer id) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "DELETE FROM SYS.SYS.TALLER WHERE d_cod = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "DELETE FROM `taller` WHERE `d_cod`= ?";
		}
		int resp = 0;
		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, id);

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public int update(Taller bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "UPDATE SYS.TALLER SET d_nom1 = ?, d_tfno = ?, d_nom = ? WHERE d_cod = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "UPDATE `taller` SET `d_nom1`= ?,`d_tfno`= ?,`d_nom`= ? WHERE `d_cod`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getD_nom1());
			pst.setInt(2, bean.getD_tfno());
			pst.setString(3, bean.getD_nom());
			pst.setInt(4, bean.getD_cod());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public ArrayList<Taller> findAll() {

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TALLER";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taller`";
		}
		ArrayList<Taller> lstTaller = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);

			lstTaller = new ArrayList<>();

			while (rs.next()) {

				Taller taller = new Taller();

				taller.setD_cod(rs.getInt(1));
				taller.setD_nom1(rs.getString(2));
				taller.setD_tfno(rs.getInt(3));
				taller.setD_nom(rs.getString(4));

				lstTaller.add(taller);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return lstTaller;
	}

	@Override
	public Taller findById(Integer id) {

		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TALLER WHERE d_cod = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taller` WHERE `d_cod` = ?";
		}
		ArrayList<Taller> lstTaller = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);

			lstTaller = new ArrayList<>();

			while (rs.next()) {

				Taller taller = new Taller();

				taller.setD_cod(rs.getInt(1));
				taller.setD_nom1(rs.getString(2));
				taller.setD_tfno(rs.getInt(3));
				taller.setD_nom(rs.getString(4));

				lstTaller.add(taller);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		
		Taller taller = null;
		if(!lstTaller.isEmpty()) {
			taller = lstTaller.get(0);
		}
		return taller;
	}

}
