<template>
	<video class="user_video" autoplay/>
</template>

<script>
import { mapGetters } from "vuex";

export default {
	name: 'OvVideo',

	mounted () {
		this.mainStreamManager.addVideoElement(this.$el);
	},
	
	computed: {
		...mapGetters(['mainStreamManager']),
	},
};
</script>

<style scoped>
.user_video {
	object-fit: cover;
}
</style>