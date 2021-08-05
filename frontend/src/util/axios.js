import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api' // 이후 바뀌어야 될 값
const DEFAULT_CONTENT_TYPE = 'application/json'
const TOKEN_NAME = 'accessToken'
const ASTAR = '*'
const ACCESS_METHODS = 'GET,PUT,POST,PATCH,DELETE'

const instance = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': DEFAULT_CONTENT_TYPE,
        'Access-Control-Allow-Origin': ASTAR,
        'Access-Control-Allow-Headers': ASTAR,
        'Access-Control-Allow-Methods': ACCESS_METHODS,
        'Authorization': "",
    }
})

if (localStorage.getItem(TOKEN_NAME) != null && localStorage.getItem(TOKEN_NAME) != "") {
    instance.defaults.headers['Authorization'] = 'Bearer ' + localStorage.getItem(TOKEN_NAME)
}

instance.interceptors.response.use(
    function (response) {
        console.log("LOG: called intercepter in success...")
        return response
    },
    function (error) {
        console.log("LOG: called intercepter in error...")
        console.log(error)
        return Promise.reject(error)
    }
)

export default instance

