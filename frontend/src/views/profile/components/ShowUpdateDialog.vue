<template>
  <div class="modal fade" id="showUpdateModal" ref="showUpdateModal"  tabindex="-1" aria-labelledby="showUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple show-update-modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <div class="information-header mt-3 ms-3">공연 정보 수정</div>
          <button type="button" class="btn-close me-2 mt-1" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
          <div class="modal-body mx-3">
            <ValidationObserver ref="showUpdateObserver" @change="changeInput()">
              <form>
                <div class="d-flex flex-row mb-3 ms-3">
                  <div><img :src="loginUser.profileImageUrl" class="profile-small-img"></div>
                  <div class="profile-small-detail">
                    <div class="txtcolor-white-nyellow">{{ loginUser.profileNickname }}</div>
                    <div class="main-font-light">{{ loginUser.accountEmail }}</div>
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
                      <div class="camera-input-bgcolor-light-grey show-update-img-box" v-else>
                        <ValidationProvider rules="required|image|size:1000" ref="showCreateFileBrowser">
                          <label class="camera-input-button show-update-img" for="input-file-update"/>
                          <input type="file" id="input-file-update" class="show-poster-input" v-on:change="handleFileChange">
                        </ValidationProvider>
                      </div>
                      {{ fileErrorMessage }}
                    </div>
                  </div>

                  <div class="show-info">
                    <div class="mb-3">
                      <ValidationProvider rules="required|max:20" v-slot="v">
                        <div class="label-alignment"><label for="showUpdateFormControlInput1" class="form-label">공연명</label></div>
                        <input type="text" class="custom-form-control main-font-light" id="showUpdateFormControlInput1" v-model="showInfoTitle" autocomplete="off">
                        <span>{{ v.errors[0] }}</span>
                      </ValidationProvider>
                    </div>
                    <div class="mb-3 d-flex">
                      <div class="flex-fill me-3">
                      <ValidationProvider rules="required|max:10|numeric" v-slot="v">
                        <div class="label-alignment"><label for="showUpdateFormControlInput2" class="form-label">티켓가격</label></div>
                        <input type="text" class="custom-form-control main-font-light" id="showUpdateFormControlInput2" v-model="price" autocomplete="off">
                        <span>{{ v.errors[0] }}</span>
                      </ValidationProvider>
                      </div>
                      <div class="flex-fill">
                      <ValidationProvider rules="required|max:10|numeric" v-slot="v">
                        <div class="label-alignment"><label for="showUpdateFormControlInput3" class="form-label">러닝타임</label></div>
                        <input type="text" class="custom-form-control main-font-light" id="showUpdateFormControlInput4" v-model="runningTime" autocomplete="off">
                        <span>{{ v.errors[0] }}</span>
                      </ValidationProvider>
                      </div>
                    </div>
                    <div class="mb-3 d-flex">
                      <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                        <div>
                          <div class="label-alignment"><label for="showUpdateFormControlInput4" class="form-label">공연 시간</label></div>
                          <datetime class="datetime-theme main-font-light" type="datetime" ref="datetimePicker" v-model="datetime" format="yyyy년 MM월 dd일 HH:mm"></datetime>
                        </div>
                        <div>
                          <button @click="doAdd" type="button" class="btn-add-timetable txtcolor-white-npurple">입력</button>
                        </div>
                      </div>
                    </div>
                    <div class="mb-3 d-flex">
                    <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                      <ValidationProvider rules="excluded:0" v-slot="v">
                        <select class="show-update-timetable main-font-light" v-model="selected">
                          <option :value="defaultValue">공연 시간 목록</option>
                          <option :key="i" :value="d.dateTime" v-for="(d, i) in timetables">
                            {{ formatter(d.dateTime) }}
                          </option>
                        </select>
                        <span>{{ v.errors[0] }}</span>
                      </ValidationProvider>
                        <div>
                          <button @click="doRemove" type="button" class="btn-remove-timetable txtcolor-white-ngreen">삭제</button>
                        </div>
                    </div>
                    
                  </div>
                </div>
                </div>

                <div class="mb-3 mx-3">
                  <ValidationProvider rules="required|max:200" v-slot="v">
                    <div class="label-alignment"><label for="showUpdateFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                    <textarea class="custom-form-control main-font-light" id="showUpdateFormControlTextarea1" rows="3" v-model="showInfoDescription"></textarea>
                    <span>{{ v.errors[0] }}</span>
                  </ValidationProvider>
                </div>
              </form>
            </ValidationObserver>
          </div>
          <div class="modal-footer-m my-3">
            <div><button type="button" class="bdcolor-ngreen small-button mx-3" data-bs-dismiss="modal">취소</button></div>
            <div><button @click="clickShowUpdateButton" type="button" class="bdcolor-npink small-button mx-3 setting-button" data-bs-dismiss="modal" :disabled="invalid">저장</button></div>
          </div>
      </div>
    </div>
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" ref="toast" data-bs-delay="700">
      <div class="toast-body" style="text-align: center">
        {{toastMessage}}
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import { ValidationObserver, ValidationProvider } from 'vee-validate';

