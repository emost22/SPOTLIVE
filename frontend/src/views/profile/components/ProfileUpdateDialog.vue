<template>
  <div class="modal fade" id="profileUpdateModal" tabindex="-1" aria-labelledby="profileUpdateModalLabel" aria-hidden="true">
    
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
     
      <div class="modal-content-m">

        <div class="modal-header no-border">
          <div class="profile-update-header">프로필 수정</div>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>

        <div class="modal-body mx-3 profile-update-box">
          <div><img :src="profileImageUrl" class="profile-update-img"></div>
          <div class="profile-update-info"> 
            <div>닉네임</div>
            <div class="input-bgcolor-light-grey">{{ profileNickname }}</div>
            <div>개인 설명</div>
            <div class="input-bgcolor-light-grey">{{ profileDescription }}</div>
            <!-- v-model로 새로운 input값 받아서 axios 요청 보내기 -->
          </div>
        </div>

        <div class="modal-footer-m">
          <div><button data-bs-dismiss="modal" aria-label="Close" type="button" class="bdcolor-ngreen small-button mx-3">취소</button></div>
          <div><button @click="clickProfileUpdateButton" type="button" class="bdcolor-npink small-button mx-3">저장</button></div>
        </div>

      </div>
    
    </div>
  
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ProfileUdateDialog',
  data: function() {
    return {
      userId: '',
      profileNickname: '',
      profileDescription: '',
      profileImageUrl: '',
    }
  },
  created: function () {
    this.getUser()
  },
  methods: {
    getUser() {
      this.userId = this.loginUser.accountEmail
      this.profileNickname = this.loginUser.profileNickname
      this.profileDescription = this.loginUser.profileDescription
      this.profileImageUrl = this.loginUser.profileImageUrl
    },
    clickProfileUpdateButton() {
      // 프로필 수정 axios actions.js에서 요청
    },
  },
  computed: {
    ...mapGetters(['loginUser',]),
  },

}
</script>

<style>
.profile-update-header {
  margin: 5px;
  font-size: 20px;
}
.profile-update-box {
  display: flex;
  direction: row;
  height: 200px;
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