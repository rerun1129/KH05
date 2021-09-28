package com.kh.web.thumb.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.web.thumb.model.vo.Thumbnail;

import static com.kh.web.common.JDBCTemplate.*;

public class ThumbnailDAO {

	private Properties prop = null;

	public ThumbnailDAO() {
		prop = new Properties();

		String filePath = ThumbnailDAO.class
				.getResource("/config/thumb-sql.properties").getPath();

		try {
			prop.load( new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Thumbnail> selectList(Connection con) {
		ArrayList<Thumbnail> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectList");

		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				Thumbnail tn = new Thumbnail();

				tn.setBno( rs.getInt("bno"));
				tn.setBtitle( rs.getString("btitle"));
				tn.setBcontent( rs.getString("bcontent"));
				tn.setBwriter( rs.getString("bwriter"));
				tn.setBcount( rs.getInt("bcount"));
				tn.setBdate( rs.getDate("bdate"));
				tn.setBoardfile( rs.getString("changename"));

				list.add(tn);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return list;
	}

	public int insertThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement ps = null;

		String sql = prop.getProperty("insertThumbnail");

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, t.getBtitle());
			ps.setString(2, t.getBcontent());
			ps.setString(3, t.getBwriter());

			result = ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(ps);
		}

		return result;
	}

	public int getCurrentBno(Connection con) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String  sql = prop.getProperty("currentBno");

		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			if( rs.next() ) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return result;
	}














}
