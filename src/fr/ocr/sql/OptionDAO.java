package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.option.Option;


public class OptionDAO extends DAO<Option> {

	public OptionDAO(Connection conn) {
		super(conn);
	}

	@Override
	public Option create(Option obj) {
		try{
			PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO option (DESCRIPTION, PRIX) VALUES (?,?)");
			prepare.setString(1, obj.getNom());
			prepare.setDouble(2, obj.getPrix());
			prepare.executeUpdate();
			ResultSet id = prepare.getGeneratedKeys();
			obj=this.find(id.getInt("ID"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean delete(Option obj) {
		boolean isDeleted = false;
		try{
			PreparedStatement prepare = this.connect.prepareStatement("DELETE FROM option WHERE ID = ? ");
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			isDeleted=true;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return isDeleted;
	}

	@Override
	public Option update(Option obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
