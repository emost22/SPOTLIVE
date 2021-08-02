<template>
  <div id="app">
    <div id="nav">
      <router-link to="/main">Main</router-link> 
    </div>
    <router-view/>
  </div>
</template>

<style>
body {
  /* 메인 배경 */
  background-color: #121212;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
  text-align: center;
  color: #2c3e50;
}
/* 메인 배경 */
.main-bgcolor-black {
  background-color: #121212;
}
/* 헤더, 사이드바, 필터, 모달 배경 */
.bgcolor-deep-grey {
  background-color: #242424;
  width: 500px;
  height: 500px;
}
/* 검색창, 알림 */
.bgcolor-mid-deep-grey {
  background-color: #6A6A6A;
}
/* 모달 인풋*/
.input-bgcolor-light-grey {
  background-color: #595959;
}
/* 카메라 인풋 */
.camera-input-bgcolor-light-grey {
  background-color: #C4C4C4;
}
.txtcolor-white {
  color: #FFFFFF;
}
.txtcolor-npink {
  color: #F84ABF;
  text-shadow:
      0 0 9px #F84ABF;
}
.txtcolor-ngreen {
  color: #04F7CA;
  text-shadow:
      0 0 9px #04F7CA;
}
.txtcolor-nyellow {
  color: #FEF279;
  text-shadow:
      0 0 9px #FEF279;
}
.txtcolor-npurple {
  color: #C752FE;
  text-shadow:
      0 0 9px #C752FE;
}
.bdcolor-ngreen {
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA;
}
.bdcolor-bold-ngreen {
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA,
    0 0 12px #04F7CA;
}
.bdcolor-npink {
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF;
}
.bdcolor-bold-npink {
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF,
    0 0 12px #F84ABF;
}
.bdcolor-nyellow {
  border: 2px solid #FEF279;
  box-shadow: 
    0 0 9px #FEF279;
}
.bdcolor-bold-nyellow {
  border: 2px solid #FEF279;
  box-shadow: 
    0 0 9px #FEF279,
    0 0 12px #FEF279;
}
.bdcolor-npurple {
  border: 2px solid #C752FE;
  box-shadow: 
    0 0 9px #C752FE;
}
.bdcolor-bold-npurple {
  border: 2px solid #C752FE;
  box-shadow: 
    0 0 9px #C752FE,
    0 0 12px #C752FE;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
