<template>
  <div>
    <div class="modal fade" id="showReservationInProfileModal" tabindex="-1" aria-labelledby="showReservationInProfileModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
        예약 {{ getShowData.title }}
        <!-- 타인이 내 프로필에서 들어오는 경우 인자 -->
        {{ getShowData.showId}}
        {{ getShowData.timetables }}
        <!-- 나의 예약내역에서 들어오는 경우 인자 -->
        {{ getShowData.date }}
        {{ getShowData.time }}
        <!-- <div class="modal-content-m">
          <div class="modal-header no-border">
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body mx-2">
            <div class="dialog-profile-info">
              <div><img :src="getShowData.userRes.profileImageUrl" class="profile-img"></div>
              <div class="profile-detail-show-reservation">
                <div> <span class="txtcolor-nyellow">{{ getShowData.userRes.userName }}</span> 님</div>
                <p> {{ getShowData.userRes.accountEmail }}</p>
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
                        <select class="custom-select-control-m" v-model="timetableId">
                          <option :key="i" :value="d.v" v-for="(d, i) in timetables">{{ d.t }}</option>
                        </select>
                      </div>
                      <div>
                        <div class="label-alignment">
                          <label class="form-label label-in-dialog">러닝타임</label>
                        </div>
                        <input type="text" class="custom-form-control" v-model="getShowDataRunningTime" readonly="readonly" disabled>
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
          <div class="modal-footer-m">
            <button type="button" class="bdcolor-npink small-button me-5" data-bs-dismiss="modal">닫기</button>
            <button type="button" class="bdcolor-ngreen small-button" @click="reservateShow()">예약하기</button>
          </div>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ShowReservationDialogInProfile',
  data: function() {
    return {
      showId: '',
      userId: '',
      timetables: [],
      user: {},
      timetableId: '',
    }
  },
  created: function () {
  },
  methods: {
  },
  computed: {
    ...mapGetters(['loginUser', 'getShowData',]),
  },
  watch: {
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
</style>