<template>
  <div v-show="isSettingDialogOpen" class="modal fade" id="roomSettingDialog" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="closeRoomSettingDialog()"></button>
        </div>
        <div class="modal-body">
          <div class='tabs'>
            <input type='radio' id='r1' name='t' checked>
            <label for='r1' class="tab-label">설정</label>
            <div class='content'>
              <RoomSettingDialogForm
                :categoryIds="categoryIds"
                :showInfoList="loginUser.showInfoResList"
                @form-data="form => videoData = form"
              />
            </div>
            <input type='radio' id='r2' name='t'>
            <label for='r2' class="tab-label">카메라</label>
            <div class='content'>
              <RoomSettingDialogCameraForm/>
            </div>
            <div id='slider'></div>
          </div>
        </div>
        <div class="modal-footer-m">
          <button type="button" class="bdcolor-ngreen small-button" @click="setCreatedVideoDataInVuex()" data-bs-dismiss="modal">확인</button>
        </div>
      </div>
    </div>
    <div class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
    <div class="offcanvas-header">
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
      <h5 class="popUpTitle">공연을 추가하기 위해 프로필로 이동해주세요</h5>
      등록된 공연이 없다면<br>
      <strong>프로필 > 공연 생성</strong> 버튼 클릭하여<br>
      상세 공연 정보를 등록 후 스트리밍을 진행할 수 있습니다.
      <div class="d-flex justify-content-center mt-4">
        <!-- <div><button type="button" class="bdcolor-ngreen small-button mx-3">취소</button></div> -->
        <div><button type="button" class="bdcolor-npink small-button mx-3">프로필로 가기</button></div>
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
  name: 'RoomSettingDialog',
  components: {
    RoomSettingDialogForm,
    RoomSettingDialogCameraForm
  },
  data: function () {
    return {
      viewId: 0,
      categoryIds: [],
      videoData: {},
    }
  }, 
  methods: {
    closeRoomSettingDialog: function () {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 0)
    },
    setViewId: function() {
      viewId = settingDialogViewId
    },
    setCreatedVideoDataInVuex: function () {
      this.$store.dispatch('requestSetCreatedVideoData', this.videoData)
    }
    
  },
  computed: {
    ...mapGetters([
    'settingDialogViewId',
    'isSettingDialogOpen',
    'loginUser'
    ]),
  },
  mounted() {
    this.$store.dispatch('requestGetCategoryIds')
    .then((response) => {
      this.categoryIds = response.data
    })
  },
  beforeUpdate() {
    
    if (this.settingDialogViewId == 1) {
      
    } else if (this.settingDialogViewId == 2) {

    }
  },
}
</script>

<style scoped>
.tabs {
  top: 51%;
  left: 50%;
  height: 50%;
  min-width: 400px;
}
.tab-label {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  color: #AAA;
  width: 200px;
  height: auto;
  padding: 20px 0px;
}

#slider {
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

.tabs [name=t] {
  display: none;   
}

.content {
  position: absolute;
  top: 50px; right: 0; bottom: 0; left: 0;
  padding-top: 50px;
  padding-right: 30px;
  padding-left: 30px;
  display: none;
}

#slider {
  background-color: transparent;
  position: absolute;
  border-bottom: 3px solid #C752FE;
  margin: 10px 0px;
  transition: transform 0.3s;
  width: 200px;
}

[name=t],#r1:checked ~ #slider {
  transform: translate(-400px, 0px);
}

[name=t],#r2:checked ~ #slider {
  transform: translate(-200px, 0px);
}

[name=t]:checked + label {
  color: white;
}

[name=t]:checked + label + .content {
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