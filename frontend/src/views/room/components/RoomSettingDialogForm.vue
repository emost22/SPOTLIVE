<template>
  <div>
    <div class="mb-3">
      <div class="label-alignment"><label for="videoTitle" class="form-label">제목</label></div>
      <input class="custom-form-control" id="videoTitle" v-model="form.videoTitle">
    </div>
    <div class="mb-3 d-flex">
      <div class="flex-fill me-3">
        <div class="label-alignment"><label class="form-label" for="categoryId">분류</label></div>
        <select class="custon-select-control" aria-label="Default select example" v-model="form.categoryId" id="categoryId">
          <option :key="i" :value="d.categoryId" v-for="(d, i) in categoryIds">{{ d.categoryName }}</option>
        </select>
      </div>
      <div>
        <div class="label-alignment"><label class="form-label">영상용도</label>
        <div class="icon-info" data-bs-toggle="tooltip" data-bs-placement="top" title="용도를 꼭 확인해주세요!💥"></div>
        </div>
        <div class="d-flex mt-1">
          <div class="form-check">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forShow" value="공연" v-model="form.mode">
            <label class="form-check-label" for="forShow" ref="forShow" data-bs-toggle="tooltip" data-placement="bottom" title="등록된 공연을 보여주기 위한 목적">
              공연용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forAd" value="홍보" v-model="form.mode">
            <label class="form-check-label" for="forAd" ref="forAd" data-bs-toggle="tooltip" data-placement="bottom" title="예매시스템이 갖춰진 공연 홍보 목적">
              홍보용
            </label>
          </div>
          <div class="form-check ms-2">
            <input class="form-check-input" type="radio" name="flexRadioDefault" id="forCommunicate" value="소통" v-model="form.mode">
            <label class="form-check-label" for="forCommunicate" ref="forCommunicate" data-bs-toggle="tooltip" data-placement="bottom" title="예매시스템 없이 관객과의 소통 목적">
              소통용
            </label>
          </div>
        </div>
      </div>
    </div>
    <div class="mb-3" v-if="form.mode=='공연' || form.mode=='홍보'">
      <div class="label-alignment"><label for="showInfoId" class="form-label">등록한 공연 선택</label></div>
      <div class="d-flex">
      <select @change="getRecentlyTimeTable()" class="custon-select-control" aria-label="Default select example" v-model="form.showInfoId" id="showInfoId">
        <option :key="i" :value="d.t.showInfoId" v-for="(d, i) in showInfoIds">{{ d.t.showInfoTitle }}</option>
      </select>
      <button class="plus-button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop"> </button>
      </div>
      <input v-if="form.mode=='공연'" class="custom-form-control mt-1" id="showTime" v-model="form.showTime" readonly="readonly" disabled="disabled">
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="thumbnail" class="form-label">썸네일</label></div>
      <div class="d-flex">
        <input type="file" class="custom-file-input" id="thumbnail" @change="handleFileChange">
        <input class="custom-form-control" v-model="fileName" readonly="readonly" disabled="disabled"/>
        <label data-browse="Browse" class="search-button" for="thumbnail" @change="handleFileChange">
        </label>
      </div>
    </div>
    <div class="mb-3">
      <div class="label-alignment"><label for="videoDescription" class="form-label">설명</label></div>
      <textarea class="custom-form-control" id="videoDescription" rows="3" v-model="form.videoDescription"></textarea>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'RoomSettingDialogForm',
  props: {
    categoryIds: {
      type: Array,
      default: [],
    },
    showInfoList: {
      type: Array,
      default: [],
    },
  },
  data: function () {
    return {
      form: {
        categoryId: '1',
        thumbnailImage: [],
        videoDescription: '',
        videoTitle: '',
        showInfoId: '',
        showTime:'',
        mode: '공연',
      },
      thumbnail: '',
      fileName:'',
      showInfoIds: [],
      toast: null,
    }
  },
  computed: {
    ...mapGetters(['createdVideoData', 'isSettingDialogOpen', 'settingDialogViewId']),
  },
  watch: {
    isSettingDialogOpen(value, oldvalue) {
      if (value && this.settingDialogViewId==2) {
        this.form.categoryId = this.createdVideoData.categoryId
        this.form.fileName = this.createdVideoData.thumbnailImage
        this.form.videoDescription = this.createdVideoData.videoDescription
        this.form.videoTitle = this.createdVideoData.videoTitle
        this.form.showInfoId = this.createdVideoData.showInfoId
        this.form.showTime = this.createdVideoData.showTime
      }
    },
  },
  methods: {
    handleFileChange(e) {
      this.form.thumbnailImage = e.target.files[0]
      this.fileName = e.target.files[0].name
    },
    makeShowInfoIds() {
      this.showInfoList.filter((showInfo, index) => {
        this.showInfoIds.push({ v: index, t: showInfo,})
      })
    },
    getRecentlyTimeTable() {
      this.$store.dispatch("requestGetRecentlyTimeTable", { showInfoId: this.form.showInfoId })
      .then((response) => {
        console.log(response.data)
        this.form.showTime = response.data.dateTime
      }).catch((error) => {
      })
    },
    makeToolTipsObject () {
      var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
      var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return bootstrap.Tooltip.getOrCreateInstance(tooltipTriggerEl)
      })
    }
  },
  created() {
    this.makeShowInfoIds()
  },
  mounted() {
    this.makeToolTipsObject()
  },
  beforeUpdate() {
    console.log("asdf")
    if (this.form.mode == '홍보' || this.form.mode == '소통') {
        delete this.form.showTime
      if (this.form.mode == '소통') {
        delete this.form.showInfoId
      }
    } 
    this.$emit('form-data', this.form)
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
.tooltip.tooltip-top,
.tooltip.tooltip-bottom,
.tooltip.tooltip-left,
.tooltip.tooltip-right {
  z-index: 100000;
}
</style>