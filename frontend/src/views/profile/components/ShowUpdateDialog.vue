<template>
  <div class="modal fade" id="showUpdateModal" tabindex="-1" aria-labelledby="showUpdateModalLabel" aria-hidden="true">
    <!-- <div class="modal-dialog bdcolor-bold-npurple modal-design modal-dialog-scrollable">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3">
                <div><img :src="loginUser.profileImageUrl" class="profile-small-img bdcolor-bold-npink"></div>
                <div class="profile-small-detail">
                  <div> {{ loginUser.profileNickname }} </div>
                  <div> {{ loginUser.accountEmail }} </div>
                </div>
              </div>
              <div class="d-flex flex-row justify-content-evenly mb-3">
                
                <div>
                  <label class="camera-input-button" for="input-file"/>
                  <input type="file" id="input-file" class="show-poster-input" v-on:change="handleChange">
                  <div v-if="preview">
                    <img class="preview-img" v-bind:src="preview">
                  </div>
                  <div v-else class="camera-input-bgcolor-light-grey show-img">
                  </div>
                </div>

                <div class="file-preview-container">
                  <div class="file-preview-wrapper">
                    <div v-if="preview">
                      <div class="file-close-button" @click="fileDeleteButton">
                        x
                      </div>
                      <img :src="preview">
                    </div>
                    <div v-else>
                      <label class="camera-input-button" for="input-file"/>
                      <input type="file" id="input-file" class="show-poster-input" v-on:change="handleChange">
                    </div>
                  </div>
                </div>

                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput1" class="form-label">공연명</label></div>
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput1" v-model="getShowData.showInfoTitle">
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput2" class="form-label">티켓가격</label></div>
                    <div class="d-flex">
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput2" v-model="getShowData.price">
                    </div>
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput3" class="form-label">러닝타임</label></div>
                    <div class="d-flex">
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput4" v-model="getShowData.runningTime">
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div>
                        <div class="label-alignment"><label for="showCreateFormControlInput4" class="form-label">공연 시간</label></div>
                        <datetime class="datetime-theme" type="datetime" ref="datetimePicker" v-model="datetime"></datetime>
                      </div>
                    </div>
                    <div class="flex-fill me-3">
                      <button @click="doAdd" type="button" class="btn-add-timetable txtcolor-nyellow">추가</button>
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                  <div class="flex-fill me-3">
                      <select class="custom-select-control" v-model="selected">
                        <option :key="i" :value="d.dateTime" v-for="(d, i) in getShowData.timetables">
                          {{ d.dateTime }}
                        </option>
                      </select>
                      
                  </div>
                  <div class="flex-fill me-3">
                      <button @click="doRemove" type="button" class="btn-remove-timetable txtcolor-nyellow">삭제</button>
                    </div>
                </div>
              </div>
              </div>
              <div class="mb-3">
                <div class="label-alignment"><label for="showCreateFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                <textarea class="custom-form-control" id="showCreateFormControlTextarea1" rows="3" v-model="getShowData.showInfoDescription"></textarea>
              </div>
            </form>
        </div>
        <div class="modal-footer-m">
          <div><button type="button" class="bdcolor-ngreen small-button mx-3" data-bs-dismiss="modal">취소</button></div>
          <div><button @click="clickShowPostButton" type="button" class="bdcolor-npink small-button mx-3" data-bs-dismiss="modal">등록</button></div>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'ShowUpdateDialog',
  data: function() {
    return {
      timetableId: '',
      timetables: [],
    }
  },
  created: function () {
  },
  mounted() {
    this.getShowInfoTimeTable()
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
    }
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
  watch: {
    
  },
}
</script>

<style>

</style>