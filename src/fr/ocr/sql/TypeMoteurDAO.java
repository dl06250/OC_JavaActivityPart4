package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import voiture.moteur.TypeMoteur;

public class TypeMoteurDAO extends DAO<TypeMoteur> {

	public TypeMoteurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public TypeMoteur create(TypeMoteur obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO type_moteur (DESCRIPTION) VALUES (?)");
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
	public boolean delete(TypeMoteur obj) {
		boolean isDeleted = false;
		try{
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM type_moteur WHERE ID = ? ");
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			isDeleted=true;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public TypeMoteur update(TypeMoteur obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("UPDATE type_moteur SET DESCRIPTION = ? WHERE ID = ?");
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
	public TypeMoteur find(int id) {
		TypeMoteur typeMoteur = new TypeMoteur();	
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM type_moteur where ID = "+id);
			if (result.first()) {
				typeMoteur = new TypeMoteur(id, result.getString("DESCRIPTION"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	
		return typeMoteur;
	}
}
