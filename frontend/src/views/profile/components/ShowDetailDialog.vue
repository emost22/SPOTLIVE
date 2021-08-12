<template>
  <div>
  <div class="modal fade" id="showDetailModal" ref="showDetailModal" tabindex="-1" aria-labelledby="showDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple show-modal-design">
      
      <div class="modal-content-m">

        <div class="modal-header no-border">
          <div class="information-header mt-3 ms-3">공연 상세 정보</div>
          <button type="button" class="btn-close me-2 mt-1" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3">
                <div><img :src="loginUser.profileImageUrl" class="profile-small-img bdcolor-bold-npink"></div>
                <div class="profile-small-detail">
                  <div>{{ loginUser.profileNickname }}님</div>
                  <div>{{ loginUser.accountEmail }}</div>
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
                      <select class="custom-select-control-m show-timetablelist" aria-label="Default select showDetail" v-model="timetableId">
                        <option :key="i" :value="d.v" v-for="(d, i) in timetables">{{ d.t }}</option>
                      </select>
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
          <div><button class="bdcolor-ngreen small-button mx-3" data-bs-toggle="offcanvas" data-bs-target="#deleteShowInfo" aria-controls="deleteShowInfo">삭제</button></div>
          <div><button type="button" @click="updateShow()" class="bdcolor-npink small-button mx-3" data-bs-toggle="modal" data-bs-target="#showUpdateModal">수정</button></div>
        </div>

      </div>
    </div>
            <div class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" tabindex="-1" id="deleteShowInfo" ref="showPopup" aria-labelledby="offcanvasTopLabel">
                <div class="offcanvas-header">
                  <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                  <div class="mt-3">
                    <div>
                      <p class="popUpTitle">삭제 시 해당 데이터는 복구할 수 없습니다.</p> 
                      <p class="popUpTitle">정말로 삭제하시겠습니까?</p> 
                    </div>
                  </div>
                  <div class="d-flex justify-content-end show-popup">
                    <div><button type="button" class="bdcolor-ngreen small-button mx-3" data-bs-dismiss="offcanvas">취소</button></div>
                    <div><button type="button" @click="deleteShow()" class="bdcolor-npink small-button mx-3" data-bs-dismiss="offcanvas">확인</button></div>
                  </div>
                </div>
              </div>
  </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ShowDetailDialog',
  data: function() {
    return {
      timetableId: '',
      timetables: [],
    }
  },
  mounted() {
    this.getShowInfoTimeTable()
  },
  methods: {
    updateShow(){
      var showDetailModal = bootstrap.Modal.getInstance(this.$refs.showDetailModal)
      showDetailModal.hide()
    },
    deleteShow(){
      var showDetailModal = bootstrap.Modal.getInstance(this.$refs.showDetailModal)
      showDetailModal.hide()
      console.log(this.$store.getters.getShowData.showId)
      this.$store.dispatch('requestDeleteShowInfo', this.$store.getters.getShowData.showId)
    },
    formatter(date) {
      var dateTime = new Date(date)
      
      return `${dateTime.getMonth() >= 10 ? dateTime.getMonth() : '0' + dateTime.getMonth()}/${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()} 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    getShowInfoTimeTable(timetables) {
      this.timetableId = timetables[0].timetableId
      this.timetables = []
      for(var i = 0; i < timetables.length; i++){
        var date = this.formatter(timetables[i].dateTime)
        this.timetables.push({ v: timetables[i].timetableId, t: date})
      }
    }
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
  watch: {
    getShowData: function(val, oldval) {
      this.getShowInfoTimeTable(val.timetables)
      var modal= this.$refs.showDetailModal
      var _this = this
      modal.addEventListener('hidden.bs.modal', function (event) {
        console.log('제발')
        _this.timetables = []
      })
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
  min-width: 150px;
  max-width: 150px;
  min-height: 200px;
  max-height: 200px;
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
.show-popup {
  margin-top: 50px;
}
.popUpTitle {
  text-align: left;
  font-size: 15px;
  padding: 0 10%;
}
.show-timetable {
  width: 160px;
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
.label-in-dialog {
  font-size: 1.05rem;
  font-weight: bold;
}
</style>