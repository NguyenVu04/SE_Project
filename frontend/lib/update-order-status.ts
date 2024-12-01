'use server';

import { spssAxios } from "./axios-config";

export default async function updateOrderStatus(
    orderId: string,
    successful: boolean,
    timeReceived: string,
    timePrinted: string) {

    const response = await spssAxios.post('/printer/message', {
        orderId: orderId,
        successful: successful,
        timeReceived: timeReceived,
        timePrinted: timePrinted
    }, {
        validateStatus: () => true
    })

    return response.status;
}