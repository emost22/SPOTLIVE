<template>
  <div>
    <!-- 예약 팝업 -->
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