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
            <div class="profile-update-info profile-update-info mb-2"><input class="custom-form-control" v-model="profileNickname" /></div>
            <div class="label-alignment profile-update-info mb-2">소개</div>
            <div class="profile-update-info"><textarea class="custom-form-control" v-model="profileDescription" rows="3"/></div> 
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
  data: function() {
    return {
      userId: '',
      profileNickname: '',
      profileDescription: '',
      profileImageUrl: '',
    };
  },
  created: function() {
    this.getUser();
  },
  methods: {
    getUser() {
      this.userId = this.loginUser.accountEmail;
      this.profileNickname = this.loginUser.profileNickname;
      this.profileDescription = this.loginUser.profileDescription;
      this.profileImageUrl = this.loginUser.profileImageUrl;
    },

    clickProfileUpdateButton() {
      let userUpdatePatchReq = {
        accountEmail: this.userId,
        profileNickname: this.profileNickname,
        profileDescription: this.profileDescription,
      };

      this.$store
        .dispatch('requestUpdateProfile', userUpdatePatchReq)
        .then((response) => {
          console.log(response.data);
          localStorage.setItem('loginUser', JSON.stringify(response.data));
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    ...mapGetters(['loginUser']),
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
  margin-left: 8%;
}
.profile-update-img {
  width: 180px;
  height: 180px;
  border-radius: 100%;
}
.profile-update-info {
  text-align: left;
}
</style>
