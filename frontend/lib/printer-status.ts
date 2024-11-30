'use server';

import { spssAxios } from "./axios-config";

export default async function setPrinterStatus(active: boolean, printerId: string) {
    const response = await spssAxios.patch('/spso/printer', null, {
        params: {
            status: active ? "active" : "inactive",
            id: printerId
        }
    });

    if (response.status !== 200) {
        return false;
    }

    return true;
}