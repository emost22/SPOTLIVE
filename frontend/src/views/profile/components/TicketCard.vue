<!--
  작성자 : 권영린
  컴포넌트 설명 : TicketDialog에서 받아온 예약정보를 하나의 티켓으로 출력
-->
<template>
  <div class="ticket">
    <div :class="className+' ticket-img'">
      <div class="ticket-small-header-line">
        <div class="ticket-header ticket-title">
          {{ title }}
        </div>
        <!-- <div class="ticket-header ticket-host">
          {{ profileNickname }}
        </div> -->
      </div>
      <div class="ticket-btns-box">
        <div class="ticket-small-btn-line">
          <div class="ticket-small-btn-box">
            <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
              {{ date }}
            </button>
          </div>
          <div class="ticket-small-btn-box">
            <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
              {{ time }}
            </button>
          </div>
          <div class="ticket-small-btn-box">
            <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
              {{ runningTime }}분
            </button>
          </div>
        </div>
        <div class="ticket-btn-line">
          <div class="ticket-btn-box">
            <button 
              class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-npurple"
              data-bs-toggle="offcanvas" 
              data-bs-target="#deleteTicketInfo" 
              aria-controls="deleteTicketInfo"
              @click="clickCancleTicketbutton"
            >
              예약 취소
            </button>
          </div>
          <div
            class="ticket-btn-box" 
            data-bs-toggle="modal" 
            data-bs-target="#ticketDetailModal"
          >
            <button 
              @click="clickTicketDetailButton" 
              class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-ngreen"
            >
              예약 상세
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  
  name: 'TicketCard',
  props: {
    timetable: {
      type: Object,
      required: true
    },
    showInfo: {
      type: Object,
      required: true
    },
    showHost: {
      type: Object,
      required: true
    },
    idx: {
      type: Number,
      required: true
    },
  },
  data: function() {
    return {
      className: 'ticket-img-num',
      userId: '',
      profileNickname: '',
      profileImageUrl: '',
      showId : '',
      title: '',
      description: '',
      posterUrl: '',
      price: '',
      runningTime : '',
      date : '',
      time : '',
      timetableId : '',
      timetables: [],
    }
  },
  watch: {
    timetable : function(){
      this.getHostInfo()
      this.getTicketImg()
      this.getReservationInfo()
    }
  },
  methods: {
    getHostInfo() {
      this.userId = this.showHost.accountEmail
      this.profileNickname = this.showHost.profileNickname
      this.profileImageUrl = this.showHost.profileImageUrl
    },
    getTicketImg() {
      if (this.idx%2) {
        this.className = "ticket-img-num"
      } else {
        this.className = "ticket-img-str"
      }
    },
    getReservationInfo() {
      this.showId = this.showInfo.showInfoId
      this.title = this.showInfo.showInfoTitle
      this.description  = this.showInfo.showInfoDescription
      this.posterUrl  = this.showInfo.posterUrl
      this.price  = this.showInfo.price
      this.runningTime = this.showInfo.runningTime
      let dateTime = this.timetable.dateTime.substr(5,11).split("T")
      this.date = dateTime[0].replace("-", "/")
      this.time = dateTime[1]
      this.timetableId = this.timetable.timetableId
      this.fullDateTime = this.timetable.dateTime
      this.timetables = []
      this.timetables.push({dateTime: this.fullDateTime, timetableId : this.timetableId})
    },
    clickTicketDetailButton() {
      let showData = {
        userId: this.userId,
        profileNickname: this.profileNickname,
        profileImageUrl: this.profileImageUrl,
        showId: this.showId,
        title: this.title,
        description: this.description,
        posterUrl: this.posterUrl,
        price: this.price,
        runningTime: this.runningTime,
        timetables: this.timetables,
      }
      this.$store.dispatch('requestGetShowData', showData)
    },
    clickCancleTicketbutton(){
      this.$emit('clickCancleTicketbutton', this.timetableId)
    },
  },
}
</script>

<style>
.ticket {
  padding: 20px 10px;
}
.ticket-img {
  min-width: 350px;
  max-width: 350px;
  min-height: 156px;
  max-height: 156px;
  background-color: #242424;
  border: none;
  border-radius: .25rem;
  background-size: cover;
  padding: 10px;
  position: relative;
}
.ticket-img-str {
  background-image: url(~@/assets/ticket_without_content1.png);
}
.ticket-img-num {
  background-image: url(~@/assets/ticket_without_content2.png);
}
.ticket-small-header-line {
  display: flex;
  place-content: space-between;
}
.ticket-header.ticket-title {
  font-size: 20px;
  font-weight: bold;
  margin-left: 90px;
  width: 250px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: start;
}
.ticket-header.ticket-host {
  font-size: 15px;
  font-weight: bold;
  padding: 0px 20px 0px 0px;
  align-self: flex-end;
}
.ticket-btns-box {
  padding: 0 0;
  position: absolute;
  bottom: 10px;
  right: 10px;
}
.ticket-small-btn-line {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-left: 55px;
  margin-bottom: 10px;
  margin-right: 30px;
}
.ticket-small-btn {
  width: 60px;
  height: 25px;
  border-radius: 12.5px;
  font-size: 15px;
}
.ticket-small-btn-box {
  width: 70px;
  height: 30px;
}
.ticket-btn-line {
  display: flex;
  justify-content: flex-end;
}
.ticket-btn-box {
  /* width: 70px;
    height: 30px;
    border-radius: 15px;
    margin-left: 20px;
    font-size: 12px;
    text-align: center; */
}
.ticket-btn{
  width: 70px;
  height: 30px;
  border-radius: 15px;
  margin-left: 20px;
  /* margin-bottom: 10px; */
  font-size: 12px;
  text-align: center;
}
.ticket-popup-button {
  margin-top: 50px;
}
.ticket-popup-title {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  padding: 0 10%;
}
</style>