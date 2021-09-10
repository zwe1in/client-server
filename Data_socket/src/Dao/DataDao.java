package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.SQLClient;


public class DataDao {
	public List<Map<String, Object>> queryAllData(int start, int end, int id){
		List<Map<String, Object>> datas = new ArrayList<>();
		Connection connection = SQLClient.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			pstm = connection.prepareStatement("select * from data where id =?");
			pstm.setInt(1, id);
			pstm.setInt(2, start);
			pstm.setInt(3, end);
			rs = pstm.executeQuery();
		while(rs.next()) {
			Map<String, Object> data = new HashMap<>();
			data.put("id", (rs.getInt("id")));
			data.put("temperature", (rs.getString("temperature")));
			data.put("weat", (rs.getString("weat")));
			data.put("EC", (rs.getString("EC")));
			data.put("time", (rs.getString("time")));
			datas.add(data);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			SQLClient.release(connection, pstm, rs);
		}
		return datas;
	}
	
	public int queryCount() {
		int count = 0;
		Connection connection = SQLClient.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			pstm = connection.prepareStatement("select count(*) from data");
			rs = pstm.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			SQLClient.release(connection, pstm, rs);
		}
		return count;
	}
}
