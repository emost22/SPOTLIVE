<template>
  <div class="modal fade" id="showReservationDialog" ref="showReservationDialog" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="closeRoomSettingDialog()"></button>
        </div>
        <div class="modal-body mx-2">
          <div class="dialog-profile-info">
            <div><img src="~@/assets/icon-sidebar-back.png" class="profile-img"></div>
            <div class="profile-detail-show-reservation">
              <div> <span class="txtcolor-nyellow">나예뽀</span> 님</div>
              <p> jiin20803@naver.com</p>
            </div>
          </div>
          <div class="showInfoWrapper">
            <form>
              <div class="d-flex flex-row mb-3">
                <div class="d-flex flex-row justify-content-center align-items-center camera-input-bgcolor-light-grey camera-size">
                  <img :src="posterUrl">
                </div>
                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label class="form-label">공연명</label></div>
                    <div class="d-flex">
                    {{ showInfoTitle }}
                    </div>
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label class="form-label">티켓가격</label></div>
                    <div class="d-flex">
                    {{ price }}
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="me-3">
                      <div class="label-alignment">
                        <label class="form-label">공연 시간</label>
                      </div>
                      <select class="custom-select-control-m">
                        <option selected>7/29 13:00</option>
                      </select>
                    </div>
                    <div class="me-3">
                      <div class="label-alignment">
                        <label class="form-label">러닝타임</label>
                      </div>
                      <input type="text" class="custom-form-control" v-model="runningTime" readonly="readonly" disabled>
                    </div>
                  </div>
                </div>
              </div>
              <div class="mb-3 label-alignment">
                <div ><label class="form-label"> 공연 설명</label></div>
                <div>{{ showInfoDescription }}</div>
              </div>
            </form>
          </div>
        </div>
        <div class="modal-footer-m">
          <button type="button" class="bdcolor-npink small-button me-5" data-bs-dismiss="modal">닫기</button>
          <button type="button" class="bdcolor-ngreen small-button" data-bs-dismiss="modal" @click="clickToast()">예약하기</button>
        </div>
      </div>
    </div>
    <div class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" tabindex="-1" id="offcanvasTop" ref="showPopup" aria-labelledby="offcanvasTopLabel">
    <div class="offcanvas-header">
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
  </div>
  <div class="position-fixed top-0 end-0 p-3" style="z-index: 1100">
      <div id="liveToast" ref="myToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-animation="true" data-bs-delay="5000">
        <div class="toast-header">
          <strong class="me-auto">이미 예약된 상태입니다.</strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          이미 예약된 상태입니다.
        </div>
      </div>
    </div> 
  </div>
</template>

<script scoped>
import { mapGetters } from "vuex"
export default {
  name: 'ShowReservationDialog',
  data: function () {
    return {
      viewId: 0,
      categoryIds: [],
      videoData: {},
      runningTime: '30분',
      posterUrl: '',
      price: '3000원',
      showInfoDescription: '테스트입니다.테스트입니다.테스트입니다.테스트입니다.테스트입니다.테스트입니다.테스트입니다.',
      showInfoTitle: '테스트입니다.'
    }
  }, 
  methods: {
    clickToast() {
      var myToast = bootstrap.Toast.getOrCreateInstance(this.$refs.myToast)
      myToast.show()
    },
  },
  computed: {
    ...mapGetters([
    'settingDialogViewId',
    'isSettingDialogOpen',
    'loginUser',
    'createdVideoData',
    'videoId'
    ]),
  },

}
</script>

<style scoped>

.btn-close {
  background-image: url('~@/assets/icon-x.png');
  opacity: 1;
}
.btn-close:hover {
  background-image: url('~@/assets/icon-x.png');
}
.profile-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
.profile-detail-show-reservation{
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 30px;
  text-align: left;
}
.dialog-profile-info{
  display: flex;
  flex-direction: row;
  margin: 20px;
}
.camera-size {
  width: 280px;
  margin-right: 18px;
}
.custom-select-control-m {
  background-color: #595959;
  padding: .375rem 2.25rem .375rem .75rem;
  font-size: 1rem;
  font-weight: 400;
  color: white;
  background-repeat: no-repeat;
  background-position: right .75rem center;
  background-size: 16px 12px;
  border: 0px;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
</style>