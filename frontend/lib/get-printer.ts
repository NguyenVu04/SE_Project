'use server';

import { Printer } from "@/app/spso/printer/service/manage";
import { spssAxios } from "./axios-config";

export default async function getAllPrinters() {
    const response = await spssAxios.get('/printer/all');
    return response.data as Printer[];
}