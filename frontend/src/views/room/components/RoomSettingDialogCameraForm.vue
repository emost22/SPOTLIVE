<template>
  <div class="camera-setting-wrapper">
    <div class="prev-camera-screen">
    </div>
    <select class="camera-setting-selector mb-3" aria-label="Default select example">
      <option selected>FaceTime HD 카메라(내장형)</option>
      <option value="1">FaceTime HD 카메라(내장형)</option>
    </select>
    <select class="camera-setting-selector mb-3" aria-label="Default select example">
      <option selected>음성오디오(내장형)</option>
      <option value="1">음성오디오(내장형)</option>
    </select>
  </div>
</template>

<script>
export default {

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