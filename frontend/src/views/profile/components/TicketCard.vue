<!--
  작성자 : 권영린
  컴포넌트 설명 : TicketDialog에서 받아온 예약정보를 하나의 티켓으로 출력
-->
<template>
  <div>
    <div :class="className">
      <div class="ticket-title">
        {{title}}
      </div>
      <div class="ticket-small-btn-line">
        <div class="ticket-small-btn-box">
          <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
            {{date}}
          </button>
        </div>
        <div class="ticket-small-btn-box">
          <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
            {{time}}
          </button>
        </div>
        <div class="ticket-small-btn-box">
          <button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">
            {{runningTime}}분
          </button>
        </div>
      </div>
      <div class="ticket-btn-line">
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
.ticket-box {
  margin: 0;
}
.ticket-img-str {
  min-width: 350px;
  max-width: 350px;
  min-height: 160px;
  max-height: 160px;
  margin-left: 10px;
  margin-bottom: 20px;
  background-color: #242424;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/ticket_without_content1.png');
  background-size: cover;
}
.ticket-img-num {  
  min-width: 350px;
  max-width: 350px;
  min-height: 160px;
  max-height: 160px;
  margin-left: 10px;
  margin-bottom: 20px;
  background-color: #242424;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/ticket_without_content2.png');
  background-size: cover;
}
.ticket-btn {
  width: 100px;
  height: 30px;
  border-radius: 15px;
  margin-left: 20px;
  margin-bottom: 10px;
  font-size: 15px;
  text-align: center;
}
.ticket-btn-box {
  width: 110px;
  height: 30px;
}
.ticket-btn-line {
  display: flex;
  flex-direction: row-reverse;
  justify-content: end;
  margin-right: 20px;
  margin-top: 35px;
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
.ticket-small-btn-line {
  display: flex;
  flex-direction: row;
  justify-content: start;
  margin-left: 90px;
  margin-top: 10px;
}
.ticket-title {
  font-size: 20px;
  font-weight: bold;
  text-align: start;
  margin-left: 100px;
  padding-top: 10px;
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