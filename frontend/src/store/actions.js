import $axios from '../util/axios'

export default {
    // login.vue
    requestGetKakaoLoginUrl() {
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

    requestGetUserByAccountEmail(context, payload) {
        const URL = `/auth/user/${payload.accountEmail}`
        return $axios.get(URL)
    },

    // RoomCreate.vue (민권)
    requestInitSession(context, payload) {
        context.commit("INIT_SESSION", payload)
    },

    requestGetSessionForOpenvidu(context) {
        const URL = '/video/openvidu/session'
        return $axios.get(URL)
    },

    requestGetTokenForOpenvidu(context, payload) {
        const URL = `/video/openvidu/token/${payload.sessionId}`
        return $axios.get(URL)
    },

    requestSetSessionIdAndTokenForOpenvidu(context, payload) {
        context.commit("SET_SESSION_ID_AND_TOKEN_FOR_OPENVIDU", payload)
    },

    requestSetAllDevices(context) {
        context.commit("SET_ALL_DEVICES")
    },

    requestAddEventInSession(context) {
        context.commit("ADD_EVENT_IN_SESSION")
    },

    requestConnectSession(context) {
        context.commit("CONNECT_SESSION")
    },
    
    requestConnectSessionForGuest(context) {
        context.commit("CONNECT_SESSION_FOR_GUEST")
    },

    requestSetmainStreamManager(context, payload) {
        context.commit("SET_MAIN_STREAM_MANAGER", payload)
    },

    requestSetSubscribe(context, payload) {
        context.commit("SET_SUBSCRIBE", payload)
    },

    requestChangeDevice(context, payload) {
        context.commit("CHANGE_DEVICE", payload)
    },

    requestSendChat(context, payload) {
        context.commit("SEND_CHAT", payload)
    },

    // RoomCreate.vue (희진)
    requestSetIsOpenSettingDialog({ commit }, payload) {
        commit('SET_IS_OPEN_SETTING_DIALOG', payload)
    },

    requestGetCategoryIds(context) {
        const URL = '/category/'
        return $axios.get(URL)
    },

    requestSetCreatedVideoData({ commit }, payload) {
        commit('SET_CREATEVIDEO_DATA', payload)
    },

    requestStartStreaming(context, payload) {
        const URL = '/video/insert'
        return $axios.post(URL, payload)
    },

    // RoomDetail.vue
    requestGetRoomDetail(context, payload) {
        const URL = `/video/${payload}`
        return $axios.get(URL)
    },

    requestCloseVideo(context, payload) {
        const URL = `/video/close/${payload}`
        return $axios.patch(URL)
    },

    // RoomSettingDialogForm.vue
    requestGetRecentlyTimeTable(context, payload) {
        const URL = `/showinfo/timetable/${payload.showInfoId}`
        console.log(URL)
        return $axios.get(URL)
    },

    // Main.vue
    requestGetCarouselVideos() {
        const URL = '/main/top'

        return $axios.get(URL)
    },

    requestGetFilterButtons() {
        const URL = '/category/'
        // 엔드슬래시 전부 제거한 후 pull받고 수정

        return $axios.get(URL)
    },

    requestGetTotalMainVideos(context, payload) {
        const URL = '/main/all'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetAdVideos(context, payload) {
        const URL = '/main/ad'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetShowVideos(context, payload) {
        const URL = '/main/show'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetTalkVideos(context, payload) {
        const URL = '/main/talk'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetLiveVideos(context, payload) {
        const URL = '/main/live'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetReplayVideos(context, payload) {
        const URL = '/main/replay'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },

    requestGetFollowVideos(context, payload) {
        const URL = '/main/follow'
        const PAGE_VALUE = payload.pageValue
        const SIZE_VALUE = payload.sizeValue

        return $axios.get(URL, { params: { page: PAGE_VALUE, size: SIZE_VALUE }})
    },
    
    // MainSidebar.vue
    requestGetFollowingList() {
        const URL = '/main/user'

        return $axios.get(URL)
    },

    // Profile.vue
    requestGetMyProfile() {
        const URL = '/auth/user'

        return $axios.get(URL)
    },
}
