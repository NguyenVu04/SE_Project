'use server';

import { spssAxios } from "./axios-config";

export default async function updateBalance(studentId: string, amount: number) {
    const response = await spssAxios.patch('/student/balance', null, {
        params: {
            id: studentId,
            amount: amount
        },
        validateStatus: () => true
    });

    return response.status;
}