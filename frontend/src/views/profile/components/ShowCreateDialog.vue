<template>
  <div class="modal fade" id="showCreateModal" tabindex="-1" aria-labelledby="showCreateModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design modal-dialog-scrollable">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3">
                <div><img :src="profileImageUrl" class="profile-small-img bdcolor-bold-npink"></div>
                <div class="profile-small-detail">
                  <div> {{ profileNickname }} </div>
                  <div> {{ userId }} </div>
                </div>
              </div>
              <div class="d-flex flex-row justify-content-evenly mb-3">
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
                      <!-- <div class="show-img"> -->
                        <label class="camera-input-button" for="input-file"/>
                        <input type="file" id="input-file" class="show-poster-input" v-on:change="handleChange">
                      <!-- </div> -->
                    </div>
                  </div>
                </div>

                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput1" class="form-label">공연명</label></div>
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput1" v-model="showInfoTitle">
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment"><label for="showCreateFormControlInput2" class="form-label">티켓가격</label></div>
                      <input type="text" class="custom-form-control" id="showCreateFormControlInput2" v-model="price">
                    </div>
                    <div class="flex-fill">
                      <div class="label-alignment"><label for="showCreateFormControlInput3" class="form-label">러닝타임</label></div>
                      <input type="text" class="custom-form-control" id="showCreateFormControlInput4" v-model="runningTime">
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                      <div>
                        <div class="label-alignment"><label for="showCreateFormControlInput4" class="form-label">공연 시간</label></div>
                        <datetime class="datetime-theme" type="datetime" ref="datetimePicker" v-model="datetime"></datetime>
                      </div>
                      <div>
                        <button @click="doAdd" type="button" class="btn-add-timetable txtcolor-nyellow">추가</button>
                      </div>
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                  <div class="flex-fill me-3 d-flex flex-row justify-content-start">
                      <select class="custom-select-control" v-model="selected">
                        <option :key="i" :value="d.dateTime" v-for="(d, i) in timetables">
                          {{ formatter(d.dateTime) }}
                        </option>
                      </select>
                      <div>
                        <button @click="doRemove" type="button" class="btn-remove-timetable txtcolor-nyellow">삭제</button>
                      </div>
                  </div>
                  
                </div>
              </div>
              </div>
              <div class="mb-3">
                <div class="label-alignment"><label for="showCreateFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                <textarea class="custom-form-control" id="showCreateFormControlTextarea1" rows="3" v-model="showInfoDescription"></textarea>
              </div>
            </form>
        </div>
        <div class="modal-footer-m">
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
  created: function () {
    this.getUser()
    this.preview = ''
  },
  methods: {
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
      this.timetables.push({dateTime: this.datetime})
    },
    doRemove(){
      let filtered = this.timetables.filter((element) => element.dateTime !== this.selected);
      this.timetables = filtered;
    },
    getUser() {
      this.userId = this.loginUser.accountEmail
      this.profileNickname = this.loginUser.profileNickname
      this.profileImageUrl = this.loginUser.profileImageUrl
    },
    formatter(date) {
      var dateTime = new Date(date)
      return `${dateTime.toLocaleString()}`
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
        
      }).catch(error => {
        console.log(error)
      })
    }
  },
  computed: {
    ...mapGetters(['loginUser',]),
  },
}
</script>

<style>
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
  margin: auto;
  margin-top: 50%;
}
.show-img {
  width: 100%;
  margin-right: 20px;
}
.show-info {
  width: 100%;
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
</style>