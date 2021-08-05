<template>
  <div class="modal fade" id="showCreateModal" tabindex="-1" aria-labelledby="showCreateModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            <form>
              <div class="d-flex flex-row">
                <div><img src="~@/assets/icon-profile.png" class="profile-small-img bdcolor-bold-npink"></div>
                <div class="mx-2">
                  <p> 나예뽀님</p>
                  <p> email@naver.com </p>
                </div>
              </div>
              <div class="d-flex flex-row">
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
  width: 40px;
  height: 40px;
  margin-left: 10px;
  background-color: #C4C4C4;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-camera-input.png');
  background-repeat: no-repeat;
  background-position: center;
}
.show-img {

}
.show-info {

}
.profile-small-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
</style>