<template>
  <div 
    class="modal fade" 
    id="ticketModal" 
    ref="ticketDialog" 
    tabindex="-1" 
    aria-labelledby="ticketModalLabel" 
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple ticket-modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button 
            type="button" 
            class="btn-close" 
            data-bs-dismiss="modal" 
            aria-label="Close"
          />
        </div>
        <div class="modal-body d-flex flex-column align-items-center ticket-box">
          <div
            v-for="(reservation, idx) in reservations"
            :key="idx"
          >
            <TicketCard
             :timetable="reservation.timetableFindByReservationRes"
             :idx="idx"
             @clickCancleReservationbutton="clickCancleReservationbutton"
            />
          </div> 
        </div>
        <div class="modal-footer-m"></div>
      </div>
    </div>
    <!--오프캔버스-->
    <div 
      class="offcanvas offcanvas-top m-offcanvas m-offcanvas-top bdcolor-nyellow" 
      tabindex="-1" 
      id="deleteTicketInfo" 
      ref="showPopup" 
      aria-labelledby="offcanvasTopLabel"
    >
      <div class="offcanvas-header">
        <button 
          type="button" 
          class="btn-close text-reset" 
          data-bs-dismiss="offcanvas" 
          aria-label="Close"
        >
        </button>
      </div>
      <div class="offcanvas-body">
        <div class="mt-3">
          <div>
            <p class="ticket-popup-title">
              예약을 정말로 삭제하시겠습니까?
            </p> 
          </div>
        </div>
        <div class="d-flex justify-content-end ticket-popup-button">
          <div>
            <button 
              type="button" 
              class="bdcolor-ngreen small-button mx-3"
              data-bs-dismiss="offcanvas"
            >
              취소
            </button>
          </div>
          <div>
            <button 
              type="button" 
              @click="clickConfirmbutton"
              class="bdcolor-npink small-button mx-3" 
              data-bs-dismiss="offcanvas"
            >
              확인
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--오프캔버스-->
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import TicketCard from '@/views/profile/components/TicketCard.vue'

export default {
  name: 'TicketDialog',
  data: function() {
    return {
      timetableIdToBeDeleted : '',
    }
  },
  components: {
    TicketCard,
  },
  methods: {
    clickCancleReservationbutton(timetableId) {
      console.log("티켓다이알로그에서 타임테이블 찍음")
      console.log(timetableId)
      this.timetableIdToBeDeleted = timetableId
      console.log("티켓다이알로그에서 디스삭제될타임테이블 찍음")
      console.log(this.timetableIdToBeDeleted)
    },
    clickConfirmbutton() {
      this.clickReservationDeleteConfirmButton(this.timetableIdToBeDeleted)
      this.timetableIdToBeDeleted = ''
    },
    clickReservationDeleteConfirmButton(timetableIdToBeDeleted) {
      console.log("티켓다이알로그에서 진짜 삭제할 타임테이블 아이디 찍음")
      console.log(timetableIdToBeDeleted)
        this.$store.dispatch('requestDeleteTicket', {timetableId : timetableIdToBeDeleted})
        .then(() => {
          this.$store.dispatch('requestGetLoginUser')
        })
        .catch((error) => {
          console.log('에러났는데여 ????????')
          console.log(error)
        })
    },
  },
  computed: {
    ...mapGetters(['reservations']),
  },
}
</script>

<style>
.ticket-modal-design {
  max-height: 700px;
  min-width: 500px;
  width: 70%;
  background-color: #242424;
  color: white;
}
.ticket-box {
  margin: 0;
}
.ticket-img-str {
  min-width: 350px;
  min-height: 160px;
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
  min-height: 160px;
  margin-left: 10px;
  margin-bottom: 20px;
  background-color: #242424;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/ticket_without_content2.png');
  background-size: cover;
}
</style>