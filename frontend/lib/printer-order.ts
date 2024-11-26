'use server';

import { spssAxios } from "./axios-config";
export type Order = {
    orderId: string;
    documentUrl: string;
    paperSize: string;
    pageNumbers: number[];
    numberOfCopies: number;
    singleSide: boolean;
}

export default async function getPrinterOrder(printerId: string): Promise<Order[]> {
    const response = await spssAxios.get(`/printer/orders`, {
        params: {
            printerId: printerId
        }
    });

    return response.data as Order[];
}