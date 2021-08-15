<template>
  <div class="modal fade" id="showInfoDialogNowPlaying" ref="showInfoDialogNowPlaying" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple ticket-detail-modal-design">
      <div class="modal-content-m">

        <div class="modal-header no-border">
          <div class="information-header mt-3 ms-3">
            {{this.loginUser.profileNickname}}의 티켓 정보
          </div>
          <button 
            type="button" 
            class="btn-close me-2 mt-1"
            data-bs-dismiss="modal" 
            aria-label="Close"
          />       
        </div>

        <div class="modal-body">
          <div class="my-ticket-box d-flex flex-row justify-content-evenly">   
            <div class="my-ticket-poster">
              <div class="d-flex ticket-poster-box">
                <img :src="showReservationData.posterUrl" class="ticket-poster-img bdcolor-bold-npurple"> 
              </div>
            </div>
            <div class="my-ticket-info">           
              <div class="ticket-show-info">  
                <div class="mb-2"> 
                  <div><label class="ticket-label-in-dialog">TEAM</label></div>
                  <div class="txtcolor-white-ngreen">{{ showReservationData.userRes.userName }}</div>
                </div>       
                <div class="mb-2">
                  <div><label class="ticket-label-in-dialog">공연명</label></div>
                  <div class="txtcolor-white-npink">{{ showReservationData.showInfoTitle }}</div>
                </div>
                <div class="mb-2">
                  <div><label class="ticket-label-in-dialog">티켓가격</label></div>
                  <div class="txtcolor-white-npurple">{{ showReservationData.price }}원</div>
                </div>
                <div class="d-flex flex-row mb-2">
                  <div class="me-3">
                    <label class="ticket-label-in-dialog">공연 시간</label>
                    <div class="txtcolor-white-nyellow"> {{}} </div>
                  </div>
                  <div>
                    <div><label class="ticket-label-in-dialog">러닝타임</label></div>
                    <div class="txtcolor-white-ngreen">{{showReservationData.runningTime}} min</div> 
                  </div>
                </div>
                <div>
                  <div><label class="ticket-label-in-dialog"> 공연 설명</label></div>
                  <div>{{ showReservationData.showInfoDescription }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="modal-footer-m my-3">
          <button type="button" class="bdcolor-ngreen small-button" data-bs-dismiss="modal">닫기</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script scoped>
import { mapGetters } from "vuex"
export default {
  name: 'ShowInfoDialogNowPlaying',
  data: function () {
    return {
      showInfoId: '',
      user: {},
      timetableId: '',
      timetables: [],
    }
  }, 
  methods: {
    formatter(date) {
      let dateTime = new Date(date)
      let month = parseInt(dateTime.getMonth()) + 1
      return `${dateTime.getFullYear()}년 ${month >= 10 ? month : '0' + month}월 ${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()}일 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    getShowInfoTimeTable() {
      this.$store.dispatch('requestGetShowTimetable', this.showInfoId)
        .then(res => {
          this.timetableId = res.data.timetables[0].timetableId
          res.data.timetables.forEach((timetable) => {
            var date = this.formatter(timetable.dateTime)
            this.timetables.push({ v: timetable.timetableId, t: date})
          })
        })
    }
  },
  computed: {
    ...mapGetters(['showReservationData', 'loginUser',]),
  },
  watch: {
    showReservationData: function(val, oldval) {
      var modal= this.$refs.showInfoDialogNowPlaying
      var _this = this
      console.log(val)
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
.ticket-detail-modal-design {
  max-height: 660px;
  min-width: 550px;
  width: 70%;
  background-color: #242424;
  color: white;
}
.my-ticket-box {
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
}
.my-ticket-poster {
  min-width: 205px;
  max-width: 205px;
  min-height: 450px;
  max-height: 450px;
  border: none;
  border-radius: .25rem;
  background-size: cover;
  background-image: url(~@/assets/ticket_without_content1_detail.png);
  background-color: #242424;
  background-repeat: no-repeat;
  padding-top: 240px;
  padding-left: 55px;
}
.my-ticket-info {
  min-width: 200px;
  max-width: 200px;
  min-height: 440px;
  max-height: 440px;
  border: none;
  border-radius: .25rem;
  background-size: cover;
  background-image: url(~@/assets/ticket_without_content2_detail.png);
  background-color: #242424;
  background-repeat: no-repeat;
  padding-top: 113px;
  padding-bottom: 18px;
  padding-left: 18px;
  padding-right: 18px;
}
.information-header {
  font-size: 20px;
  font-weight: bold;
}
.ticket-profile-img {
  width: 45px;
  height: 45px;
  border-radius: 100%;
  border: 2px #FFFFFF;
  box-shadow: 
    0 0 9px #FFFFFF,
    0 0 12px #FFFFFF,
    0 0 20px #FFFFFF,
}
.ticket-profile-detail{
  width: 500px;
  height: 50px;
  margin-left: 30px;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.ticket-poster-img {
  min-width: 100px;
  max-width: 100px;
  min-height: 130px;
  max-height: 130px;
}
.ticket-show-info {
  text-align: start;
}
.ticket-label-in-dialog {
  font-size: 15px;
  font-weight: bold;
}


/* .btn-close {
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
} */
</style>