export default {
  name: 'ShowUpdateDialog',
  components: {
    ValidationObserver,
    ValidationProvider
  },
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
      selected: '0',
      toastMessage: '',
      fileErrorMessage: '',
      invalid: true,
      defaultValue: '',
      duplicate: false,
    }
  },
  created: function () {
    this.getUser()
 },
  mounted(){
  },
  methods: {
    async handleFileChange(e) {
      const { valid } = await this.$refs.showCreateFileBrowser.validate(e);
      if (valid) {
        var file = e.target.files[0]
        this.preview = window.URL.createObjectURL(file)
        this.posterImage = file
        this.fileErrorMessage = ''    
        console.log('Uploaded the file...');
      } else {
        this.posterImage = ''
        this.preview = ''
        this.fileErrorMessage = this.$refs.showCreateFileBrowser.errors[0]        
      }
    },
    changeInput: function () {
      if (this.$refs.showUpdateObserver.flags.invalid) {
        this.invalid = true
      } else {
        this.invalid = false
      }
    },
    async init(showData){
      this.showInfoTitle = showData.title
      this.showInfoDescription = showData.description
      this.price = showData.price
      this.runningTime = showData.runningTime
      this.preview = showData.posterUrl
      this.timetables = showData.timetables
      if (this.timetables) this.selected = this.timetables[0].dateTime
    },
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
    toastEvent(){
      var toast = new bootstrap.Toast(this.$refs.toast)
      toast.show()
    },
    fileDeleteButton(e) {
      this.preview = ''
    },
    openDatetime() {
      this.$refs.datetimePicker.open(event);
    },
    checkDuplicateDatetime(){
      for(var key in this.timetables){
          if(this.timetables[key].dateTime==this.modifyDatetime()){
            this.duplicate = true
            break
          }
      }
    },
    modifyDatetime(){
      let newVal = new Date(this.datetime)
      newVal.setHours(newVal.getHours() + 9)
      newVal = newVal.toISOString().substring(0, 19)
      return newVal
    },
    doAdd(){
      if (this.datetime != ""){
        this.checkDuplicateDatetime()
        if(!this.duplicate){
          this.timetables.push({dateTime: this.modifyDatetime()})
          this.toastMessage = "공연 시간이 등록되었습니다."
          this.selected = this.modifyDatetime()
          this.datetime = ''
          this.defaultValue = ''
        }else{
          this.toastMessage = "이미 등록한 시간입니다!"
        }    
      }else{
        this.toastMessage = "공연 시간을 입력해주세요!"
      }
      this.toastEvent()
      this.duplicate = false
    },
    doRemove(){
      if(this.selected != ""){
        let filtered = this.timetables.filter((element) => element.dateTime !== this.selected);
        this.timetables = filtered;
        this.toastMessage = "공연 시간이 삭제되었습니다."
        if (this.timetables.length > 0) {
          this.selected = this.timetables[0].dateTime
        } else {
          this.selected = '0'
          this.defaultValue = '0'
        }
      }else{
        this.toastMessage = "삭제할 공연 시간을 선택해주세요!"
      }
      this.toastEvent()
    },
    getUser() {
      this.userId = this.loginUser.accountEmail
      this.profileNickname = this.loginUser.profileNickname
      this.profileImageUrl = this.loginUser.profileImageUrl
    },
    formatter(date) {
      let dateTime = new Date(date)
      let month = parseInt(dateTime.getMonth()) + 1
      return `${dateTime.getFullYear()}년 ${month >= 10 ? month : '0' + month}월 ${dateTime.getDate() >= 10 ? dateTime.getDate() : '0' + dateTime.getDate()}일 
        ${dateTime.getHours() >= 10 ? dateTime.getHours() : '0' + dateTime.getHours()}:${dateTime.getMinutes() >= 10 ? dateTime.getMinutes() : '0' + dateTime.getMinutes()}`
    },
    clickShowUpdateButton(){
      this.$store.dispatch('requestShowLoadingSpinner', true)
      let formData = new FormData()
      let showInfoUpdatePatchReq = {
        "showInfoTitle": this.showInfoTitle,
        "showInfoDescription": this.showInfoDescription,
        "price": this.price,
        "runningTime": this.runningTime,
        "timetableInsertPostReq": this.timetables
      }
      
      if(this.posterImage != null)
        formData.append('posterImage', this.posterImage)
      else
        formData.append('posterImage', null)

      formData.append('showInfoUpdatePatchReq', new Blob([JSON.stringify(showInfoUpdatePatchReq)], {type: "application/json"}))

      let data = {
        formData: formData,
        showInfoId: this.$store.getters.getShowData.showId
      }
      console.log(this.posterImage)
      this.$store.dispatch('requestPutShow', data)
      .then((res) => {
        // this.getMyProfile()
        var showData = {
          showId: this.getShowData.showId,
          title: this.showInfoTitle,
          description: this.showInfoDescription,
          posterUrl: this.preview,
          price: this.price,
          runningTime: this.runningTime,
          timetables: this.timetables ,
        }
        this.$store.dispatch('requestGetShowData', showData)
        this.getMyProfile()
        this.$store.dispatch('requestShowLoadingSpinner', false)
      })
      .catch((err) => {
        console.log('fail')
        this.$store.dispatch('requestShowLoadingSpinner', false)
      })
    },
  },
  computed: {
    ...mapGetters(['loginUser','getShowData']),
  },
  watch: {
    getShowData: function(val, oldval) {
      this.init(val)
    }
  },
}
</script>

