<template>
  <div class="modal fade" id="MyReservationModal" tabindex="-1" aria-labelledby="MyReservationModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable bdcolor-bold-npurple modal-design">
      <div class="modal-content-m">
        <div class="modal-header no-border">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body mx-3">
        <div class="modal-footer-m">
          <div><button type="button" class="bdcolor-ngreen small-button mx-3">취소</button></div>
          <div><button type="button" class="bdcolor-npink small-button mx-3">등록</button></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyReservationDialog',
  components: {
  }
}
</script>

<style>

</style>