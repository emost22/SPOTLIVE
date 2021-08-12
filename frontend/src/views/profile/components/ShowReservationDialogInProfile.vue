<template>
  <div>
    <div class="modal fade" id="showReservationInProfileModal" ref="showReservationInProfileModal" tabindex="-1" aria-labelledby="showReservationInProfileModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
        <!-- 예약 {{ getShowData.title }}
        타인이 내 프로필에서 들어오는 경우 인자
        {{ getShowData.userId}}
        {{ getShowData.profileImageUrl}}
        {{ getShowData.profileNickname}}
        {{ getShowData.showId}}
        {{ getShowData.timetables }} -->
        <!-- 나의 예약내역에서 들어오는 경우 인자 -->
        <!-- {{ getShowData.date }} -->
        <!-- {{ getShowData.time }} -->
        <div class="modal-content-m">
          <div class="modal-header no-border">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body mx-2">
            <div class="dialog-profile-info">
              <div><img :src="getShowData.profileImageUrl" class="profile-img"></div>
              <div class="profile-detail-show-reservation">
                <div> <span class="txtcolor-nyellow">{{ getShowData.profileNickname }}</span> 님</div>
                <p> {{ getShowData.userId }}</p>
              </div>
            </div>
            <div class="showInfoWrapper">
              <form>
                <div class="d-flex flex-row mb-3">
                  <div class="d-flex flex-row justify-content-center align-items-center camera-input-bgcolor-light-grey camera-size">
                    <img :src="getShowData.posterUrl" class="poster-image">
                  </div>
                  <div class="show-info">
                    <div class="mb-3">
                      <div class="label-alignment"><label class="form-label label-in-dialog">공연명</label></div>
                      <div class="d-flex">
                      {{ getShowData.title }}
                      </div>
                    </div>
                    <div class="mb-3">
                      <div class="label-alignment"><label class="form-label label-in-dialog">티켓가격</label></div>
                      <div class="d-flex">
                      {{ getShowData.price }}원
                      </div>
                    </div>
                    <div class="mb-3 d-flex">
                      <div class="me-3">
                        <div class="label-alignment">
                          <label class="form-label label-in-dialog">공연 시간</label>
                        </div>
                        <select class="custom-select-control" aria-label="Default select showDetail" v-model="timetableId">
                          <option :key="i" :value="d.v" v-for="(d, i) in timetables">{{ d.t }}</option>
                          <!-- 삭제 버튼  -->
                        </select>
                      </div>
                      <div>
                        <div class="label-alignment">
                          <label class="form-label label-in-dialog">러닝타임</label>
                        </div>
                        <!-- <div class="d-flex">
                          {{ getShowData.runningTime }}분
                        </div> -->
                        <input type="text" class="custom-form-control" v-model="getShowData.runningTime" readonly="readonly" disabled>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="mb-3 label-alignment">
                  <div ><label class="form-label label-in-dialog"> 공연 설명</label></div>
                  <div>{{ getShowData.description }}</div>
                </div>
              </form>
            </div>
          </div>
          <div class="modal-footer-m">
            <button type="button" class="bdcolor-npink small-button me-5" @click="clearTimeTableArray()" data-bs-dismiss="modal">닫기</button>
            <button type="button" class="bdcolor-ngreen small-button" @click="reservateShow()">예약하기</button>
          </div>
        </div>
      </div>
    <div class="position-fixed top-0 end-0 p-3" style="z-index: 1100">
      <div id="liveToast" ref="alreadyBooked" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-animation="true" data-bs-delay="3000">
        <div class="toast-header">
          <strong class="me-auto">이미 예약된 상태입니다!</strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          또 예약하고 싶으신가요~?😁
        </div>
      </div>
    </div> 
    <div class="position-fixed top-0 end-0 p-3" style="z-index: 1100">
      <div id="liveToast" ref="bookCompleted" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-animation="true" data-bs-delay="3000">
        <div class="toast-header">
          <strong class="me-auto">예약 완료 되었습니다!</strong>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
          멋진 공연 즐겨봐요❤️
        </div>
      </div>
    </div> 
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ShowReservationDialogInProfile',
  data: function() {
    return {
      showInfoDescription : '',
      title: '',
      description: '',
      posterUrl: '',
      price: '',
      runningTime: '',
      timetableId: '',
      timetables: [],
    }
  },
  created: function () {
  },
  methods: {
    formatter(date) {
      var dateTime = new Date(date)
      
      return `${dateTime.getMonth() >= 10 ? dateTime.getMonth() : '0' + dateTime.getMonth()}/${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()} 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    getShowInfoTimeTable(timetables) {
      this.timetableId = timetables[0].timetableId
      for(var i = 0; i < timetables.length; i++){
        var date = this.formatter(timetables[i].dateTime)
        this.timetables.push({ v: timetables[i].timetableId, t: date})
      }
    },
    clearTimeTableArray() {
      this.timetables = []
    },
    reservateShow() {
      console.log(this.timetableId," 이 쇼아이디로 예약하기 클릭했음")
      this.$store.dispatch('requestShowIsReservated', this.timetableId)
      .then(({ status} ) => {
        if(status == 200) {
          this.clickToast(1)
        } else if(status == 204) { // 예약안한 공연이므로 예약 axios 한번 더 호출
          this.clickToast(2)
          this.$store.dispatch('requestReservateShow', {timetableId : this.timetableId})
        } else {
          console.log("requestShowIsReservated Fail...")
        }
      })
      .catch((error) => {
        console.log(error)
      })
    },
    clickToast(viewId) {
      if (viewId == 1) {
        var myToast = bootstrap.Toast.getOrCreateInstance(this.$refs.alreadyBooked)
        myToast.show()
      } else {
        var myToast = bootstrap.Toast.getOrCreateInstance(this.$refs.bookCompleted)
        myToast.show()
      }
    },
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
  watch: {
    getShowData(val, oldVal) {
      this.getShowInfoTimeTable(this.getShowData.timetables)
      var modal= this.$refs.showReservationInProfileModal
      var _this = this
      modal.addEventListener('hidden.bs.modal', function (event) {
        console.log('제발')
        _this.timetables = []
      })
    },
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
.poster-image{
  width: 100%;
  height: 100%;
  background-size: cover;
}
.label-in-dialog {
  font-size: 1.05rem;
  font-weight: bold;
}
.showInfoWrapper {
  margin-left: 20px;
  margin-right: 20px;
}
</style>