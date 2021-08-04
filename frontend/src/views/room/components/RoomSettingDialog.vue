<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class='tabs'>
            <input type='radio' id='r1' name='t' checked>
            <label for='r1' class="tab-label">설정</label>
            <div class='content'>
              <RoomSettingDialogForm/>
            </div>
            <input type='radio' id='r2' name='t'>
            <label for='r2' class="tab-label">카메라</label>
            <div class='content'>
              <RoomSettingDialogCameraForm/>
            </div>
            <div id='slider'></div>
          </div>
        </div>
        <div class="modal-footer-m">
          <button type="button" class="bdcolor-ngreen small-button">확인</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RoomSettingDialogForm from './RoomSettingDialogForm.vue'
import RoomSettingDialogCameraForm from './RoomSettingDialogCameraForm.vue'
export default {
  name: 'RoomSettingDialog',
  components: {
    RoomSettingDialogForm,
    RoomSettingDialogCameraForm
  }
}
</script>

<style>
.tabs {
  top: 51%;
  left: 50%;
  height: 50%;
  min-width: 400px;
}
.tab-label {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  color: #AAA;
  width: 200px;
  height: auto;
  padding: 20px 0px;
}

#slider {
  display: inline-block;
  font-weight: bold;
  text-align: center;
  color: #AAA;
  width: 200px;
  height: auto;
  padding: 20px 0px;
}

label:hover {
  color: white;
  cursor: pointer;
}

.tabs [name=t] {
  display: none;   
}

.content {
  position: absolute;
  top: 50px; right: 0; bottom: 0; left: 0;
  padding: 30px;
  display: none;
}

#slider {
  background-color: transparent;
  position: absolute;
  border-bottom: 3px solid #C752FE;
  margin: 10px 0px;
  transition: transform 0.3s;
  width: 200px;
}

[name=t],#r1:checked ~ #slider {
  transform: translate(-400px, 0px);
}

[name=t],#r2:checked ~ #slider {
  transform: translate(-200px, 0px);
}

[name=t]:checked + label {
  color: white;
}

[name=t]:checked + label + .content {
  display: inline-block;
}

.custon-select-control {
  background-color: #595959;
  padding: .375rem 2.25rem .375rem .75rem;
  width: 100%;
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
</style>