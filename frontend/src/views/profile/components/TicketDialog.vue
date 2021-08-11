<template>
  <div class="modal fade" id="ticketModal" tabindex="-1" aria-labelledby="ticketModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body d-flex flex-column align-items-center ticket-box">
          <div
            v-for="(reservation, idx) in reservations"
            :key="idx"
          >
            <TicketCard
             :reservation="reservation"
             :idx="idx"
            />
          </div> 
        </div>
        <div class="modal-footer-m"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import TicketCard from '@/views/profile/components/TicketCard.vue'

export default {
  name: 'TicketDialog',
  data: function() {
    return {
      userId: '',
      reservations: [],
    }
  },
  components: {
    TicketCard,
  },
  created: function () {
    this.getReservation()
    console.log(this.reservations)
  },
  methods: {
    getReservation() {
      this.userId = this.loginUser.accountEmail
      this.reservations = this.loginUser.reservationResList
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