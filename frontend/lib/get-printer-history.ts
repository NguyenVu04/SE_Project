'use server';

import { HistoryItem } from "@/app/history/page";
import { spssAxios } from "./axios-config";

export default async function getPrinterHistory(printerId: string, from: string, to: string) {
    const response = await spssAxios.get('history/printing/printer', {
        params: {
            printerId: printerId,
            from: from,
            to: to
        },
        validateStatus: () => true
    })

    return response.data as HistoryItem[];
}