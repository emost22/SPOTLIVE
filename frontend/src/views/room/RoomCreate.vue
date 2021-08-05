<template>
  <div class="wrapper">
    <div class="full-wide-screen"> 
    </div>
    <div class="btn-wrapper">
      <button class="bdcolor-bold-ngreen extra-big-button" data-bs-toggle="modal" data-bs-target="#exampleModal"> 설정 </button>
      <button class="bdcolor-bold-npink extra-big-button"> 스트리밍 시작 </button>
    </div>
  </div>
</template>

<script>
export default {
  
}
</script>

<style>

.wrapper {
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.btn-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin-top: 30px;
  margin-right: 50px;
  margin-left: 50px;
}

</style>