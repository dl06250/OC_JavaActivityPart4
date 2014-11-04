package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

public class MoteurDAO extends DAO<Moteur> {

	public MoteurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public Moteur create(Moteur obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO moteur (CYLINDRE,MOTEUR,PRIX) VALUES (?,?,?)");
			prepare.setString(1, obj.getCylindre());		
			prepare.setInt(2, obj.getType().getId());
			prepare.setDouble(3, obj.getPrix());
			prepare.executeUpdate();
			ResultSet id = prepare.getGeneratedKeys();
			obj=this.find(id.getInt("ID"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(Moteur obj) {
		boolean isDeleted = false;
		try{
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM moteur WHERE ID = ? ");
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			isDeleted=true;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public Moteur update(Moteur obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("UPDATE moteur SET CYLINDRE = ?, MOTEUR = ?, PRIX = ? WHERE ID = ?");
			prepare.setString(1, obj.getCylindre());		
			prepare.setInt(2, obj.getType().getId());
			prepare.setDouble(3, obj.getPrix());
			prepare.executeUpdate();
			obj=this.find(obj.getId());
		} catch (SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Moteur find(int id) {
		Moteur moteur = new Moteur();	
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM moteur where ID = "+id);
			if (result.first()) {
				TypeMoteur type= new TypeMoteur();
				type.setId(result.getInt("MOTEUR"));
				moteur = new Moteur(id,type,result.getString("CYLINDRE"),result.getDouble("PRIX"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	
		return moteur;
	}

}
