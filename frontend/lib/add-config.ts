'user server';

import { Config } from "@/app/spso/policy/page";
import { spssAxios } from "./axios-config";

export default async function addConfig(config: Config) {

    return await spssAxios.post('/config', {
        defaultNumberOfPages: config.defaultNumberOfPages,
        paperSupplyDay: config.paperSupplyDay,
        createdBy: config.createdBy,
        fileTypes: config.fileTypes
    })
}