import axios from "axios";

export const spssAxios = axios.create({
    baseURL: process.env.BACKEND_URL || "http://localhost:8080",
    timeout: 4000
})