<template>
  <div class="camera-setting-wrapper">
    <div class="prev-camera-screen">
    </div>
    <select class="camera-setting-selector mb-3" v-model="cameraId" id="cameraId">
      <option :key="i" :value="d.v" v-for="(d, i) in cameraIds">{{ d.t }}</option>
    </select>
    <select class="camera-setting-selector mb-3" v-model="audioId" id="audioId">
      <option :key="i" :value="d.v" v-for="(d, i) in audioIds">{{ d.t }}</option>
    </select>
  </div>
</template>

<script>
export default {
  name: 'RoomSettingDialogCameraForm',
  data: function () {
    return {
      cameraId: '1',
      cameraIds: [
        { v: "1", t: "FaceTime HD 카메라(내장형)" },
      ],
      audioId: '1',
      audioIds: [
        { v: "1", t: "옥탑방 음성오디오(내장형)" },
      ]
    }
  },
}
</script>

<style>
.camera-setting-wrapper {
  height: 250px;
  margin-top: 40px;
  padding-right: 30px;
  padding-left: 30px;
}
.prev-camera-screen {
  margin-bottom: 30px;
  height: 80%;
  background-color: lightgrey;
}
.camera-setting-selector {
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