<template>
  <div class="wrapper">
    <div class="wide-screen"> 
    </div>
    <div class="right-side">
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.wide-screen {
  flex-grow: 4;
  margin-right: 10px;
  height: 80%;
  background-color: lightgrey;
}
.wrapper {
  width: 100%;
  height: 90%;
  padding: 30px;
  display: flex;
}
.right-side {
  flex-grow: 1;
  height: 80%;
  background-color: lightgrey;
}
</style>