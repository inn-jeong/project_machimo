package com.example.project_machimo;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dto.AuctionVO;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProjectMachimoApplicationTests {

	@Autowired
	private SqlSession sqlSession;

	@Test
	void contextLoads() {
	}


	@Test()
	void test1(){
		LocalDateTime now = LocalDateTime.now();
            AuctionDAO auctionDAO = sqlSession.getMapper(AuctionDAO.class);
            List<AuctionVO> auctionVOS = auctionDAO.endList(Timestamp.valueOf(now));

			for (int i = 0; i< auctionVOS.size(); i++){
				AuctionVO auctionVO = auctionVOS.get(i);
				System.out.println(auctionVO.productsId());

			}
	}

	@Test()
	void url인코딩테스트() throws UnsupportedEncodingException {
		String url="http%3A%2F%2Flocalhost%3A8090%2Fsearch%2F1";
		String decodedUrl = URLDecoder.decode(url,"UTF-8");
		System.out.println(decodedUrl.substring(decodedUrl.length()-1));
	}
}
