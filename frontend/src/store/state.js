export default {
    
    loginUser: undefined,
    isLogin: false,
    isSettingDialogOpen: false,
    settingDialogViewId: 0,
    // in openvidu variable
    ovSessionId: '',
    ovToken: '',
    ovSession: undefined,
    OV: undefined,
    audioDevices: [],
    videoDevices: [],
    audioDeviceId: 0,
    videoDeviceId: 0,
    mainStreamManager: undefined,
    publisher: undefined,
    subscribers: [],
    RESOLUTION: '1024x576',
    MAX_CHAT_LIST_SIZE: 20,
    createdVideoData: {
      categoryId: '1',
      thumbnailImage: [], 
      videoDescription: '',
      videoTitle: '',
      showInfoId: '',
      showTime:'',
      mode: '공연',
    },
    createdProfileData: {},
    getShowData: {},
    onCreateVideoLive: false,
    videoId: '',
    showReservationData: {
        runningTime: '',
        posterUrl: '',
        price: '',
        showInfoDescription: '',
        showInfoId: '',
        showInfoTitle: '',
        userRes: {
          accountEmail:'',
          userName: '',
          profileImageUrl:''
        }
      },
    fileNamevuex: '',
}