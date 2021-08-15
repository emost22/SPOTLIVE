<template>
  <div class="modal fade" id="roomSettingUpdateDialog" ref="roomSettingUpdateDialog" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="closeRoomSettingDialog()"></button>
        </div>
        <div class="modal-body">
          <div class='tabs'>
            <input type='radio' id='rr1' name='u' checked>
            <label for='rr1' class="tab-label-u">설정</label>
            <div class='content-u'>
              <RoomSettingDialogForm
                :categoryIds="categoryIds"
                @form-data="form => videoData = form"
                :showInfoList="showInfoList"
                :showInUpdate="showInUpdate"
                :createdVideoData="createdVideoData"
                :closing="closing"
              />
            </div>
            <input type='radio' id='rr2' name='u'>
            <label for='rr2' class="tab-label-u">카메라</label>
            <div class='content-u'>
              <RoomSettingDialogCameraForm/>
            </div>
            <div id='slider-u'></div>
          </div>
        </div>
        <div class="modal-footer-m">
          <button type="button" class="bdcolor-npink small-button" @click="roomSettingUpdateDialogButton()">확인</button>
        </div>
      </div>
    </div>
    <div class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" tabindex="-1" id="offcanvasTop" ref="showPopup" aria-labelledby="offcanvasTopLabel">
    <div class="offcanvas-header">
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <h5 class="popUpTitle">공연을 추가하기 위해 프로필로 이동해주세요</h5>
      등록된 공연이 없다면<br>
      <strong>프로필 > 공연 생성</strong> 버튼 클릭하여<br>
      상세 공연 정보를 등록 후 스트리밍을 진행할 수 있습니다.
      <div class="d-flex justify-content-center mt-4">
        <div><button type="button" class="bdcolor-npink small-button mx-3" data-bs-dismiss="offcanvas" @click="routeToProfile()">프로필로 가기</button></div>
      </div>
    </div>
  </div>
  </div>
</template>

<script scoped>
import { mapGetters } from "vuex"
import RoomSettingDialogForm from './RoomSettingDialogForm.vue'
import RoomSettingDialogCameraForm from './RoomSettingDialogCameraForm.vue'
export default {
  name: 'RoomSettingUpdateDialog',
  components: {
    RoomSettingDialogForm,
    RoomSettingDialogCameraForm
  },
  data: function () {
    return {
      categoryIds: [],
      videoData: {},
      showInUpdate: false,
      closing: true,
    }
  }, 
  methods: {
    checkMode: function() {
      if (this.videoData.mode == '홍보' || this.videoData.mode == '소통') {
        delete this.videoData.showTime
        if (this.videoData.mode == '소통') {
          delete this.videoData.showInfoId
        }
      } 
    },
    makeFormDataForUpdateDialog() {
      
      console.log("=================makeFormDataForUpdateDialog RUN...===========================")
      console.log(this.videoData.thumbnailImage)

      let formData = new FormData()
      let videoUpdateByIdPatchReq = {
        "videoTitle": this.videoData.videoTitle,
        "videoDescription": this.videoData.videoDescription,
        "categoryId": this.videoData.categoryId,
      }
      formData.append('thumbnailImage', this.videoData.thumbnailImage)
      formData.append('videoUpdateByIdPatchReq', new Blob([JSON.stringify(videoUpdateByIdPatchReq)] , {type: "application/json"}))
      return formData
    },
    roomSettingUpdateDialogButton: function () {
      this.checkMode()
      let videoData = this.makeFormDataForUpdateDialog()
      console.log(videoData)

      let payload = {
        videoData: videoData,
        videoId: this.videoId
      }

      this.$store.dispatch('requestUpdateSettingDialog', payload)
      .then(res => {
        console.log(res)
        this.$store.dispatch('requestSetCreatedVideoData', this.videoData)
        var roomSettingUpdateDialog = bootstrap.Modal.getInstance(this.$refs.roomSettingUpdateDialog)
        roomSettingUpdateDialog.hide()
      })
      .catch(err => {
        alert('라이브 영상 수정을 하다 오류가 났어요! 다시 시도해주세요!')
      })
    },
    routeToProfile: function () {
      var roomSettingModal = bootstrap.Modal.getInstance(this.$refs.roomSettingDialog)
      roomSettingModal.hide()
      this.$router.push({name: 'Profile', query: { profileId : this.loginUser.accountEmail }})
      this.$router.go()
    }
  },
  computed: {
    ...mapGetters([
    'loginUser',
    'videoId',
    'createdVideoData'
    ]),
  },
  mounted() {
    // 오픈되었을 때 업데이트에서 켜졌다고 form에 알려줌 -> form에서 현재 켜져있는 라이브 데이터 가져와서 넣어줌, 
    // 클로즈 되었을 때 closing되었다고 form에 알려줌 -> form이 자기자신 초기화함
    var modal= this.$refs.roomSettingUpdateDialog
    var _this = this
    modal.addEventListener('show.bs.modal', function (event) {
      _this.showInUpdate = true
      _this.closing = false
      console.log('RoomsettingUpdateDialog show')
    })
    modal.addEventListener('hidden.bs.modal', function (event) {
      _this.closing = true
      console.log('RoomsettingUpdateDialog hidden')
    })
    this.$store.dispatch('requestGetCategoryIds')
    .then((response) => {
      this.categoryIds = response.data
    })
    this.$store.dispatch('requestGetShowInfoIds')
    .then(response => {
      console.log(response)
      this.showInfoList = response.data
    })
  },
  watch: {
    videoData(val, oldVal) {
      console.log("========= VideoData 변화 감지 =========")
      console.log("* OLD")
      console.log(oldVal)
      console.log("* NEW")
      console.log(val)
    },
  }
}
</script>

<style scoped>
.tabs-u {
  top: 51%;
  left: 50%;
  height: 50%;
  min-width: 400px;
}
.tab-label-u {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  color: #AAA;
  width: 200px;
  height: auto;
  padding: 20px 0px;
}

#slider-u {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  color: #AAA;
  width: 200px;
  height: auto;
  padding: 20px 0px;
}

label:hover {
  color: white;
  cursor: pointer;
}

.tabs [name=u] {
  display: none;   
}

.content-u {
  position: absolute;
  top: 50px; right: 0; bottom: 0; left: 0;
  padding-top: 50px;
  padding-right: 30px;
  padding-left: 30px;
  display: none;
}

#slider-u {
  background-color: transparent;
  position: absolute;
  border-bottom: 3px solid #C752FE;
  margin: 10px 0px;
  transition: transform 0.3s;
  width: 200px;
}

[name=u],#rr1:checked ~ #slider-u {
  transform: translate(-400px, 0px);
}

[name=u],#rr2:checked ~ #slider-u {
  transform: translate(-200px, 0px);
}

[name=u]:checked + label {
  color: white;
}

[name=u]:checked + label + .content-u {
  display: inline-block;
}
.btn-close {
  background-image: url('~@/assets/icon-x.png');
  opacity: 1;
}
.btn-close:hover {
  background-image: url('~@/assets/icon-x.png');
}
</style>