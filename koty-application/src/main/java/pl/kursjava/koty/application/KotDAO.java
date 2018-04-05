package pl.kursjava.koty.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kursjava.koty.domain.Kot;

@Repository
public class KotDAO {
    private List<Kot> Koty = new ArrayList<Kot>();
    Connection conn = null;
    @Autowired
    DataSource dataSource;
    /*public void dodajKota(Kot kot)
    {
        Koty.add(kot);
    }*/
    public Kot dodajKota(Kot kot)
    {
    	String sql = "INSERT INTO koty.koty" + "(imie, imieOpiekuna, data, waga) VALUES (?, ?, ?, ?)";
    	conn = null;
    	try { 
    		conn = dataSource.getConnection(); 
    		PreparedStatement ps = conn.prepareStatement(sql); 
    		//ps.setInt(1, kotId);
    		ps.setString(1, kot.getImie());
    		ps.setString(2, kot.getImieOpiekuna());
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		ps.setString(3, sdf.format(kot.getDataUrodzenia()));
    		ps.setFloat(4, kot.getWaga());
    		ps.executeUpdate();
    		//rs.close(); 
    		ps.close(); 
    		return kot; 
    		} 
    catch (SQLException e) { 
    	throw new RuntimeException(e); 
} 	finally { 
    	if (conn != null) 
    		{ try { 
    			conn.close(); 
    				} catch (SQLException e) {} 
    		} 
    		}
    }
    /*public List<Kot> getKoty() {
        return Koty;
    }*/
    public List<Kot> getKoty() {
        {
        	String sql = "SELECT * FROM koty.koty";
        	Koty = new ArrayList<Kot>();
        	conn = null;
        	try { 
        		conn = dataSource.getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(sql);  
        		Kot kot = null; 
        		ResultSet rs = ps.executeQuery(); 
        		while (rs.next()) { 
        			kot = new Kot(); 
        			kot.setId(rs.getInt("kotId")); 
        			kot.setImie(rs.getString("imie"));
        			kot.setImieOpiekuna(rs.getString("imieOpiekuna"));
        			kot.setDataUrodzenia(rs.getDate("data"));
        			kot.setWaga(rs.getFloat("waga"));
        			Koty.add(kot);
        		} 
        		rs.close(); 
        		ps.close(); 
        		return Koty; 
        		} 
        catch (SQLException e) { 
        	throw new RuntimeException(e); 
    } 	finally { 
        	if (conn != null) 
        		{ try { 
        			conn.close(); 
        				} catch (SQLException e) {} 
        		} 
        		}
        }
    }
	/*public Kot getKotById(Integer id) {
		if (id<Koty.size()) {
			return Koty.get(id);
		} else {
			return null;
		}
	}*/
	
	public Kot getKotById(Integer id) {
    	String sql = "SELECT * FROM koty.koty WHERE kotId = ?";
    	Koty = new ArrayList<Kot>();
    	conn = null;
    	try { 
    		conn = dataSource.getConnection(); 
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		Kot kot = null; 
    		ResultSet rs = ps.executeQuery(); 
    		if (rs.next()) { 
    			kot = new Kot(); 
    			kot.setId(rs.getInt("kotId")); 
    			kot.setImie(rs.getString("imie"));
    			kot.setImieOpiekuna(rs.getString("imieOpiekuna"));
    			kot.setDataUrodzenia(rs.getDate("data"));
    			kot.setWaga(rs.getFloat("waga"));
    			Koty.add(kot);
    		} 
    		rs.close(); 
    		ps.close(); 
    		return kot; 
    		} 
    catch (SQLException e) { 
    	throw new RuntimeException(e); 
} 	finally { 
    	if (conn != null) 
    		{ try { 
    			conn.close(); 
    				} catch (SQLException e) {} 
    		} 
    		}
    }

}
