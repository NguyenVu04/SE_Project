'use server';

import { StudentOrder } from "@/components/Order";
import { spssAxios } from "./axios-config";

export default async function getStudentOrders(studentId: string) {
    const response = await spssAxios.get('/order/all/student', {
        params: {
            id: studentId
        },
        validateStatus: () => true
    })

    return response.data as StudentOrder[];
}