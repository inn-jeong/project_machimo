package com.example.project_machimo;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dto.AuctionDTO;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            List<AuctionDTO> auctionDTOS = auctionDAO.endList(Timestamp.valueOf(now));

			for (int i = 0 ; i<auctionDTOS.size(); i++){
				AuctionDTO auctionDTO = auctionDTOS.get(i);
				System.out.println(auctionDTO.productsId());

			}
	}
}
