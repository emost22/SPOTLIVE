<template>
  <div class="modal fade" id="ticketModal" tabindex="-1" aria-labelledby="ticketModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">

        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
          
        <div class="modal-body d-flex flex-column align-items-center ticket-box">
          <div class="ticket-img-num"> 예매내역1 </div>
          <div class="ticket-img-str"> 예매내역2 </div>
          <div class="ticket-img-num"> 예매내역3 </div>
          <div class="ticket-img-str"> 예매내역4 </div>
          <!-- div태그 안에 백그라운드 이미지로 assets 폴더 티켓 이미지 넣어보기 -->
          <!-- 모든 나의 예약 내역 vuex 밑에서 불러와서 공연 삭제 및 예약 상세 다이얼로그 이동 버튼 추가 -->
        </div>

        <div class="modal-footer-m"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ProfileUdateDialog',
  data: function() {
    return {
      userId: '',
      rservation_list: [],
    }
  },
  created: function () {
    this.getReservation()
  },
  methods: {
    getReservation() {
      this.userId = this.loginUser.accountEmail
      this.rservation_list = this.loginUser.reservationResList
      // 예매내역을 위에서 for문 순회하면서 티켓에 삽입하기
    },
    clickReservationDeleteButton() {
      // 예약 삭제 axios actions.js에서 요청
    },
    clickShowDetailButton() {
      // 예약 공연 상세 정보 조회 다이얼로그(ShowReservationDialog.vue) 
      // 티켓 다이얼로그 닫고 공연 상세 정보 조회 다이얼로그 오픈
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