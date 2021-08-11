<template>
  <div>
    <div class="ticket-img-num" v-if="open">
      <div class="ticket-title">{{this.reservation.timetableFindByReservationRes.showInfoRes.showInfoTitle}}</div>
      <div class="ticket-small-btn-line">
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{date}}</button></div>
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{time}}</button></div>
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{runningTime}}분</button></div>
      </div>
      <div class="ticket-btn-line">
        <div
          class="ticket-btn-box" 
          data-bs-toggle="modal" 
          data-bs-target="#showReservationInProfileModal"
        >
          <button @click="clickShowReservationInProfileButton" class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-ngreen">예약 상세</button>
        </div>
        <div class="ticket-btn-box"><button @click="clickReservationDeleteButton" class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-npurple">예약 취소</button></div>
      </div>
    </div>
    <div class="ticket-img-str" v-if="!open">
      <div class="ticket-title">{{this.reservation.timetableFindByReservationRes.showInfoRes.showInfoTitle}}</div>
      <div class="ticket-small-btn-line">
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{date}}</button></div>
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{time}}</button></div>
        <div class="ticket-small-btn-box"><button class="ticket-small-btn main-bgcolor-black txtcolor-white bdcolor-npink">{{runningTime}}분</button></div>
      </div>
      <div class="ticket-btn-line">
        <div 
          class="ticket-btn-box" 
          data-bs-toggle="modal" 
          data-bs-target="#showReservationInProfileModal"
        >
          <button @click="clickShowReservationInProfileButton" class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-ngreen">예약 상세</button>
        </div>
        <div class="ticket-btn-box">
          <button @click="clickReservationDeleteButton" class="ticket-btn main-bgcolor-black txtcolor-white bdcolor-npurple">예약 취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
export default {
  
  name: 'TicketCard',
  props: {
    reservation: {
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
      showId : '',
      title: '',
      description: '',
      posterUrl: '',
      price: '',
      runningTime : '',
      date : '',
      time : '',
      timetableId : '',
    }
  },
  created() {
    this.getUser()
    this.getTicketImg()
    this.getReservation()
  },
  watch: {
    reservation(val, oldVal) {
      this.getUser()
      this.getTicketImg()
      this.getReservation()
    }
  },
  methods: {
    getUser() {
      this.userId = this.loginUser.accountEmail
    },
    getTicketImg() {
      if (this.idx%2) {
        this.open = true
      } else {
        this.open = false
      }
    },
    getReservation() {
      this.showId = this.reservation.timetableFindByReservationRes.showInfoRes.showInfoId
      this.title = this.reservation.timetableFindByReservationRes.showInfoRes.showInfoTitle
      this.description  = this.reservation.timetableFindByReservationRes.showInfoRes.showInfoDescription
      this.posterUrl  = this.reservation.timetableFindByReservationRes.showInfoRes.posterUrl
      this.price  = this.reservation.timetableFindByReservationRes.showInfoRes.price
      this.runningTime = this.reservation.timetableFindByReservationRes.showInfoRes.runningTime
      var day = this.reservation.timetableFindByReservationRes.dateTime.substr(0,10).split(" ")[0]
      var tmpDay = day.split("-")
      this.date =  tmpDay[1] + "/" + tmpDay[2]
      this.time = this.reservation.timetableFindByReservationRes.dateTime.substring(11,16)
      this.timetableId = this.reservation.timetableFindByReservationRes.timetableId
    },
    clickReservationDeleteButton() {
      this.$store.dispatch('requestDeleteTicket', {timetableId : this.timetableId})
    },
    clickShowReservationInProfileButton() {
      var showData = {
        showId: this.showId,
        title: this.title,
        description: this.description,
        posterUrl: this.posterUrl,
        price: this.price,
        runningTime: this.runningTime,
        date: this.date,
        time: this.time,
      }
      this.$store.dispatch('requestGetShowData', showData)
      this.closeTicketDialog()
    },
    closeTicketDialog() {
      this.$emit('closeTicketDialog')
    },
  },
  computed: {
    ...mapGetters(['loginUser',]),
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
</style>