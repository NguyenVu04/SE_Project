'use server';

import { Report } from "@/app/spso/report/page";
import { spssAxios } from "./axios-config";

export default async function getAllReports() {
    const response = await spssAxios.get('/report/all', {
        validateStatus: () => true
    });

    return response.data as Report[];
}