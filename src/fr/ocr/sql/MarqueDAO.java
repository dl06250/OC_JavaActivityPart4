package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Marque;

public class MarqueDAO extends DAO<Marque> {

	public MarqueDAO(Connection conn) {
		super(conn);
	}

	@Override
	public Marque create(Marque obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO marque (NOM) VALUES (?)");
			prepare.setString(1, obj.getNom());
			prepare.executeUpdate();
			ResultSet id = prepare.getGeneratedKeys();
			obj=this.find(id.getInt("ID"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(Marque obj) {
		boolean isDeleted = false;
		try{
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM marque WHERE ID = ? ");
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			isDeleted=true;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public Marque update(Marque obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("UPDATE marque SET NOM = ? WHERE ID = ?");
			prepare.setString(1, obj.getNom());
			prepare.setInt(2, obj.getId());
			prepare.executeUpdate();
			obj=this.find(obj.getId());
		} catch (SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Marque find(int id) {
		Marque marque = new Marque();	
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM marque where ID = "+id);
			if (result.first()) {
				marque = new Marque(id, result.getString("NOM"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	
		return marque;
	}

}
