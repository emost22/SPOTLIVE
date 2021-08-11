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
          <div class="profile-update-header">프로필 수정</div>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body mx-3 profile-update-box">
          <div><img :src="profileImageUrl" class="profile-update-img" /></div>
          <div class="profile-update-info">
            <div class="label-alignment">닉네임</div>
            <input class="custom-form-control" v-model="profileNickname" />
            <div class="label-alignment">개인 설명</div>
            <input class="custom-form-control" v-model="profileDescription" />
          </div>
        </div>

        <div class="modal-footer-m">
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
  margin: 5px;
  font-size: 20px;
  display: flex;
  align-items: center;
}
.profile-update-box {
  display: flex;
  direction: row;
  height: 200px;
  justify-content: space-between;
  align-content: space-around;
  align-items: center;
}
.profile-update-img {
  width: 100px;
  height: 100px;
  border-radius: 100%;
}
.profile-update-info {
  text-align: left;
  margin-left: 20px;
}
</style>
