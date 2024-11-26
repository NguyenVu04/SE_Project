import axios from "axios";

export const spssAxios = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 4000
})