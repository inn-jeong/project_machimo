package com.example.project_machimo;

import com.example.project_machimo.gyuha.auction.dao.AuctionDAO;
import com.example.project_machimo.gyuha.auction.vo.AuctionVO;
import com.example.project_machimo.gyuha.order.dao.OrderDAO;
import com.example.project_machimo.gyuha.order.dto.OrderDTO;
import com.example.project_machimo.gyuha.order.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProjectMachimoApplicationTests {

	@Autowired
	private SqlSession sqlSession;


	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDAO orderDAO;



	//옥션테스트
	
	@Test()
	void 옥션테스트(){
		LocalDateTime now = LocalDateTime.now();
            AuctionDAO auctionDAO = sqlSession.getMapper(AuctionDAO.class);
            List<AuctionVO> auctionVOS = auctionDAO.endList(Timestamp.valueOf(now));

			for (int i = 0; i< auctionVOS.size(); i++){
				AuctionVO auctionVO = auctionVOS.get(i);
				System.out.println(auctionVO.productsId());

			}
	}

	//	-최규하
	@Test()
	void url인코딩테스트() throws UnsupportedEncodingException {
		String url="http%3A%2F%2Flocalhost%3A8090%2Fsearch%2F1";
		String decodedUrl = URLDecoder.decode(url,"UTF-8");
		System.out.println(decodedUrl.substring(decodedUrl.length()-1));
	}



	    private final String IMAPORT_REST_API_KEY = "6181664231655157";
    private final String IMAPORT_REST_API_SECRET = "cHWubAwNZnM9ZwZmLTqo2prSdPMbBrdjAz057Ivcz1ffW9TeBeHXIgym88opNwExhPvTNfjlT7Kxs0t0";


//	-최규하 아임포트 테스트
	    @Test
    public void testCancelPaymentChecksumByImpUid() {
        String test_already_cancelled_imp_uid = "imp_448280090638";
        CancelData cancel_data = new CancelData(test_already_cancelled_imp_uid, true); //imp_uid를 통한 전액취소
        cancel_data.setChecksum(BigDecimal.valueOf(500)); // checksum 으로 검증 추가
	IamportClient client = new IamportClient(IMAPORT_REST_API_KEY,IMAPORT_REST_API_SECRET);
        try {
            IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

            assertNull(payment_response.getResponse());
        } catch (IamportResponseException e) {
            System.out.println(e.getMessage());

            switch (e.getHttpStatusCode()) {
                case 401:
                    //TODO
                    break;
                case 500:
                    //TODO
                    break;
            }
        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	
//	-최규하 결제실패시 DB에 정보 삭제 테스트
	@Test
	public void cancelDbTest(){
			List<Integer> list = new ArrayList<>();
		list.add(1);
		Date date = new Date();
		LocalDateTime localDateTime = LocalDateTime.now();
		OrderDTO orderDTO = new OrderDTO(1,3333,list,"안녕",Timestamp.valueOf(localDateTime),null,"나는 아무것도 몰라요","나는 아무것도  몰라요",2,2);
		orderService.cancelDb(orderDTO);
		
	}


}
