import router from '../router/index';

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
}