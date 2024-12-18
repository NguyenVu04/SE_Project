'use server';
import { Printer } from "@/app/spso/printer/service/manage";
import { spssAxios } from "./axios-config";

export default async function getActivePrinter() {
    const response = await spssAxios.get('/printer/active', {
        validateStatus: () => true
    });
    return response.data as Printer[];
}