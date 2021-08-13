<!--
  작성자 : 권영린
  컴포넌트 설명 : TicketDialog에서 받아온 예약정보를 하나의 티켓으로 출력
-->
<template>
  <div>
    <!---------------------------짝수번째 티켓일 때 ----------------------------->
    <div class="ticket-img-num" v-if="open">
      <div class="ticket-title">
        {{this.timetable.showInfoRes.showInfoTitle}}
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
            @click="clickShowReservationInProfileButton" 
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
            @click="clickCancleReservationbutton"
          >
            예약 취소
          </button>
        </div>
      </div>
    </div>
    <!---------------------------홀수번째 티켓일 때 ----------------------------->
    <div class="ticket-img-str" v-if="!open">
      <div class="ticket-title">
        {{this.timetable.showInfoRes.showInfoTitle}}
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
            @click="clickShowReservationInProfileButton" 
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
            @click="clickCancleReservationbutton"
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
    idx: {
      type: Number,
      required: true
    },
  },
  data: function() {
    return {
      open: false,
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
  created() {
    this.getUser()
    this.getTicketImg()
    this.getReservation()
  },
  watch: {
    timetable : function(val, oldval){
      this.getUser()
      this.getTicketImg()
      this.getReservation()
    }
  },
  methods: {
    getUser() {
      this.userId = this.timetable.showInfoRes.userRes.accountEmail
      this.profileNickname = this.timetable.showInfoRes.userRes.profileNickname
      this.profileImageUrl = this.timetable.showInfoRes.userRes.profileImageUrl
    },
    getTicketImg() {
      if (this.idx%2) {
        this.open = true
      } else {
        this.open = false
      }
    },
    getReservation() {
      this.showId = this.timetable.showInfoRes.showInfoId
      this.title = this.timetable.showInfoRes.showInfoTitle
      this.description  = this.timetable.showInfoRes.showInfoDescription
      this.posterUrl  = this.timetable.showInfoRes.posterUrl
      this.price  = this.timetable.showInfoRes.price
      this.runningTime = this.timetable.showInfoRes.runningTime
      var day = this.timetable.dateTime.substr(0,10).split(" ")[0]
      var tmpDay = day.split("-")
      this.date =  tmpDay[1] + "/" + tmpDay[2]
      this.time = this.timetable.dateTime.substring(11,16)
      this.timetableId = this.timetable.timetableId
      this.dateTime = this.timetable.dateTime
      this.timetables = []
      this.timetables.push({dateTime: this.dateTime, timetableId : this.timetableId})
    },
    clickShowReservationInProfileButton() {
      var showData = {
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
    clickCancleReservationbutton(){
      console.log("티켓카드뷰에서 타임테이블 찍음")
      console.log(this.timetableId)
      this.$emit('clickCancleReservationbutton', this.timetableId)
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