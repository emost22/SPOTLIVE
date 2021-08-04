<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border ">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class='tabs'>
            <input type='radio' id='r1' name='t' checked>
            <label for='r1'>설정</label>
            <div class='content'>설정</div>
            <input type='radio' id='r2' name='t'>
            <label for='r2'>카메라</label>
            <div class='content'>카메라</div>
            <div id='slider'></div>
          </div>
        </div>
        <div class="modal-footer no-border ">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.tabs {
  top: 51%;
  left: 50%;
  height: 50%;
  min-width: 400px;
}

label, #slider {
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

.tabs [type=radio] {
  display: none;   
}

.content {
  position: absolute;
  top: 50px; right: 0; bottom: 0; left: 0;
  padding: 20px;
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

[type=radio],#r1:checked ~ #slider {
  transform: translate(-400px, 0px);
}

[type=radio],#r2:checked ~ #slider {
  transform: translate(-200px, 0px);
}

[type=radio]:checked + label {
  color: white;
}

[type=radio]:checked + label + .content {
  display: inline-block;
}
.modal-content-m {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  pointer-events: auto;
  outline: 0;
}
.no-border {
  border: 0px;
}
</style>