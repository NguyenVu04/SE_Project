'use server';
import { Config } from "@/app/spso/policy/page";
import { spssAxios } from "./axios-config";

export default async function getConfig() {
    const response = await spssAxios.get('/config');
    return response.data as Config;
}