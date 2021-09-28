package com.kh.web.thumb.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.web.thumb.model.dao.ThumbnailDAO;
import com.kh.web.thumb.model.vo.Attachment;
import com.kh.web.thumb.model.vo.Thumbnail;

import static com.kh.web.common.JDBCTemplate.*;

public class ThumbnailService {
	private Connection con;
	private ThumbnailDAO dao = new ThumbnailDAO();

	public ArrayList<Thumbnail> selectList() {
		con = getConnection();

		ArrayList<Thumbnail> list = dao.selectList(con);

		close(con);

		return list;
	}

	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();

		// 1. 사진 게시글 저장
		int result1 = dao.insertThumbnail(con, t);

		if (result1 > 0) {
			int bno = dao.getCurrentBno(con);

			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setBno(bno);  // 최근 게시글 번호 전달
			}
		}

		// 2. 첨부 파일 저장
		int result2 = 0;
		for(int i = 0 ; i < list.size(); i++) {
			// 첫번째 이미지는 대표 이미지! FLEVEL = 1
			// 나머지 이미지는 FLEVEL = 2
			list.get(i).setFlevel(i == 0 ? 1 : 2);

			/* if 방식
			if( i == 0) {
				list.get(i).setFlevel(1);
			} else {
				list.get(i).setFlevel(2);
			}
			*/

			result2 = dao.insertAttachment(con, list.get(i));
		}


		return 0;
	}













}
