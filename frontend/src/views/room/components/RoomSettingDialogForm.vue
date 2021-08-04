<template>
  <div>
    <form>
    <div class="mb-3">
      <div class="label-alignment"><label for="exampleFormControlInput1" class="form-label">제목</label></div>
      <input type="email" class="custom-form-control" id="exampleFormControlInput1">
    </div>
    <div class="mb-3 d-flex">
      <div class="flex-fill me-3">
        <div class="label-alignment"><label class="form-label">분류</label></div>
        <select class="custon-select-control" aria-label="Default select example">
          <option selected>뮤지컬</option>
          <option value="1">One</option>
        </select>
      </div>
      <div>
        <div class="label-alignment"><label for="exampleFormControlInput1" class="form-label">영상용도</label><div class="icon-info"></div></div>
        <div class="d-flex mt-1">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
            <label class="form-check-label" for="flexRadioDefault1">
              영상용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
              홍보용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3">
            <label class="form-check-label" for="flexRadioDefault3">
              소통용
            </label>
          </div>
        </div>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="exampleFormControlInput3" class="form-label">등록한 공연 선택</label></div>
      <div class="d-flex">
      <select class="custon-select-control" aria-label="Default select example">
        <option selected>옥탑방 고냥쓰들</option>
        <option value="1">One</option>
      </select>
      <button class="plus-button"></button>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="exampleFormControlInput3" class="form-label">썸네일</label></div>
      <div class="d-flex">
      <input type="email" class="custom-form-control" id="exampleFormControlInput1">
      <button class="search-button"></button>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="exampleFormControlTextarea1" class="form-label">설명</label></div>
      <textarea class="custom-form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
    </div>
  </form>

  </div>
</template>

<script>
export default {

}
</script>

<style>
form {
  margin-top: 20px;
}

.plus-button {
  width: 45px;
  height: 40px;
  margin-left: 10px;
  background-color: #595959;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-plus.png');
  background-repeat: no-repeat;
  background-position: center;
}

.search-button {
  width: 45px;
  height: 40px;
  margin-left: 10px;
  background-color: #595959;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-search.png');
  background-repeat: no-repeat;
  background-position: center;
}

.icon-info {
  display: inline-block;
  margin-left: 3px;
  width: 15px;
  height: 15px;
  background-image: url('~@/assets/icon-info.png');
  background-repeat: no-repeat;
  background-position: center;
}

.custom-form-control {
  display: block;
  width: 100%;
  background-color: #595959;
  border: 0px;
  color: white;
  border-radius: .25rem;
  padding: .375rem .75rem;
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

.label-alignment {
  text-align: left;
}


.form-check-input {
  background-color: #595959;
}

.form-check-input:checked {
    background-color: #04F7CA;
    border-color: #04F7CA;
}
</style>