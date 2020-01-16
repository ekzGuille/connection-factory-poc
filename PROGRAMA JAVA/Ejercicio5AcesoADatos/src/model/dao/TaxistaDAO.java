package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Taxista;
import model.connectionFactory.ConnectionFactory;
import model.motor.Motor;
import model.motor.MotorMySQL;
import model.motor.MotorOracle;

public class TaxistaDAO implements DAO<Taxista, String> {

	private PreparedStatement pst;
	private Motor motor;

	public TaxistaDAO() {
		motor = ConnectionFactory.getInstance();
	}

	@Override
	public int add(Taxista bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "INSERT INTO SYS.TAXISTA (d_dni, d_nom, d_tfno, d_dir, d_edad) VALUES (?,?,?,?,?)";
		} else if (motor instanceof MotorMySQL) {
			sql = "INSERT INTO `taxista`(`d_dni`, `d_nom`, `d_tfno`, `d_dir`, `d_edad`) VALUES (?,?,?,?,?)";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, bean.getD_dni());
			pst.setString(2, bean.getD_nom());
			pst.setInt(3, bean.getD_tfno());
			pst.setString(4, bean.getD_dir());
			pst.setInt(5, bean.getD_edad());

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
			sql = "DELETE FROM SYS.TAXISTA WHERE d_dni = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "DELETE FROM `taxista` WHERE `d_dni`= ?";
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
	public int update(Taxista bean) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "UPDATE SYS.TAXISTA SET d_nom = ?, d_tfno = ?, d_dir = ?, d_edad = ? WHERE d_dni = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "UPDATE `taxista` SET `d_nom`= ?,`d_tfno`= ?,`d_dir`= ?,`d_edad`= ? WHERE `d_dni`= ?";
		}
		int resp = 0;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getD_nom());
			pst.setInt(2, bean.getD_tfno());
			pst.setString(3, bean.getD_dir());
			pst.setInt(4, bean.getD_edad());
			pst.setString(5, bean.getD_dni());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public ArrayList<Taxista> findAll() {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TAXISTA";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taxista`";
		}
		ArrayList<Taxista> lstTaxista = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstTaxista = new ArrayList<>();

			while (rs.next()) {
				Taxista taxista = new Taxista();

				taxista.setD_dni(rs.getString(1));
				taxista.setD_nom(rs.getString(2));
				taxista.setD_tfno(rs.getInt(3));
				taxista.setD_dir(rs.getString(4));
				taxista.setD_edad(rs.getInt(5));

				lstTaxista.add(taxista);
			}

		} catch (Exception e) {
		} finally {
			this.motor.disconnect();
		}
		return lstTaxista;
	}

	@Override
	public Taxista findById(String id) {
		String sql = "";
		if (motor instanceof MotorOracle) {
			sql = "SELECT * FROM SYS.TAXISTA WHERE d_dni = ?";
		} else if (motor instanceof MotorMySQL) {
			sql = "SELECT * FROM `taxista` WHERE `d_dni` = ?";
		}
		ArrayList<Taxista> lstTaxista = null;

		try {
			this.motor.connect().setAutoCommit(true);
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTaxista = new ArrayList<>();

			while (rs.next()) {
				Taxista taxista = new Taxista();

				taxista.setD_dni(rs.getString(1));
				taxista.setD_nom(rs.getString(2));
				taxista.setD_tfno(rs.getInt(3));
				taxista.setD_dir(rs.getString(4));
				taxista.setD_edad(rs.getInt(5));

				lstTaxista.add(taxista);
			}

		} catch (Exception e) {
		} finally {
			this.motor.disconnect();
		}
		
		Taxista taxista = null;
		if(!lstTaxista.isEmpty()) {
			taxista = lstTaxista.get(0);
		}
			
		return taxista;
	}

}
