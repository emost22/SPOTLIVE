<template>
  <div>
    <div class="modal fade" id="ticketDetailModal" ref="ticketDetailModal" tabindex="-1" aria-labelledby="ticketDetailModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple show-modal-design">
        
        <div class="modal-content-m">
          
          <div class="modal-header no-border">
            <div class="profile-update-header mt-3 ms-3">예약 상세 정보</div>
            <button type="button" class="btn-close me-2 mt-1" data-bs-dismiss="modal" aria-label="Close"></button>
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
                        <input type="text" class="custom-form-control" v-model="dateTime" readonly="readonly" disabled>
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
          
          <div class="modal-footer-m my-3">
            <button 
              type="button" 
              class="bdcolor-npink small-button me-5" 
              @click="clearGetShowData()" 
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'TicketDetailDialog',
  data: function() {
    return {
      showInfoDescription : '',
      title: '',
      description: '',
      posterUrl: '',
      price: '',
      runningTime: '',
      timetableId: '',
      timetables: '',
      dateTime: '',
    }
  },
  created: function () {
  },
  methods: {
    formatter(date) {
      var dateTime = new Date(date)
      
      return `${dateTime.getMonth() >= 10 ? dateTime.getMonth() : '0' + dateTime.getMonth()}/${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()} ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    getShowInfoTimeTable(timetables) {
      this.dateTime = this.formatter(timetables[0].dateTime)
    },
    clearGetShowData() {
      // this.$store.dispatch('requestDeleteGetShowData')
    },
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
  watch: {
    getShowData(val, oldVal) {
      this.getShowInfoTimeTable(this.getShowData.timetables)
      var modal= this.$refs.showDetailModal
      // var _this = this
      // modal.addEventListener('hidden.bs.modal', function (event) {
      //   console.log('제발')
      //   _this.timetables = []
      // })
    }
  },
}
</script>

<style scoped>
.show-modal-design {
  max-height: 700px;
  min-width: 500px;
  width: 70%;
  background-color: #242424;
  color: white;
}
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