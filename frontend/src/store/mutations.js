import { Publisher } from 'openvidu-browser'
import router from '../router/index'
import $axios from '../util/axios'

export default {

    DO_KAKAO_LOGIN(state, payload) {
        console.log("MUTATION: DO_KAKAO_LOGIN() RUN...")
        console.log(payload)

        state.loginUser = payload.loginUser
        state.isLogin = true
        localStorage.setItem('loginUser', JSON.stringify(state.loginUser))
        localStorage.setItem('accessToken', payload.loginUser.accessToken)
        
        $axios.defaults.headers['Authorization'] = 'Bearer ' + payload.loginUser.accessToken
        router.push({ name: "Main" })
    },

    INIT_SESSION(state, payload) {
        console.log("MUTATION: INIT_SESSION() RUN...")
        state.OV = payload
        state.ovSession = state.OV.initSession()
    },

    LOGOUT(state, payload) {
        console.log("MUTATION: LOGOUT() RUN...")
        state.isLogin = false
        localStorage.removeItem('loginUser')
        localStorage.removeItem('accessToken')

        $axios.defaults.headers['Authorization'] = ''
        router.push({ name: "Login" })
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

        state.ovSession.on('streamDestroyed', ({ stream }) => {
            console.log('[OPENVIDU] Stream Destroyed!')
            const index = state.subscribers.indexOf(stream.streamManager, 0)
            if (index >= 0) {
                state.subscribers.splice(index, 1)
            }
        })

        state.ovSession.on('exception', ({ exception }) => {
            console.log('[OPENVIDU] exception!')
            console.warn(exception)
        })
    },

    SET_MAIN_STREAM_MANAGER(state, payload) {
        console.log("MUTATION: SET_MAIN_STREAM_MANAGER() RUN...")
        state.mainStreamManager = state.ovSession.subscribe(payload.stream, undefined)
    },

    SET_SUBSCRIBE(state, payload) {
        console.log("MUTATION: SET_SUBSCRIBE() RUN...")
        state.ovSession.subscribe(payload.stream, undefined)
    },
    
    CONNECT_SESSION(state) {
        console.log("MUTATION: CONNECT_SESSION() RUN...")
        console.log("OV TOKEN: " + state.ovToken)
        state.ovSession.connect(state.ovToken, { clientData: state.loginUser.accountEmail })
        .then((response) => {
            let publisher = state.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: state.RESOLUTION,  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: false       	// Whether to mirror your local video or not
            })
            state.mainStreamManager = publisher
            state.publisher = publisher
            state.ovSession.publish(state.publisher)
        }).catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message)
        })
    },

    CONNECT_SESSION_FOR_GUEST(state) {
        console.log("MUTATION: CONNECT_SESSION_FOR_GUEST() RUN...")
        console.log("OV TOKEN: " + state.ovToken)
        console.log("ACCOUNT_EMAIL: " + state.loginUser.accountEmail)
        state.ovSession.connect(state.ovToken, { clientData: "example@example.com" })
        .then((response) => {
            console.log("CONNECT_SESSION_FOR_GUEST() SUCCESS!")
            console.log("MUTATION: SEND_JOIN() RUN...")
            console.log(state.loginUser.accountEmail)
            state.ovSession.signal({
                data: state.loginUser.accountEmail,
                to: [],                     
                type: 'join-video'
            }).then(() => {
                console.log('JOIN SIGNAL successfully sent');
            }).catch(error => {
                console.error(error);
            })
        }).catch((error) => {
            console.log("CONNECT_SESSION_FOR_GUEST() FAIL!")
            console.log('There was an error connecting to the session:', error.code, error.message)
        })
    },

    CHANGE_DEVICE(state, payload) {
        console.log("MUTATION: CHANGE_DEVICE() RUN...")
        state.audioDeviceId = payload.audioDeviceId
        state.videoDeviceId = payload.videoDeviceId
        console.log("* ID: " + payload.audioDeviceId)
        console.log("* ID: " + payload.videoDeviceId)
        console.log("* ID: " + state.audioDeviceId)
        console.log("* ID: " + state.videoDeviceId)

        let newPublisher = state.OV.initPublisher(undefined, {
			audioSource: state.audioDevices[state.audioDeviceId].deviceId, // The source of audio. If undefined default microphone
			videoSource: state.videoDevices[state.videoDeviceId].deviceId, // The source of video. If undefined default webcam
			publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
			publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
			resolution: state.RESOLUTION,  // The resolution of your video
			frameRate: 30,			// The frame rate of your video
			insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
			mirror: false       	// Whether to mirror your local video or not
		})

        state.ovSession.unpublish(state.publisher).then(() => {
            console.log('Old publisher unpublished!')
            state.publisher = newPublisher
            state.mainStreamManager = state.publisher
            state.ovSession.publish(state.publisher).then(() => {
                console.log('New publisher published!')
            })
        })
        console.log("MUTATION: CHANGE_DEVICE() DONE...")
    },

    LEAVE_SESSION(state, payload) {
        console.log("MUTATION: LEAVE_SESSION() RUN...")
        
        state.ovSession.disconnect()

        state.mainStreamManager = undefined
        state.publisher = undefined
        state.subscribers = []
        state.OV = undefined
        state.ovSession = undefined
        state.ovSessionId = ''
        state.ovToken = ''
    },

    SEND_CHAT(state, payload) {
        console.log("MUTATION: SEND_CHAT() RUN...")
        console.log(payload.chatMsg)
        state.ovSession.signal({
            data: payload.chatMsg,
            to: [],                     
            type: 'my-chat'             
        }).then(() => {
            console.log('Message successfully sent');
        }).catch(error => {
            console.error(error);
        })
    },
    
    SEND_EXIT(state) {
        console.log("MUTATION: SEND_EXIT() RUN...")
        console.log(state.loginUser.accountEmail)
        state.ovSession.signal({
            data: state.loginUser.accountEmail,
            to: [],                     
            type: 'exit-video'
        }).then(() => {
            console.log('EXIT SIGNAL successfully sent');
        }).catch(error => {
            console.error(error);
        })
    },
    
    SET_IS_OPEN_SETTING_DIALOG(state, payload) {
        state.isSettingDialogOpen = !state.isSettingDialogOpen
        console.log(!state.isSettingDialogOpen)
        state.settingDialogViewId = payload
    },

    SET_CREATEVIDEO_DATA (state, payload) {
        state.createdVideoData = payload
    }
}
