import axios from '../util/axios'

export default {

    requestGetKakaoLoginUrl(context, payload) {
        const URL = '/auth/kakao/showlogin'
        return axios.get(URL)
    },

    requestDoKakaoLogin(context, payload) {
        const URL = '/auth/kakao/login'
        axios.get(URL + "?code=" + payload)
        .then((response) => {
            context.commit("DO_KAKAO_LOGIN", {
                loginUser: response.data,
            })
        }).catch((error) => {
            console.log(error)
        })
    },
}