<style>

@import '../../../../node_modules/vue-datetime/dist/vue-datetime.css';
.show-update-modal-design {
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
  display: flex;
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
}
.show-update-img-box {
  min-width: 180px;
  max-width: 180px;
  min-height: 230px;
  max-height: 230px;
}
.show-update-img {
  width: 100%;
  height: 100%;
  min-width: 180px;
  max-width: 180px;
  min-height: 230px;
  max-height: 230px;
  margin-right: 20px;
  cursor: pointer;
}
.show-img {
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
  width: 100px;
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
  width: 220px;
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

.show-update-timetable {
  width: 220px;
  background-color: #595959;
  padding: .375rem 0.8rem .375rem .75rem;
  font-size: 1rem;
  font-weight: 400;
  color: white;
  background-repeat: no-repeat;
  background-position: right .75rem center;
  background-size: 16px 12px;
  border: 0px;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
  height: 36px;
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
  cursor: pointer;
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
  cursor: pointer;
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
.toast {
    top: 9%; 
    right: 0;
    left: 50%;
    position: fixed;
    transform: translate(-50%, 0px);
    z-index: 9999;
    width: 220px;
}
.toast-body {
    color: white;
    text-align: center;
}
.setting-button:disabled {
  border-color: black;
  color: gray;
}
</style>