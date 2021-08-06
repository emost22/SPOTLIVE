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

    ADD_EVENT_IN_SESSION(state) {
        console.log("MUTATION: ADD_EVENT_IN_SESSION() RUN...")
        state.ovSession.on('connectionCreated', event => {
            console.log('[OPENVIDU] Found Connection: ', { event })
        })

        state.ovSession.on('streamCreated', ({ stream }) => {
            let subscriber = state.session.subscribe(stream, undefined);
            console.log('[OPENVIDU] Add new user: ' + subscriber.id)
            console.log(subscriber)
            state.mainStreamManager = subscriber
            state.subscribers.push(subscriber)
        })

        state.ovSession.on('streamDestroyed', ({ stream }) => {
            console.log('[OPENVIDU] Stream Destroyed!')
            const index = state.subscribers.indexOf(stream.streamManager, 0);
            if (index >= 0) {
                state.subscribers.splice(index, 1);
            }
        })

        state.ovSession.on('exception', ({ exception }) => {
            console.log('[OPENVIDU] exception!')
            console.warn(exception);
        })

        // state.ovSession.on('signal:my-chat', (event) => {
        //     console.log('[OPENVIDU] Get Chat data: ' + event.data)
        //     let userId = JSON.parse(event.from.data).clientData
        //     state.chatArray.push(userId + ": " + event.data)
        // })
    },

    CONNECT_SESSION(state) {
        console.log("MUTATION: CONNECT_SESSION() RUN...")
        console.log("OV TOKEN: " + state.ovToken)
        state.ovSession.connect(state.ovToken, { clientData: 'kmk130519@naver.com' })
        .then((response) => {
            let publisher = state.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: '960x540',  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: false       	// Whether to mirror your local video or not
            })
            state.mainStreamManager = publisher
            state.publisher = publisher
            state.ovSession.publish(state.publisher)
        }).catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message);
        })
    }
}