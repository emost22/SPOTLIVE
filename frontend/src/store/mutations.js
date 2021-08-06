import router from '../router/index'
import $axios from '../util/axios'

export default {

    DO_KAKAO_LOGIN(state, payload) {
        console.log("MUTATION: DO_KAKAO_LOGIN() RUN...")
        console.log(payload)
        state.loginUser = payload.loginUser
        state.isLogin = true;
        localStorage.setItem('accessToken', payload.loginUser.accessToken)
        $axios.defaults.headers['Authorization'] = 'Bearer ' + payload.loginUser.accessToken

        router.push({ name: "Main" })
    },

    INIT_SESSION(state, payload) {
        console.log("MUTATION: INIT_SESSION() RUN...")
        state.OV = payload;
        state.ovSession = state.OV.initSession()
    },

    SET_SESSION_ID_AND_TOKEN_FOR_OPENVIDU(state, payload) {
        console.log("MUTATION: SET_SESSION_ID_AND_TOKEN_FOR_OPENVIDU() RUN...")
        state.ovSessionId = payload.ovSessionId
        state.ovToken = payload.ovToken
        console.log("OV SESSION ID: " + state.ovSessionId)
        console.log("OV TOKEN: " + state.ovToken)
    },

    SET_ALL_DEVICES(state) {
        console.log("MUTATION: SET_ALL_DEVICES() RUN...")
        state.OV.getDevices().then(devices => {
            state.videoDevices = devices.filter(device => device.kind === 'videoinput')
            state.audioDevices = devices.filter(device => device.kind === 'audioinput')
        })
    },
}