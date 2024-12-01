'use server';

import { Config } from "@/app/spso/policy/page";
import { spssAxios } from "./axios-config";

export default async function addConfig(config: Config) {
    const response = await spssAxios.post('/config', config);
    return response.status;
}