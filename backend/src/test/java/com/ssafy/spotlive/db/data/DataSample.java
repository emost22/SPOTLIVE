package com.ssafy.spotlive.db.data;

import com.github.javafaker.Faker;
import com.ssafy.spotlive.db.entity.*;
import com.ssafy.spotlive.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@SpringBootTest
public class DataSample {

    static Faker faker_ko = new Faker(new Locale("ko"));
    static Faker faker_eng = new Faker();
    static User user = new User();
    static Video video = new Video();
    static Category category = new Category();
    static ShowInfo showInfo = new ShowInfo();
    static Timetable timetable = new Timetable();
    static Reservation reservation = new Reservation();
    static Follow follow = new Follow();


    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    ShowInfoRepository showInfoRepository;
    @Autowired
    TimetableRepository timetableRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    FollowRepository followRepository;

//    @Test
//    void 스트리머_데이터_생성() {
//        int n = 10;
//        while( n--> 0) {
//            category.setCategoryId(faker_eng.options().option(1L, 2L, 3L, 4L, 5L));
//            setUser();
//            userRepository.save(user);
//            setShowInfo();
//            showInfoRepository.save(showInfo);
//            setTimeTalbeAndSave();
//            setVideo(user);
//            videoRepository.save(video);
//            userReservation();
//        }
//    }
//    @Test
//    void 시청자_데이터_생성() {
//        int n = 10;
//        while( n--> 0) {
//            setUser();
//            userRepository.save(user);
//            userReservation();
//        }
//    }
//    @Test
//    void 팔로우_데이터_생성() {
//        userFollowing();
//    }

