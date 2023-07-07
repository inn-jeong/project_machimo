package com.example.project_machimo.jomuragi;

import com.example.project_machimo.jomuragi.review.dao.AttachMapper;
import com.example.project_machimo.jomuragi.review.dao.ReviewDao;
import com.example.project_machimo.jomuragi.review.dto.AttachImageVO;
import com.example.project_machimo.jomuragi.review.service.AttachImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private AttachImageService imageService;


//    @Autowired
//    private AttachMapper attachMapper;

    //    로그인을 하든 안하든 어디서든 접근할 수 있어야하기때문에 공통 컨트롤러에 작성하는 게 좋음


    /* 이미지 정보 반환 */
    @GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AttachImageVO>> getAttachList(int reviewId){
//        AttachMapper attachMapper = sqlSession.getMapper(AttachMapper.class);
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        log.info("getAttachList.........." + reviewId);

        return new ResponseEntity<List<AttachImageVO>>(dao.getAttachList(reviewId), HttpStatus.OK);

    }


    @GetMapping("/display")
    public ResponseEntity<List<byte[]>> getImages(@RequestParam("reviewId") int reviewId) {
//    public ResponseEntity<List<byte[]>> getImages(@RequestParam("bno") int reviewId) {

        AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
        List<AttachImageVO> imageList = mapper.getAttachList(reviewId);

        List<byte[]> imageBytesList = new ArrayList<>();

        for (AttachImageVO image : imageList) {
            String imageUrl = image.getUrl();

            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("GET");

                // 이미지 가져오기
                InputStream inputStream = connection.getInputStream();
                byte[] imageBytes = IOUtils.toByteArray(inputStream);

                imageBytesList.add(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!imageBytesList.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageBytesList, headers, HttpStatus.OK);
        }

        // 이미지가 존재하지 않거나 오류가 발생한 경우 에러 응답
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    //    admin만이 이미지 파일 삭제 수행해야함. 나중에 언니거 받아서 admincontroller에 작성
    /* 이미지 파일 삭제 */
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(int reviewId,  String fileName){

        log.info("deleteFile........" + fileName);
        File file = null;
        try {
//            인코딩된 파일 이름을 다시 디코드해야 파일 이름 지정 가능
//            썸네일 파일 삭제(미리보기 파일이 썸네일 파일이므로)
//            file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
            file = new File(URLDecoder.decode(fileName, "UTF-8"));
            log.info("deleteFile........" + file);

//            File 클래스의 기본 메서드, 서버에 있는 파일 삭제

            file.delete();

            /* 원본 파일 삭제 */
//            String originFileName = file.getAbsolutePath().replace("s_", "");
            String originFileName = file.toString().replace("s_", "");

            log.info("originFileName : " + originFileName);

            file = new File(originFileName);

            file.delete();

            AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
            mapper.deleteFile(reviewId);
//          attachMapper.deleteFile(reviewId);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
