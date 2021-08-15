<template>
  <div
    class="modal fade"
    id="profileUpdateModal"
    tabindex="-1"
    aria-labelledby="profileUpdateModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog bdcolor-bold-npurple modal-design">

      <div class="modal-content-m">
        <div class="modal-header no-border">
          <div class="profile-update-header mt-3 ms-3">프로필 수정</div>
          <button
            type="button"
            class="btn-close me-2 mt-1"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body mx-3 profile-update-box">
          <div class="profile-update-img-box"><img :src="loginUser.profileImageUrl" class="profile-update-img" /></div>
          <div>
            <div class="label-alignment profile-update-info mb-2">닉네임</div>
            <div class="profile-update-info profile-update-info mb-2"><input class="custom-form-control" v-model="loginUser.profileNickname" /></div>
            <div class="label-alignment profile-update-info mb-2">소개</div>
            <div class="profile-update-info"><textarea class="custom-form-control" v-model="loginUser.profileDescription" rows="3"/></div> 
          </div>
        </div>

        <div class="modal-footer-m my-3">
          <div>
            <button
              data-bs-dismiss="modal"
              aria-label="Close"
              type="button"
              class="bdcolor-ngreen small-button mx-3"
            >
              취소
            </button>
          </div>
          <div>
            <button
              @click="clickProfileUpdateButton"
              type="button"
              class="bdcolor-npink small-button mx-3"
              data-bs-dismiss="modal"
            >
              저장
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'ProfileUdateDialog',
  methods: {
    clickProfileUpdateButton() {
      let userUpdatePatchReq = {
        accountEmail: this.loginUser.accountEmail,
        profileNickname: this.loginUser.profileNickname,
        profileDescription: this.loginUser.profileDescription,
        userName: this.loginUser.userName,
      };

      this.$store
        .dispatch('requestUpdateProfile', userUpdatePatchReq)
        .then((response) => {
          console.log(response.data);
          localStorage.setItem('loginUser', JSON.stringify(response.data));
          this.getMyProfile()
        })
        .catch((error) => {
          console.log(error);
        });
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
  },
  computed: {
    ...mapGetters(['loginUser', 'createdProfileData']),
  },
};
</script>

<style>
.profile-update-header {
  font-size: 20px;
  font-weight: bold;
}
.profile-update-box {
  display: flex;
  direction: row;
  height: 200px;
  justify-content: space-between;
  align-content: space-around;
  align-items: center;
}
.profile-update-img-box {
  display: flex;
  direction: row;
  margin-left: 5%;
}
.profile-update-img {
  width: 180px;
  height: 180px;
  border-radius: 100%;
  border: none;
  box-shadow: 
    0 0 9px #FFFFFF,
    0 0 12px #FFFFFF,
    0 0 20px #FFFFFF,
    0 0 35px #FFFFFF;
}
.profile-update-info {
  text-align: left;
}
</style>