    private void setUser(){
        user.setAccountEmail(faker_eng.internet().emailAddress());
        user.setUserName(faker_ko.name().fullName().replace(" ", ""));
        user.setPhoneNumber(faker_ko.phoneNumber().phoneNumber().replaceAll("-", ""));
        user.setProfileNickname(faker_ko.animal().name());
        user.setGender(faker_eng.options().option("female", "male"));
        user.setAgeRange(faker_eng.options().option("10~19","20~29","30~39","40~49"));
        user.setProfileDescription(faker_ko.company().name());
        user.setProfileImageUrl(faker_eng.options().option("https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99CEA7415A323B1A11",
                "https://cdn.ppomppu.co.kr/zboard/data3/2020/0721/20200721102637_nuobypxg.jpg",
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdtH5u8%2FbtqwCPUCBTM%2FlfWlkYMx4XaoZddlOTvtSK%2Fimg.png",
                "https://opgg-com-image.akamaized.net/attach/images/20190227180604.767248.png",
                "https://static-cdn.jtvnw.net/jtv_user_pictures/18c169cf-c0e7-4885-ae29-47f81ef2c4d4-profile_image-300x300.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQangJSkdWvxiZpyy4la8J9gW9kyuZwss4BZA&usqp=CAU",
                "https://im.vsco.co/aws-us-west-2/8c2e24/8353436/5c8694681f5b701085000000/vsco5c86946defab2.jpg?w=412&dpr=2.625",
                "https://t1.daumcdn.net/liveboard/benter/8b7466c3c55146bfac3c46781962367c.png",
                "https://t1.daumcdn.net/news/202005/08/newsen/20200508194808230exit.jpg",
                "https://i.pinimg.com/736x/2c/2c/60/2c2c60b20cb817a80afd381ae23dab05.jpg",
                "https://mblogthumb-phinf.pstatic.net/20150722_33/studio_amabile231_1437515041139XWDNp_PNG/4.PNG?type=w2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyYOQCy7vou_pquhMPx8PYsDQ3oWv_CI_K4w&usqp=CAU",
                "https://1.bp.blogspot.com/-L9txCMCEzzw/YIZaDIKJN2I/AAAAAAAAr4U/lrYCGM6tb3AayC7ZwV_Or6cuUCNmP7ZhgCLcBGAsYHQ/s0/ggoorr.net_001.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnN7PTHw0f2KouPNcmY8VYOHDqt52f1jdDtA&usqp=CAU",
                "https://t1.daumcdn.net/cfile/blog/99EF44335A17F02F38",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT59BZYpb3nMi7kHlEEQrAqUz9f0Q9DkglDkg&usqp=CAU",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5OVzFksygKDfBIJKMvs8tO7eMpyWwk3tA1w&usqp=CAU",
                "https://blog.kakaocdn.net/dn/n3Au0/btq8wkQZQQu/LJ3LtDExszwyeanBA75jK1/img.jpg"
        ));
    }
    private void setVideo(User user) {
        video.setVideoId(null);
        video.setUser(user);
        video.setHit(faker_eng.number().randomNumber(5, false));
        video.setCategory(category);
        String[] arr;
        arr = new String[]{"공연", "소통", "홍보"};
        String mode = faker_ko.options().option(arr);
        video.setMode(mode);
        video.setThumbnailUrl(getVideoThumbnailUrlByMode(video.getMode()));
        video.setVideoTitle(faker_ko.funnyName().name());
        video.setVideoDescription(faker_ko.company().buzzword());
        video.setEndTime(LocalDateTime.now().plusHours(2));
        video.setIsLive(Boolean.FALSE);
        video.setVideoUrl(faker_ko.options().option(
                "https://smrmembers-smr.smartmediarep.com/smc/smrmembers/single/eng/0/smr/contents/video/2021/08/03/d2076bb73af9077e25d9d4d1b22f59ee_t34.mp4",
                "https://smrmembers-smr.smartmediarep.com/smc/smrmembers/single/eng/0/smr/contents/video/2021/06/25/a093da3cd3b1f4008cf2162a7c45047d_t34.mp4",
                "https://smrmembers-smr.smartmediarep.com/smc/smrmembers/single/eng/0/smr/contents/video/2021/08/04/29d69c94309d605a56e72bf6e9d05086_t34.mp4",
                "https://smrmembers-smr.smartmediarep.com/smc/smrmembers/single/eng/0/smr/contents/video/2021/07/29/e9ff71514f1836bf1c862d5d1dfb21ff_t34.mp4",
                "https://smrmembers-smr.smartmediarep.com/smc/smrmembers/single/eng/0/smr/contents/video/2021/07/01/a0790396d92f0ab025bdc4f939866383_t34.mp4"
                ));
        if(!Objects.equals(mode, "소통")) video.setShowInfo(showInfo);
        else video.setShowInfo(null);
    }
    private String getVideoThumbnailUrlByMode(String mode){
        if(mode == "소통") {
            return faker_eng.options().option(
                    "http://img.newspim.com/news/2019/10/28/1910281051017890.jpg",
                    "https://i.ytimg.com/vi/YrDmbaFSPN8/maxresdefault.jpg",
                    "https://t1.daumcdn.net/cfile/tistory/99146E345B38AB9137",
                    "https://i.ytimg.com/vi/YN0vvVYm1Rw/maxresdefault.jpg",
                    "https://i.ytimg.com/vi/D0OmLtpfOm4/mqdefault.jpg",
                    "https://i.ytimg.com/vi/bgvYCOEShsY/maxresdefault.jpg",
                    "https://cdn.imweb.me/upload/S201909015ff825328ab63/ecd56d51befde.png",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa13Yfz7ARn4PQ8tD-QsWu4qlDPqaZHh8YZpBpH_dltU_XvJukv2CbSUVxJfhEOUHiX2c&usqp=CAU",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6yeJ5vsrjWbdM8S-H1hYy5ylGfPGxqYLf4kRgKGjp33jMdmCLc0q-g1k01xUNZesOlag&usqp=CAU",
                    "https://1.bp.blogspot.com/-9g3yckkJg9g/YEI9RlkdrsI/AAAAAAACOsk/Wr0qiNwzLyU7sP6jI5kzxFyniLI180C8ACLcBGAsYHQ/s715/20210305231456.jpg",
                    "https://cliimage.commutil.kr/phpwas/restmb_allidxmake.php?idx=3&simg=201812190925310606601286bacad121131233211.jpg",
                    "http://img.khan.co.kr/news/2020/10/12/l_2020101301001082300093861.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLPi1GNyjcMo5LOZHWnM6yTwWz_oHYcoXAzkzdJX4gXF0Vuxhn2Xm1TtZkUNi2hT8CWSE&usqp=CAU"
            );
        } else {
            /**
             * 1 버스킹
             * 2 뮤지컬(버스킹으로 들어가있음)
             * 3 오페라(버스킹으로 들어가있음)
             * 4 마술(버스킹으로 들어가있음)
             * 5 연극(버스킹으로 들어가있음)
             */
            Long category = video.getCategory().getCategoryId();
            if (category == 1L) {// 버스킹
                return faker_eng.options().option(
                        "https://lh3.googleusercontent.com/proxy/7CYVCs2qEPcX_Su-6RQEhddvZkT3bVKLk77n5v-MW_IEQN9OblllvzNthPULDcQplabuuQ9vMYDN5kjwP-ouheQlHwYgtuy2LS1msY_OlT4A2YYwMyTAqTAWqR3G",
                        "https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/guest/image/9-76nQMUy7bKP1WleZ7LhlqZdKE.JPG",
                        "https://lh3.googleusercontent.com/proxy/Vqp4KhLPM8VUKHsdjrkGH1lvrxrDG7V6qqfLMvw0kRI8wFhf30WuZzSQ1ZCqsgEktuPVbwXoNs9k5f0zTPR5RBKFnkco1ckk4xhKW02CB-1EVdGThZ8",
                        "http://cdnimage.dailian.co.kr/news/202002/news_1581993821_869287_m_1.jpeg",
                        "https://i.ytimg.com/vi/cGxtTqCwUDQ/maxresdefault.jpg",
                        "http://www.safetimes.co.kr/news/photo/201912/78812_54936_3412.jpg",
                        "https://cdn.jjan.kr/news/photo/201509/559478_177636_2717.jpg",
                        "https://lh3.googleusercontent.com/proxy/YitZ-vF3NtdSQDRoUT1tzW1pOmGIjg3EXm4FAS9-Kocp3r7bglvF0LwSOKuOmKjOSS_swSvlb839i5sx8RdkQi9BA28QbHSFiuHFDzgChCJxeExsj0TkiHcWVwTFpyBhbzVs12mFKk3uEbL0zEx27lh_zlB8ZO0lHp-neoeEXilR4fS0VbM",
                        "https://i.ytimg.com/vi/ccFBMTNG9hg/maxresdefault.jpg",
                        "http://talkimg.imbc.com/TVianUpload//tvian/image/2014/08/28/toNWUBYXgmHn635448334432870740.jpg",
                        "http://file2.nocutnews.co.kr/newsroom/image/2019/07/18/20190718141403827950_0_650_350.jpg"
                );
            }
            if (category == 2L) { // 뮤지컬
                return faker_eng.options().option(
                        "https://t1.daumcdn.net/liveboard/fanzeel/1175321b1ccd4964b10d0ffebb56d464.JPG",
                        "http://img.khan.co.kr/news/2019/04/19/2019041901002490500198421.jpg",
                        "https://file.mk.co.kr/meet/neds/2020/12/image_readtop_2020_1275512_16076750364468420.jpg",
                        "https://img.hankyung.com/photo/202005/AKR20200517023400005_01_i.jpg",
                        "https://i.ytimg.com/vi/VLP0-tfF-Nw/maxresdefault.jpg",
                        "http://image.newdaily.co.kr/site/data/img/2018/09/05/2018090500010_0.jpg",
                        "https://lh3.googleusercontent.com/proxy/9pVhqG39Dza0Ua3S2fJcraAAHQRLljtbq_783iikqYgZ1Rmb-uKZ-iSurEbyRbgIaITDgV4YH_1WX-YU-XgHix0iMaWHjmADtYyuBETccsf92Cro7B9H",
                        "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2VOP/image/yYop6juWwJeS3J4LjFknSRuuCpo",
                        "https://t1.daumcdn.net/liveboard/cineplay/f97e52678e7b4f78af698b3cd42dbce6.JPG"
                );
            }
            if (category == 3L) { // 오페라
                return faker_eng.options().option(
                        "https://t1.daumcdn.net/liveboard/fanzeel/1175321b1ccd4964b10d0ffebb56d464.JPG",
                        "http://img.khan.co.kr/news/2019/04/19/2019041901002490500198421.jpg",
                        "https://file.mk.co.kr/meet/neds/2020/12/image_readtop_2020_1275512_16076750364468420.jpg",
                        "https://img.hankyung.com/photo/202005/AKR20200517023400005_01_i.jpg",
                        "https://i.ytimg.com/vi/VLP0-tfF-Nw/maxresdefault.jpg",
                        "http://image.newdaily.co.kr/site/data/img/2018/09/05/2018090500010_0.jpg",
                        "https://lh3.googleusercontent.com/proxy/9pVhqG39Dza0Ua3S2fJcraAAHQRLljtbq_783iikqYgZ1Rmb-uKZ-iSurEbyRbgIaITDgV4YH_1WX-YU-XgHix0iMaWHjmADtYyuBETccsf92Cro7B9H",
                        "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2VOP/image/yYop6juWwJeS3J4LjFknSRuuCpo",
                        "https://t1.daumcdn.net/liveboard/cineplay/f97e52678e7b4f78af698b3cd42dbce6.JPG"
                );
            }
            if (category == 4L) { // 마술
                return faker_eng.options().option(
                        "https://static.wixstatic.com/media/0cfcf9_e358971a2f4d451a887d5b057d1357cb~mv2.jpg/v1/fill/w_554,h_370,al_c,q_80,usm_0.66_1.00_0.01/%5B%ED%81%AC%EA%B8%B0%EB%B3%80%ED%99%98%5D%ED%95%9C%EA%B8%B0%EC%9A%A9%EB%B9%84%EB%91%98%EA%B8%B0%EB%A7%88%EC%88%A0(1).webp",
                        "http://ojsfile.ohmynews.com/STD_IMG_FILE/2013/0714/IE001599505_STD.jpg",
                        "https://image.kkday.com/v2/image/get/w_960%2Cc_fit%2Cq_55%2Ct_webp/s1.kkday.com/product_33498/20191108075734_Jagij/jpg",
                        "https://pds.joins.com/news/component/htmlphoto_mmdata/201905/14/a22ccda5-0656-4b17-b923-b6147d637089.jpg",
                        "http://mnews.imaeil.com/inc/photos/2019/09/20/2019092016462909790_l.jpg",
                        "https://t1.daumcdn.net/cfile/tistory/9913CF335A1AC98727",
                        "https://www.yangsanilbo.com/news/photo/201904/68148_32939_2832.jpg"
                );
            }
            if (category == 5L) { // 뮤지컬
                return faker_eng.options().option(
                        "https://lh3.googleusercontent.com/proxy/7CYVCs2qEPcX_Su-6RQEhddvZkT3bVKLk77n5v-MW_IEQN9OblllvzNthPULDcQplabuuQ9vMYDN5kjwP-ouheQlHwYgtuy2LS1msY_OlT4A2YYwMyTAqTAWqR3G",
                        "https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/guest/image/9-76nQMUy7bKP1WleZ7LhlqZdKE.JPG",
                        "https://lh3.googleusercontent.com/proxy/Vqp4KhLPM8VUKHsdjrkGH1lvrxrDG7V6qqfLMvw0kRI8wFhf30WuZzSQ1ZCqsgEktuPVbwXoNs9k5f0zTPR5RBKFnkco1ckk4xhKW02CB-1EVdGThZ8",
                        "http://cdnimage.dailian.co.kr/news/202002/news_1581993821_869287_m_1.jpeg",
                        "https://i.ytimg.com/vi/cGxtTqCwUDQ/maxresdefault.jpg",
                        "http://www.safetimes.co.kr/news/photo/201912/78812_54936_3412.jpg",
                        "https://cdn.jjan.kr/news/photo/201509/559478_177636_2717.jpg",
                        "https://lh3.googleusercontent.com/proxy/YitZ-vF3NtdSQDRoUT1tzW1pOmGIjg3EXm4FAS9-Kocp3r7bglvF0LwSOKuOmKjOSS_swSvlb839i5sx8RdkQi9BA28QbHSFiuHFDzgChCJxeExsj0TkiHcWVwTFpyBhbzVs12mFKk3uEbL0zEx27lh_zlB8ZO0lHp-neoeEXilR4fS0VbM",
                        "https://i.ytimg.com/vi/ccFBMTNG9hg/maxresdefault.jpg",
                        "http://talkimg.imbc.com/TVianUpload//tvian/image/2014/08/28/toNWUBYXgmHn635448334432870740.jpg",
                        "http://file2.nocutnews.co.kr/newsroom/image/2019/07/18/20190718141403827950_0_650_350.jpg"
                );
            }
            return "";
        }
    }
    private void setTimeTalbeAndSave(){
        //3. 공연하나 만들때마다 타임테이블에 1~4개 시간 만들어서 저장
        timetable.setShowInfo(showInfo);
        int n = faker_eng.options().option(1, 2, 3, 4, 5, 6);
        for (int i = 1 ; i <= n ; i ++) {
            timetable.setTimetableId(null);
            LocalDateTime dateTime = LocalDateTime.now().plusHours(i* 2L);
            timetable.setDateTime(dateTime.minusHours(1L));
            timetableRepository.save(timetable);
        }
    }
    private void userFollowing(){
        // 본인을 제외한 모든유저를 팔로잉
        List<User> userList = userRepository.findAll();

        int size = userList.size();
        for (int i = 0; i < size; i++) {
            int num = (int) (Math.random() * 20);
            if (size <= num) continue;
            for (int j = 0; j < num; j++) {
                if (i == j) continue;

                User fan = userList.get(i);
                User artist = userList.get(j);
                Follow follow = new Follow();
                follow.setFan(fan);
                follow.setArtist(artist);

                followRepository.save(follow);
            }
        }
    }
    private void userReservation(){
        // 한 유저 당 0 ~ 4개의 공연을 예약한다.
        List<ShowInfo> showInfoList = showInfoRepository.findShowInfosByUser_AccountEmailNot(user.getAccountEmail()).orElse(null);

        if (showInfoList == null || showInfoList.isEmpty())
            return;

        int showInfoListSize = showInfoList.size();
        int num = (int) (Math.random()*4);
        boolean[] visit = new boolean[showInfoListSize];

        for (int i = 0; i < num; i++) {
            int x = (int) (Math.random()*showInfoListSize);
            if (visit[x]) continue;

            visit[x] = true;
            Long showInfoId = showInfoList.get(x).getShowInfoId();
            List<Timetable> timetableList = timetableRepository.findTimetablesByShowInfo_ShowInfoId(showInfoId).orElse(null);

            int reservationNum = (int) (Math.random()*timetableList.size());

            Reservation reservation = new Reservation();
            reservation.setTimetable(timetableList.get(reservationNum));
            reservation.setUser(user);
            reservationRepository.save(reservation);
        }
    }
    private void setShowInfo(){
        showInfo.setShowInfoId(null);
        showInfo.setUser(user);
        showInfo.setShowInfoTitle(faker_ko.country().name());
        showInfo.setShowInfoDescription(faker_ko.dog().name());
        showInfo.setPrice(faker_eng.number().randomNumber(7, false));
        showInfo.setRunningTime(faker_eng.number().numberBetween(30, 300));
        showInfo.setPosterUrl(faker_eng.options().option("https://i.pinimg.com/originals/a6/94/33/a6943328f2aadbe2ec9e9103087bb0cb.jpg",
                "https://images.chosun.com/resizer/VEaKqo3pUn9Go6lXB0dpBAylSUY=/464x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/ZYSWGA4P6VYIMYFWD7RZGLBBVM.jpg",
                "https://mblogthumb-phinf.pstatic.net/20160513_119/aproeasy_1463123866649MHzTF_JPEG/%B9%E3%C7%CF%B4%C32.jpg?type=w2",
                "https://cdn.gukjenews.com/news/photo/202011/2109322_2103661_3134.jpg",
                "https://cdn.thecheongna.net/news/photo/202105/4896_4492_1331.jpg",
                "https://t1.daumcdn.net/cfile/tistory/2635C33F5580BCFE17",
                "https://cdn.gukjenews.com/news/photo/201811/1022597_796348_114.jpg"
        ));
    }

}
