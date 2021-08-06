<template>
<div v-if="isLoading">
	<ov-video />
	<div><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import { mapGetters } from "vuex";
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	data() {
		return  {
			clientData: undefined,
			isLoading: false,
		}
	}, 
	
	watch: {
		mainStreamManager: function() {
			const { connection } = this.mainStreamManager.stream
			const { clientData } = JSON.parse(connection.data)
			this.clientData = clientData
			this.isLoading = true
		},
	},

	computed: {
		...mapGetters(['mainStreamManager']),
	},
};
</script>
