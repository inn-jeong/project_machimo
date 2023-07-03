package com.example.project_machimo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {

    //    로그인을 하든 안하든 어디서든 접근할 수 있어야하기때문에 공통 컨트롤러에 작성하는 게 좋음
    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) {

        File file = new File("c:\\upload\\" + fileName);

        // 리턴용 객체와 파일 조회용 객체생성
        ResponseEntity<byte[]> result = null;

        try {

            // 화면에 무슨 타입으로 보여줄지 + 해당 타입으로 뭘 보여줄지 작성
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);


            // 오류발생했을땐 해당 메시지 + null 값 리턴
        } catch (Exception e) { e.printStackTrace();}
        return result;

    }

//    admin만이 이미지 파일 삭제 수행해야함. 나중에 언니거 받아서 admincontroller에 작성
    /* 이미지 파일 삭제 */
    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String fileName){

        log.info("deleteFile........" + fileName);
        File file = null;
        try {
//            인코딩된 파일 이름을 다시 디코드해야 파일 이름 지정 가능
//            썸네일 파일 삭제(미리보기 파일이 썸네일 파일이므로)
            file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
//            File 클래스의 기본 메서드, 서버에 있는 파일 삭제
            file.delete();

            /* 원본 파일 삭제 */
            String originFileName = file.getAbsolutePath().replace("s_", "");

            log.info("originFileName : " + originFileName);

            file = new File(originFileName);

            file.delete();

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
