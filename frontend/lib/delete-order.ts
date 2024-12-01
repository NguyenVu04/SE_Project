'use server';

import { spssAxios } from "./axios-config";

export default async function deleteOrder(id: string) {
    const response = await spssAxios.delete('/order', {
        params: {
            id: id
        },
        validateStatus: () => true
    })

    return response.status;
}