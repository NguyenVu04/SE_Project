'use server';

import { SPSO } from "@/app/admin/page";
import { spssAxios } from "./axios-config";

export default async function getAllSPSOs() {
    const response = await spssAxios.get('/admin/spsos', {
        validateStatus: () => true
    });

    if (response.status !== 200) {
        return null;
    }

    return response.data as SPSO[];
}