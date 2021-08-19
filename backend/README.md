# Backend Tech Doc

## File Structure


```bash
.
└── main
    ├── generated
    ├── java
    │   └── com
    │       └── ssafy.spotlive
    │           ├── SpotliveApplication.java
    │           ├── api  /* REST API 요청관련 컨트롤러, 서비스, 요청/응답 모델 정의*/
    │           │   ├── controller 
    │           │   │   ├── CategoryController.java /* Catogory 테이블에 대한 매핑 정의 */
    │           │   │   ├── FollowController.java /* Follow 테이블에 대한 매핑 정의 */
    │           │   │   ├── MainController.java  /* App의 메인화면 데이터에 대한 매핑 정의 */
    │           │   │   ├── ReservationController.java  /* 공연 예약 테이블에 대한 매핑 정의 */
    │           │   │   ├── ShowinfoController.java  /* 공연 정보 테이블에 대한 매핑 정의 */
    │           │   │   ├── UserController.java  /* 유저 정보와 OAuth 관련 매핑 정의 */
    │           │   │   ├── UserVideoController.java  /* Video에 참여한 User에 대한 매핑 정의 */
    │           │   │   └── VideoController.java  /* Stream되는 비디오에 대한 매핑 정의 */
    │           │   ├── request /* 각 테이블의 각 요청에 맞는 요청 객체 정의 */
    │           │   │   ├── showinfo
    │           │   │   │   ├── ShowInfoInsertPostReq.java
    │           │   │   │   └── ShowInfoUpdatePostReq.java
    │           │   │   ├── timetable
    │           │   │   │   └── TimetableInsertPostReq.java
    │           │   │   ├── user
    │           │   │   │   └── UserUpdatePatchReq.java
    │           │   │   ├── video
    │           │   │   │   ├── VideoInsertPostReq.java
    │           │   │   │   ├── VideoInsertUrlByIdPostReq.java
    │           │   │   │   └── VideoUpdateByIdPatchReq.java
    │           │   ├── response /* 각 테이블의 각 반환 형태에 맞는 응답 객체 정의 */
    │           │   │   ├── category
    │           │   │   │   ├── CategoryGetRes.java
    │           │   │   │   └── CategoryRes.java
    │           │   │   ├── follow
    │           │   │   │   ├── FollowFindByArtistAccountEmail.java
    │           │   │   │   ├── FollowFindByFanAccountEmail.java
    │           │   │   │   ├── FollowMyArtistRes.java
    │           │   │   │   └── FollowMyFanRes.java
    │           │   │   ├── main
    │           │   │   │   ├── UserFindFollowGetRes.java
    │           │   │   │   ├── VidoeFindAllGetRes.java
    │           │   │   │   ├── VideoFindMainVideoRes.java
    │           │   │   │   ├── VideoFindUserRes.java
    │           │   │   │   └── VideoGetRes.java
    │           │   │   ├── reservation
    │           │   │   │   └── ReservationRes.java
    │           │   │   ├── showinfo
    │           │   │   │   ├── ShowInfoFindByIdGetRes.java
    │           │   │   │   └── ShowInfoRes.java
    │           │   │   ├── timetable
    │           │   │   │   ├── TimetableFindByReservationRes.java
    │           │   │   │   └── TimetableRes.java
    │           │   │   ├── user
    │           │   │   │   ├── KakaoUserRes.java /* 카카오 oauth 반환을 받기위한 Res 객체 */
    │           │   │   │   └── UserRes.java
    │           │   │   ├── uservideo
    │           │   │   │   └── UserVideoRes.java
    │           │   │   ├── video
    │           │   │   │   ├── VideoFindAllByUserIdGetRes.java
    │           │   │   │   ├── VideoFindByIdGetRes.java
    │           │   │   │   ├── VideoInsertPostRes.java
    │           │   │   │   ├── VideoOpenviduSessionGetRes.java /* 특정 비디오의 openvidu 세션ID 반환 */
    │           │   │   │   └── VideoRes.java
    │           │   ├── service /* 비즈니스 로직 정의 */
    │           │   │   ├── AuthServiceImpl.java /* oAuth 및 권한 처리 관련 비즈니스 로직 정의
    │           │   │   ├── CategoryServiceImpl.java
    │           │   │   ├── FileUploadService.java /* 파일 업로드 관련 비즈니스 로직 정의 */
    │           │   │   ├── FollowServiceImpl.java
    │           │   │   ├── MainServiceImpl.java /* 메인화면에서 사용될 데이터에 대한 비즈니스 로직 정의 */
    │           │   │   ├── ReservationServiceImpl.java
    │           │   │   ├── ShowInfoServiceImpl.java
    │           │   │   ├── TimetableServiceImpl.java
    │           │   │   ├── UserServiceImpl.java
    │           │   │   ├── UserVideoServiceImpl.java
    │           │   │   └── VideoServiceImpl.java 
    │           ├── common.exception.handler
    │           │   └── NotFoundHandler.java /* SPA를 위한 index.html 매핑 핸들러 */
    │           ├── db
    │           │   ├── entity
    │           │   │   ├── Category.java
    │           │   │   ├── Follow.java
    │           │   │   ├── FollowId.java /* N:M 관계 테이블 위한 PK 객체 설정 */
    │           │   │   ├── Reservation.java 
    │           │   │   ├── ReservationId.java /* N:M 관계 테이블 위한 PK 객체 설정 */
    │           │   │   ├── ShowInfo.java 
    │           │   │   ├── Timetable.java 
    │           │   │   ├── User.java 
    │           │   │   ├── UserVideo.java 
    │           │   │   ├── UserVideoId.java /* N:M 관계 테이블 위한 PK 객체 설정 */
    │           │   │   └── Video.java 
    │           │   ├── repository
    │           │   │   ├── CategoryRepository.java
    │           │   │   ├── FollowRepository.java
    │           │   │   ├── ReservationRepository.java
    │           │   │   ├── ShowinfoRepository.java
    │           │   │   ├── TimetableRepository.java
    │           │   │   ├── UserRepository.java
    │           │   │   ├── UserVideoRepository.java
    │           │   │   └── VideoRepository.java
    ├── resouces
    │   ├── dist /* 하위에 SPA를 위한 Vue.js 빌드 */
    │   ├── keystore /* Https 설정을 위한 인증서 파일 */
    │   ├── application.properties /* App을 위한 환경변수 정의 */
    │   └── application.yaml /* App을 위한 환경변수 중 보안이 필요한 변수 정의 */
```