<template>
  <div>
    <div class="mb-3">
      <div class="label-alignment"><label for="videoTitle" class="form-label">제목</label></div>
      <input type="email" class="custom-form-control" id="videoTitle" v-model="videoTitle">
    </div>
    <div class="mb-3 d-flex">
      <div class="flex-fill me-3">
        <div class="label-alignment"><label class="form-label" for="categoryId">분류</label></div>
        <select class="custon-select-control" aria-label="Default select example" v-model="categoryId" id="categoryId">
          <option :key="i" :value="d.v" v-for="(d, i) in categoryIds">{{ d.t }}</option>
        </select>
      </div>
      <div>
        <div class="label-alignment"><label class="form-label">영상용도</label><div class="icon-info"></div></div>
        <div class="d-flex mt-1">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forShow" value="1" v-model="mode">
            <label class="form-check-label" for="forShow">
              공연용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forAd" value="2" v-model="mode">
            <label class="form-check-label" for="forAd">
              홍보용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forCommunicate" value="3" v-model="mode">
            <label class="form-check-label" for="forCommunicate">
              소통용
            </label>
          </div>
        </div>
      </div>
    </div>
    <div class="mb-3" v-if="mode==1 || mode==2">
      <div class="label-alignment"><label for="showInfoId" class="form-label">등록한 공연 선택</label></div>
      <div class="d-flex">
      <select class="custon-select-control" aria-label="Default select example" v-model="showInfoId" id="showInfoId">
        <option :key="i" :value="d.v" v-for="(d, i) in showInfoIds">{{ d.t }}</option>
      </select>
      <button class="plus-button"></button>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="thumbnail" class="form-label">썸네일</label></div>
      <div class="d-flex">
        <input type="file" class="custom-file-input" id="thumbnail" @change="handleFileChange">
        <input class="custom-form-control" v-model="fileName"/>
        <label data-browse="Browse" class="search-button" for="thumbnail" @change="handleFileChange">
        </label>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="videoDescription" class="form-label">설명</label></div>
      <textarea class="custom-form-control" id="videoDescription" rows="3" v-model="videoDescription"></textarea>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomSettingDialogForm',
  data: function () {
    return {
      categoryId: '1',
      categoryIds: [
        { v: "1", t: "뮤지컬" },
        { v: "2", t: "마술" },
        { v: "3", t: "연극" },
      ],
      mode: 1,
      thumbnail: '',
      file: [],
      fileName:'',
      videoDescription: '',
      videoTitle: '',
      showInfoId: '1',
      showInfoIds: [
        { v: "1", t: "옥탑방 고냥쓰들" },
        { v: "2", t: "캣츠" },
        { v: "3", t: "룰루" },
      ]
    }
  },
  methods: {
    handleFileChange(e) {
      this.fileName = e.target.files[0].name
    }
  }
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
  padding: .375rem 2.25rem .375rem .75rem;
  width: 100%;
  background-color: #595959;
  border: 0px;
  font-size: 1rem;
  color: white;
  border: 0px;
  border-radius: .25rem;
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

.custom-file-input {
    display: none;
}
</style>