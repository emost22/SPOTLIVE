<template>
  <div>
  <div class="modal fade" id="showDetailModal" tabindex="-1" aria-labelledby="showDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
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
                <div><img :src="getShowData.posterUrl" class="show-detail-img"></div>
                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showDetailFormControlInput1" class="form-label">공연명</label></div>
                    <div>{{ getShowData.showInfoTitle }}</div>
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showDetailFormControlInput2" class="form-label">티켓가격</label></div>
                    <div class="d-flex">
                    <div>{{ getShowData.price }}</div>
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment">
                        <label class="form-label">공연 시간 <button class="calendar-plus-button"></button></label>
                      </div>
                      <select class="custom-select-control" aria-label="Default select showDetail">
                        {{ getShowData.timetables}}
                        <option selected>7/29 13:00</option>
                        <option value="1">One</option>
                        <option value="2">One</option>
                        <!-- 삭제 버튼  -->
                      </select>
                    </div>
                    <div class="flex-fill me-3">
                      <div class="label-alignment">
                        <label class="form-label" for="showDetailFormControlInput4">
                          러닝타임
                        </label>   
                      </div>
                      {{ getShowData.runningTime }}
                    </div>
                  </div>
                </div>
              </div>
              <div class="mb-3">
                <div class="label-alignment"><label for="showDetailFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                {{ getShowData.description }}
              </div>
            </form>
        </div>
        <div class="modal-footer-m">
          <div><button @click="clickDeleteShow" type="button" class="bdcolor-ngreen small-button mx-3">삭제</button></div>
          <div><button type="button" class="bdcolor-npink small-button mx-3">수정</button></div>
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
      showId: '',
      title : '',
      description : '',
      price : '',
      runningTime : '',
      timetables: '',
      userId: '',
      profileNickname: '',
      profileImageUrl: '',
    }
  },
  created: function () {
    this.getShow()
  },
  methods: {
    getShow() {
      this.showId = this.getShowData.showInfoId
      this.title = this.getShowData.title
      this.description = this.getShowData.description
      this.posterUrl = this.getShowData.posterUrl
      this.price = this.getShowData.price
      this.runningTime = this.getShowData.runningTime
      this.timetables = this.getShowData.timetables
      this.userId = this.loginUser.accountEmail
      this.profileNickname = this.loginUser.profileNickname
      this.profileImageUrl = this.loginUser.profileImageUrl
    },
    clickDeleteShow() {
      // 팝업 띄우고 삭제 확인 버튼 확인하는 로직 추가하시면 됩니다
    },
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
}
</script>

<style>
/* show-detail-어찌구로 시작하는 클래스로 정의해주세요 다른 곳과 겹치지 않게 아님 scoped */
.show-detail-img {
  min-width: 150px;
  max-width: 150px;
  min-height: 200px;
  max-height: 200px;
}
</style>