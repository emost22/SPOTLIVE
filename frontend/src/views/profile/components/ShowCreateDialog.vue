<template>
  <div class="modal fade" id="showCreateModal" tabindex="-1" aria-labelledby="showCreateModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
            <form>
              <div class="d-flex flex-row mb-3">
                <div><img src="~@/assets/icon-profile.png" class="profile-small-img bdcolor-bold-npink"></div>
                <div class="profile-small-detail">
                  <div> 나예뽀님</div>
                  <div> email@naver.com </div>
                </div>
              </div>
              <div class="d-flex flex-row justify-content-evenly mb-3">
                <div class="camera-input-bgcolor-light-grey show-img">
                  <div><button class="camera-input-button"></button></div>
                </div>
                <div class="show-info">
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput1" class="form-label">공연명</label></div>
                    <input type="email" class="custom-form-control" id="showCreateFormControlInput1">
                  </div>
                  <div class="mb-3">
                    <div class="label-alignment"><label for="showCreateFormControlInput3" class="form-label">티켓가격</label></div>
                    <div class="d-flex">
                    <input type="text" class="custom-form-control" id="showCreateFormControlInput1">
                    </div>
                  </div>
                  <div class="mb-3 d-flex">
                    <div class="flex-fill me-3">
                      <div class="label-alignment">
                        <label class="form-label">공연 시간</label>
                        <button class="calendar-plus-button"></button>
                      </div>
                      <select class="custom-select-control" aria-label="Default select showCreate">
                        <option selected>7/29 13:00</option>
                        <!-- 삭제 버튼  -->
                        <option value="1">One</option>
                        <option value="2">One</option>
                      </select>
                    </div>
                    <div class="flex-fill me-3">
                      <div class="label-alignment">
                        <label class="form-label">러닝타임</label>
                      </div>
                      <input type="text" class="custom-form-control" id="showCreateFormControlInput1">
                    </div>
                  </div>
                </div>
              </div>
              <div class="mb-3">
                <div class="label-alignment"><label for="showCreateFormControlTextarea1" class="form-label"> 공연 설명</label></div>
                <textarea class="custom-form-control" id="showCreateFormControlTextarea1" rows="3"></textarea>
              </div>
            </form>
        </div>
        <div class="modal-footer-m">
          <div><button type="button" class="bdcolor-ngreen small-button mx-3">취소</button></div>
          <div><button type="button" class="bdcolor-npink small-button mx-3">등록</button></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomSettingDialog',
  components: {
  }
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