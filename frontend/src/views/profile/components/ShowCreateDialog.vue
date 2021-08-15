<template>
  <div class="modal fade" id="showCreateModal" ref="showCreateModal"  tabindex="-1" aria-labelledby="showCreateModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple show-create-modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <div class="information-header mt-3 ms-3">공연 정보 생성</div>
          <button @click="clearShowCreateData" type="button" class="btn-close me-2 mt-1" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3 ms-3">
                <div><img :src="loginUser.profileImageUrl" class="profile-small-img"></div>
                <div class="profile-small-detail">
                  <div class="txtcolor-white-nyellow">{{ loginUser.profileNickname }}</div>
                  <div>{{ loginUser.accountEmail }}</div>
                </div>
              </div>

              <div class="d-flex flex-row">
                <div class="file-preview-container">
                  <div class="file-preview-wrapper">
                    <div class="show-img" v-if="preview">
                      <div class="show-img-box">
                        <div class="file-close-button" @click="fileDeleteButton">
                          x
                        </div>
                        <img :src="preview" class="show-preview">
                      </div>
                    </div>
                    <div class="camera-input-bgcolor-light-grey show-img" v-else>
                      <label class="camera-input-button" for="input-file"/>
                      <input type="file" id="input-file" class="show-poster-input" v-on:change="handleChange">
                    </div>
                  </div>
                </div>

                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput1" class="form-label">공연명</label></div>
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput1" v-model="showInfoTitle" autocomplete="off">
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment"><label for="showCreateFormControlInput2" class="form-label">티켓가격</label></div>
                      <input type="text" class="custom-form-control" id="showCreateFormControlInput2" v-model="price" autocomplete="off">
                    </div>
                    <div class="flex-fill">
                      <div class="label-alignment"><label for="showCreateFormControlInput3" class="form-label">러닝타임</label></div>
                      <input type="text" class="custom-form-control" id="showCreateFormControlInput4" v-model="runningTime" autocomplete="off">
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                      <div>
                        <div class="label-alignment"><label for="showCreateFormControlInput4" class="form-label">공연 시간</label></div>
                        <datetime class="datetime-theme" type="datetime" ref="datetimePicker" v-model="datetime" format="yyyy년 MM월 dd일 HH:mm"></datetime>
                      </div>
                      <div>
                        <button @click="doAdd" type="button" class="btn-add-timetable txtcolor-white-npurple">입력</button>
                      </div>
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                  <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                      <select class="show-create-timetable" v-model="selected">
                        <option value='' disabled>공연 시간 목록</option>
                        <option :key="i" :value="d.dateTime" v-for="(d, i) in timetables">
                          {{ formatter(d.dateTime) }}
                        </option>
                      </select>
                      <div>
                        <button @click="doRemove" type="button" class="btn-remove-timetable txtcolor-white-ngreen">삭제</button>
                      </div>
                  </div>
                  
                </div>
              </div>
              </div>

              <div class="mb-3 mx-3">
                <div class="label-alignment"><label for="showCreateFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                <textarea class="custom-form-control" id="showCreateFormControlTextarea1" rows="3" v-model="showInfoDescription"></textarea>
              </div>
            </form>
        </div>
        <div class="modal-footer-m my-3">
          <div><button type="button" class="bdcolor-ngreen small-button mx-3" data-bs-dismiss="modal">취소</button></div>
          <div><button @click="clickShowPostButton" type="button" class="bdcolor-npink small-button mx-3" data-bs-dismiss="modal">등록</button></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ShowCreateDialog',
  data: function() {
    return {
      userId: '',
      showInfoTitle: '',
      showInfoDescription: '',
      price: '',
      runningTime: '',
      posterImage: '',
      preview: '',
      datetime: '',
      timetables:[],
      selected: '',
      timtetableReq: [],
    }
  },
  created() {
    this.getUser()
    this.preview = ''
    this.clearShowCreateData()
  },

  mounted(){
    this.clearShowCreateData()
  },
  methods: {
    getMyProfile() {
      this.$store.dispatch('requestGetMyProfile')
      .then((response) => {
        console.log("getMyProfile() SUCCESS!!")
        console.log(response.data)
        var ProfileData = {
          myProfile : response.data,
          following : response.data.followMyArtistResList,
          follower : response.data.followMyFanResList,
          myShows : response.data.showInfoResList,
          myVideos : response.data.videoResList,
          myReservations : response.data.reservationResList,
        }
        this.$store.dispatch('requestSetCreatedProfileData', ProfileData)
      })
      .catch((error) => {
        console.log(error)
      })
    },
    handleChange(e) {
      var file = e.target.files[0]
      if (file && file.type.match(/^image\/(png|jpeg)$/)) {
        this.preview = window.URL.createObjectURL(file)
        this.posterImage = file
      }
    },
    fileDeleteButton(e) {
      this.preview = ''
    },
    openDatetime() {
      this.$refs.datetimePicker.open(event);
    },
    doAdd(){
      console.log(this.datetime)
      if (this.datetime != "") this.timetables.push({dateTime: this.datetime})
      this.selected = ''
      this.datetime = ''
    },
    doRemove(){
      let filtered = this.timetables.filter((element) => element.dateTime !== this.selected);
      this.timetables = filtered;
      this.selected = ''
    },
    getUser() {
      this.userId = this.loginUser.accountEmail
      this.profileNickname = this.loginUser.profileNickname
      this.profileImageUrl = this.loginUser.profileImageUrl
    },
    formatter(date) {
      var dateTime = new Date(date)
      var month = parseInt(dateTime.getMonth()) + 1
      return `${dateTime.getFullYear()}년 ${month >= 10 ? month : '0' + month}월 ${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()}일 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    clearShowCreateData() {
      var modal= this.$refs.showCreateModal
      var _this = this
      modal.addEventListener('hidden.bs.modal', function (event) {
        _this.timetables = []
        _this.showInfoTitle = ''
        _this.showInfoDescription = ''
        _this.price = ''
        _this.runningTime = ''
        _this.posterImage = ''
        _this.preview = ''
        _this.datetime = ''
        _this.timetables = []
        _this.selected = ''
        _this.timtetableReq = []
      })
    },
    clickShowPostButton(){
      let formData = new FormData()
      let showInfoInsertPostReq = {
        "showInfoTitle": this.showInfoTitle,
        "showInfoDescription": this.showInfoDescription,
        "price": this.price,
        "runningTime": this.runningTime,
        "timetableInsertPostReq": this.timetables
      }
      
      formData.append('posterImage', this.posterImage)
      formData.append('showInfoInsertPostReq', new Blob([JSON.stringify(showInfoInsertPostReq)], {type: "application/json"}))
      this.$store.dispatch('requestPostShow', formData)
      .then((response) => {
        this.clearShowCreateData()
        this.getMyProfile()
        this.$router.go(this.$router.currentroute)
        // this.$router.push({ name: 'Profile', query: { profileId : this.loginUser.accountEmail } })
      }).catch(error => {
        console.log(error)
        this.clearShowCreateData()
      })
    },
  },
  computed: {
    ...mapGetters(['loginUser',]),
  },
}
</script>

<style>

@import '../../../../node_modules/vue-datetime/dist/vue-datetime.css';
.show-create-modal-design {
  max-height: 700px;
  min-width: 650px;
  width: 70%;
  background-color: #242424;
  color: white;
}
.calendar-plus-button {
  width: 20px;
  height: 20px;
  margin-left: 10px;
  background-color: #595959;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-plus.png');
  background-repeat: no-repeat;
  background-position: center;
}
.camera-input-button{
  /* display: flex; */
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  background-color: #C4C4C4;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-camera-input.png');
  background-repeat: no-repeat;
  background-position: center;
  margin: auto;
  margin-top: 50%;
}
.show-img {
  /* min-width: 100px; */
  /* min-height: 200px; */
  margin-right: 20px;
  width: 100%;
  height: 100%;
}
.show-info {
  width: 300px;
}
.profile-small-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
  border: none;
  box-shadow: 
    0 0 9px #FFFFFF,
    0 0 12px #FFFFFF,
    0 0 20px #FFFFFF;
}
.profile-small-detail{
  width: 300px;
  height: 50px;
  margin-left: 30px;
  text-align: left;
}

.show-poster-input{
  display: none;
}
/* .preview-img{
  width: 100%;
  height: 100%;
} */
.file-preview-container {
  margin: 20px;
  min-width: 200px;
  max-width: 200px;
  min-height: 250px;
  max-height: 250px;
  display: flex;
  flex-wrap: wrap;
}
.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}
.file-preview-wrapper {
    padding: 10px;
    width: 100%;
    height: 100%;
    min-width: 100%;
    max-width: 100%;
    min-height: 100%;
    max-height: 100%;
    position: relative;
}
.vdatetime-input{
  display: block;
  background-color: #595959;
  border: 0px;
  color: white;
  border-radius: .25rem;
  padding: .375rem .75rem;
}
.custom-select-control-m {
  background-color: #595959;
  padding: .375rem;
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

.datetime-theme .vdatetime-popup__header,
.datetime-theme .vdatetime-calendar__month__day--selected > span > span,
.datetime-theme .vdatetime-calendar__month__day--selected:hover > span > span {
  background: #242424;
}

.vdatetime-popup__actions__button {
  color: white
}
.datetime-theme .vdatetime-year-picker__item--selected,
.datetime-theme .vdatetime-time-picker__item--selected,
.datetim-theme .vdatetime-popup__actions__button {
  color: white;
}

.datetime-theme .vdatetime-popup{
  background-color: #242424;
  color: white
}

.show-create-timetable {
  width: 220px;
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
.btn-add-timetable{
  display: block;
  width: 65px;
  height: 37px;
  font-size: 16px;
  background-color: #595959;
  border: 0px;
  border-radius: .25rem;
  margin-top: 32px;
  margin-left: 1rem;
  border-radius: .25rem;
  padding: .375rem .75rem;
  
}
.btn-remove-timetable{
  display: block;
  width: 65px;
  height: 37px;
  font-size: 16px;
  background-color: #595959;
  border: 0px;
  border-radius: .25rem;
  margin-left: 1rem;
  border-radius: .25rem;
  padding: .375rem .75rem;
}
.show-img-box{
  width: 100%;
  height: 100%;
  overflow:hidden;
}
.show-preview{
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  border-color: none;
}
.information-header {
  font-size: 20px;
  font-weight: bold;
}
</style>