import $axios from '../util/axios'

export default {

    requestGetKakaoLoginUrl(context, payload) {
        const URL = '/auth/kakao/showlogin'
        return $axios.get(URL)
    },

    requestDoKakaoLogin(context, payload) {
        const URL = '/auth/kakao/login'

        $axios.get(URL + "?code=" + payload)
        .then((response) => {
            context.commit("DO_KAKAO_LOGIN", {
                loginUser: response.data,
            })
        }).catch((error) => {
            console.log(error)
        })
    },

    requestGetTalkVideos(context, payload) {
        const URL = '/main/talk'
        const PAGE_VALUE = payload.pageValue;
        const SIZE_VALUE = payload.sizeValue;

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetShowVideos(context, payload) {
        const URL = '/main/show'
        const PAGE_VALUE = payload.pageValue;
        const SIZE_VALUE = payload.sizeValue;

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetReplayVideos(context, payload) {
        const URL = '/main/replay'
        const PAGE_VALUE = payload.pageValue;
        const SIZE_VALUE = payload.sizeValue;

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetLiveVideos(context, payload) {
        const URL = '/main/live'
        const PAGE_VALUE = payload.pageValue;
        const SIZE_VALUE = payload.sizeValue;

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },
}