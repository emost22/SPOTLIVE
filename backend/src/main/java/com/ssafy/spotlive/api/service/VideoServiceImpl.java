package com.ssafy.spotlive.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.video.VideoFindAllByUserIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.db.entity.*;
import com.ssafy.spotlive.db.repository.UserVideoRepository;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @FileName : VideoService
 * @작성자 : 권영린
 * @Class 설명 : Video관련 기능을 위한 ServiceImpl 정의.
 */
@Service
@Transactional
public class VideoServiceImpl implements VideoService{

    @Value("${openvidu.server.url}")
    private String openviduServerUrl;

    @Value("${openvidu.server.id}")
    private String openviduServerId;

    @Value("${openvidu.server.secret}")
    private String openviduServerSecret;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserVideoRepository userVideoRepository;

    @Override
    public VideoInsertPostRes insertVideo(VideoInsertPostReq videoInsertPostReq, MultipartFile thumbnailImage){
        /**
         * @Method Name : insertVideo
         * @작성자 : 권영린, 김민권
         * @Method 설명 : 영상 시작시 1) Openvidu 세션을 생성하고 토큰을 발급 2) 썸네일을 비디오객체를 추가
         */
        // 1) Openvidu 세션을 생성하고 토큰을 발급
        String sessionId = makeSessionId();
        videoInsertPostReq.setSessionId(sessionId);
        String tokenForConnect = createToken(sessionId);

        // 2) 썸네일을 비디오객체에 추가
        String saveFileName = null;
        try {
            String separ = File.separator;
            String today = new SimpleDateFormat("yyMMdd").format(new Date());

            /* 절대경로를 가져오기 위한 file객체 생성 */
            File file = new File("");
            String rootPath = file.getAbsolutePath().split("backend")[0];
            /* 저장 경로 설정 후 없으면 생성 */

            String savePath = rootPath + "frontend" + separ + "src" + separ + "assets" + separ + "thumbnails";
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdirs();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            /* 파일 이름을 중복없이 만들어 생성. */
            String origFilename = thumbnailImage.getOriginalFilename();
            saveFileName = UUID.randomUUID().toString() + origFilename.substring(origFilename.lastIndexOf('.')); // 이상함.. 이거 했는데 왜 확장자 포함해서 파일이름이 저장될까
            /* 최종 이미지파일의 경로(Thumbnail) 생성 후 conferenceInsertPostReq에 셋팅*/
            String filePath = savePath + separ + saveFileName;
            thumbnailImage.transferTo(new File(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }
        VideoInsertPostRes videoInsertPostRes = VideoInsertPostRes.of(videoRepository.save(videoInsertPostReq.toVideo(saveFileName)));
        videoInsertPostRes.setToken(tokenForConnect);

        return videoInsertPostRes;
    }

    @Override
    public VideoFindByIdGetRes findVideoById(Long id) {
        /**
         * @Method Name : findVideoById
         * @작성자 : 권영린
         * @Method 설명 : 영상 id로 영상을 조회
         */
        return VideoFindByIdGetRes.of(videoRepository.findById(id).get());
    }

    @Override
    public Boolean updateVideoById(
            Long videoId,
            MultipartFile thumbnailImage,
            VideoUpdateByIdPatchReq videoUpdateByIdPatchReq,
            String accountEmail) {
        /* 원래 정보를 꺼내옴 */
        Optional<Video> videoById = videoRepository.findById(videoId);
        /* 정보가 없다면 FALSE */
        if(!videoById.isPresent()) return Boolean.FALSE;
        /* 수정하려는 사람과 현재 토큰 주인이 다르다면 FALSE */
        if(!videoById.get().getUser().getAccountEmail().equals(accountEmail)) return Boolean.FALSE;

        /* 썸네일이 있다면 원래 썸네일 파일을 현재 썸네일 파일로 바꿈 */
        if(thumbnailImage != null){
            String separ = File.separator;
            String originalThumbnailName = videoById.get().getThumbnailUrl();
            String rootPath = new File("").getAbsolutePath().split("backend")[0];
            String originalThumbnailUrl = rootPath + "frontend" + separ + "src" + separ + "assets" + separ + "thumbnails" + separ  + originalThumbnailName;
            File file = new File(originalThumbnailUrl);
            file.delete(); //원래 파일 삭제
            try {
                thumbnailImage.transferTo(new File(originalThumbnailUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Video video = videoById.get();
        /* 원래 정보에 바뀐 정보를 업데이트 */
        if(videoUpdateByIdPatchReq != null) {
            if (videoUpdateByIdPatchReq.getVideoTitle() != null) {
                video.setVideoTitle(videoUpdateByIdPatchReq.getVideoTitle());
            }
            if (videoUpdateByIdPatchReq.getVideoDescription() != null) {
                video.setVideoDescription(videoUpdateByIdPatchReq.getVideoDescription());
            }
            if (videoUpdateByIdPatchReq.getCategoryId() != null) {
                Category category = new Category();
                category.setCategoryId(videoUpdateByIdPatchReq.getCategoryId());
                video.setCategory(category);
            }
            if (videoUpdateByIdPatchReq.getShowInfoId() != null) {
                ShowInfo showInfo = new ShowInfo();
                showInfo.setShowInfoId(videoUpdateByIdPatchReq.getShowInfoId());
                video.setShowInfo(showInfo);
            }
        }
        videoRepository.save(video);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateVideoEndTimeById(Long videoId, String accountEmail){
        /**
         * @Method Name : updateVideoEndTimeById
         * @작성자 : 권영린, 김민권
         * @Method 설명 : 1) 해당 비디오의 Endtime을 기록하고 2) Openvidu 세션을 종료한다. 3) 이후 관련된 UserVideo를 전부 삭제한다.
         */
        Video video = videoRepository.findById(videoId).get();
        if(video.getEndTime()!=null) return Boolean.FALSE;
        if(!video.getUser().getAccountEmail().equals(accountEmail)) return Boolean.FALSE;
        int statusCode = closeSession(video.getSessionId());
        if(statusCode != 204) return Boolean.FALSE;

        video.setEndTime(LocalDateTime.now());
        videoRepository.save(video);

        // User Video 삭제
        List<UserVideo> deleteUserVideoList = userVideoRepository.findAllByVideo_VideoId(video.getVideoId());
        deleteUserVideoList.stream()
                .map(userVideo -> makeUserVideoId(userVideo))
                .forEach(userVideoId -> userVideoRepository.deleteById(userVideoId));

        return Boolean.TRUE;
    }

    @Override
    public List<VideoFindAllByUserIdGetRes> findVideoByAccountEmail(String accountEmail) {

        return videoRepository.findVideosByUserAccountEmail(accountEmail).stream()
                .map(video -> VideoFindAllByUserIdGetRes.of(video)).collect((Collectors.toList()));
    }

    @Override
    public String createSession() {
        /**
         * @Method Name : createSession
         * @작성자 : 김민권
         * @Method 설명 : 세션을 생성하고, 해당 세션 ID를 반환한다.
         */
        String targetUrl = openviduServerUrl + "/openvidu/api/sessions";
        String sessionId = makeSessionId();

        Map<String, String> httpBody = new HashMap<>();
        httpBody.put("customSessionId", sessionId);
        httpBody.put("recordingMode", "MANUAL");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json");
        httpHeaders.add("Authorization", "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU");

        HttpEntity<Map<String, String>> openviduSessionReq = new HttpEntity<>(httpBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap> result = restTemplate.exchange(targetUrl, HttpMethod.POST, openviduSessionReq, HashMap.class);

        return result.getBody().get("customSessionId").toString();
    }

    @Override
    public String createToken(String sessionId) {
        /**
         * @Method Name : createToken
         * @작성자 : 김민권
         * @Method 설명 : 전달받은 세션 ID에 대해서 Token을 생성한다.
         */
        String targetUrl = openviduServerUrl + "/openvidu/api/sessions/" + sessionId + "/connection";

        Map<String, String> httpBody = new HashMap<>();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json");
        httpHeaders.add("Authorization", "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU");

        HttpEntity<Map<String, String>> openviduTokenReq = new HttpEntity<>(httpBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap> result = restTemplate.exchange(targetUrl, HttpMethod.POST, openviduTokenReq, HashMap.class);

        return result.getBody().get("token").toString();
    }

    @Override
    public int closeSession(String sessionId) {
        /**
         * @Method Name : closeSession
         * @작성자 : 김민권
         * @Method 설명 : 전달받은 세션 ID인 세션을 종료한다.
         */
        String targetUrl = openviduServerUrl + "/openvidu/api/sessions/" + sessionId;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json");
        httpHeaders.add("Authorization", "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU");

        HttpEntity<Map<String, String>> openviduSessionReq = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(targetUrl, HttpMethod.DELETE, openviduSessionReq, HashMap.class);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT).getStatusCodeValue();
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().value() == 404) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND).getStatusCodeValue();
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).getStatusCodeValue();
            }
        }
    }

    public String makeSessionId() {
        String sessionId = "session";
        for(int i = 0; i < 8; i++) sessionId = sessionId + String.valueOf(new Random().nextInt(9) + 1);
        return sessionId;
    }

    private UserVideoId makeUserVideoId(UserVideo userVideo) {
        UserVideoId userVideoId = new UserVideoId();
        userVideoId.setUser(userVideo.getUser().getAccountEmail());
        userVideoId.setVideo(userVideo.getVideo().getVideoId());
        return userVideoId;
    }

}
