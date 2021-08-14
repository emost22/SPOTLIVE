<template>
  <div>
    <div 
      class="modal fade" 
      id="ticketDetailModal" 
      ref="ticketDetailModal" 
      tabindex="-1" 
      aria-labelledby="ticketDetailModalLabel" 
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple show-modal-design">
        <div class="modal-content-m">
          <div class="modal-header no-border">
            <div class="information-header mt-3 ms-3">
              예약 상세 정보
            </div>
            <button 
              type="button" 
              class="btn-close me-2 mt-1"
              data-bs-dismiss="modal" 
              aria-label="Close"
            />
          </div>
          <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3">
                <div>
                  <img :src="getShowData.profileImageUrl" class="profile-small-img bdcolor-bold-npink">
                </div>
                <div class="profile-small-detail">
                  <div>
                    {{ getShowData.profileNickname }}님
                  </div>
                  <p>
                    {{ getShowData.userId }}
                  </p>
                </div>
              </div>

              <div class="d-flex flex-row">                  
                <div><img :src="getShowData.posterUrl" class="show-img"></div>        
                <div class="show-info">                 
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showDetailFormControlInput1" class="form-label label-in-dialog">공연명</label></div>
                    <div>{{ getShowData.title }}</div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment"><label class="form-label label-in-dialog">티켓가격</label></div>
                      <div class="d-flex">{{ getShowData.price }}원</div>
                    </div>
                    <div class="flex-fill me-3">
                      <div class="label-alignment"><label class="form-label label-in-dialog">러닝타임</label></div>
                      <div class="d-flex">{{ getShowData.runningTime }}min</div>                      
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment"><label class="form-label label-in-dialog">공연 시간</label></div>
                      <div> {{dateTime}} </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="show-description mb-3">
                <div class="label-alignment"><label for="showDetailFormControlTextarea1" class="form-label label-in-dialog"> 공연 설명</label></div>
                <div>{{ getShowData.description }}</div>
              </div>
            </form>
          </div>
          
          <div class="modal-footer-m my-3">
            <button 
              type="button" 
              class="bdcolor-npink small-button" 
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
    }
  },
}
</script>

<style scoped>
.information-header {
  font-size: 20px;
  font-weight: bold;
}
.profile-small-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
.profile-small-detail{
  width: 100px;
  height: 50px;
  margin-left: 30px;
  text-align: left;
}
.show-modal-design {
  max-height: 700px;
  min-width: 500px;
  width: 70%;
  background-color: #242424;
  color: white;
}
.show-img {
  margin: 20px;
  min-width: 160px;
  max-width: 160px;
  min-height: 220px;
  max-height: 220px;
}
.show-info {
  margin: 20px;
  text-align: start;
}
.show-description {
  margin-left: 20px;
  margin-right: 20px;
  text-align: start;
}
.show-timetable {
  width: 160px;
}
.label-in-dialog {
  font-size: 1.05rem;
  font-weight: bold;
}
</style>