<template>
  <div>
  <div class="modal fade" id="showReservationInProfileModal" tabindex="-1" aria-labelledby="showReservationInProfileModalLabel" aria-hidden="true">
    <div class="modal-dialog bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        예약
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'ShowReservationDialogInProfile',
  data: function() {
    return {
      showId: '',
    }
  },
  created: function () {
    this.getShow()
  },
  methods: {
    getShow() {
      this.showId = this.loginUser.accountEmail
    },
  },
  computed: {
    ...mapGetters(['loginUser',]),
  },
}
</script>

<style>

</style>