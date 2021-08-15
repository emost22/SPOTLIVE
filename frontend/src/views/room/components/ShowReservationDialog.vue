<template>
  <div class="modal fade" id="showReservationDialog" ref="showReservationDialog" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-2">
          <div class="dialog-profile-info">
            <div><img :src="showReservationData.userRes.profileImageUrl" class="profile-img"></div>
            <div class="profile-detail-show-reservation">
              <div> <span class="txtcolor-nyellow">{{ showReservationData.userRes.userName }}</span> 님</div>
              <p> {{ showReservationData.userRes.accountEmail }}</p>
            </div>
          </div>
          <div class="showInfoWrapper">
            <form>
              <div class="d-flex flex-row mb-3">
                <div class="d-flex flex-row justify-content-center align-items-center camera-input-bgcolor-light-grey camera-size">
                  <img :src="showReservationData.posterUrl" class="poster-image">
                </div>
                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label class="form-label label-in-dialog">공연명</label></div>
                    <div class="d-flex">
                    {{ showReservationData.showInfoTitle }}
                    </div>
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label class="form-label label-in-dialog">티켓가격</label></div>
                    <div class="d-flex">
                    {{ showReservationData.price }}원
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="me-3">
                      <div class="label-alignment">
                        <label class="form-label label-in-dialog">공연 시간</label>
                      </div>
                      <select class="custom-select-control-m" v-model="timetableId" v-if="timetables.length > 0">
                        <option :key="i" :value="d.v" v-for="(d, i) in timetables">{{ d.t }}</option>
                      </select>
                      <div class="d-flex" v-else>예약 가능한 시간이 없습니다.</div>
                    </div>
                    <div v-if="timetables.length > 0">
                      <div class="label-alignment">
                        <label class="form-label label-in-dialog">러닝타임</label>
                      </div>
                      <input type="text" class="custom-form-control" v-model="showReservationDataRunningTime" readonly="readonly" disabled>
                    </div>
                  </div>
                </div>
              </div>
              <div class="mb-3 label-alignment">
                <div ><label class="form-label label-in-dialog"> 공연 설명</label></div>
                <div>{{ showReservationData.showInfoDescription }}</div>
              </div>
            </form>
          </div>
        </div>
        <div class="modal-footer-m" v-if="timetables.length > 0">
          <button type="button" class="bdcolor-npink small-button me-5" data-bs-dismiss="modal">닫기</button>
          <button type="button" class="bdcolor-ngreen small-button" @click="reservateShow()">예약하기</button>
        </div>
        <div class="modal-footer-m" v-else>
          <button type="button" class="bdcolor-npink small-button" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
    <div class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" tabindex="-1" id="offcanvasTop" ref="showPopup" aria-labelledby="offcanvasTopLabel">
    <div class="offcanvas-header">
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
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
</template>

<script scoped>
import { mapGetters } from "vuex"
export default {
  name: 'ShowReservationDialog',
  data: function () {
    return {
      showInfoId: '',
      timetables: [],
      user: {},
      timetableId: '',
    }
  }, 
  methods: {
    reservateShow() {
      this.$store.dispatch('requestShowIsReservated', this.timetableId)
      .then(res => {
          console.log(res.status == 204)
          if (res.status == 200) {
          this.clickToast(1)
        } else if (res.status == 204){
          this.$store.dispatch('requestReservateShow', {timetableId: this.timetableId})
          .then(res => {
            this.clickToast(2)
          })
          .catch(err => {
            alert(err)
          })
        }
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
    formatter(date) {
      let dateTime = new Date(date)
      let month = parseInt(dateTime.getMonth()) + 1
      return `${dateTime.getFullYear()}년 ${month >= 10 ? month : '0' + month}월 ${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()}일 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    getShowInfoTimeTable() {
      var now = this.formatter(new Date())
      this.timetables = []
      this.$store.dispatch('requestGetShowTimetable', this.showInfoId)
        .then(res => {
          this.timetableId = res.data.timetables[0].timetableId
          res.data.timetables.forEach((timetable) => {
            var date = this.formatter(timetable.dateTime)
            if (date > now) {
              if (this.timetableId == '') this.timetableId = timetable.timetableId
              this.timetables.push({ v: timetable.timetableId, t: date})
            }
          })
        })
    }
  },
  computed: {
    ...mapGetters([
    'showReservationData'
    ]),
    showReservationDataRunningTime: function () {
      return this.showReservationData.runningTime+' min'
    }
  },
  watch: {
    showReservationData: function(val, oldval) {
      var modal= this.$refs.showReservationDialog
      var _this = this
      modal.addEventListener('show.bs.modal', function (event) {
        _this.showInfoId = val.showInfoId
        _this.user = val.userRes
        _this.getShowInfoTimeTable()
      })
      modal.addEventListener('hidden.bs.modal', function (event) {
      })
    }
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