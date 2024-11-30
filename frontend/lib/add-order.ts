'use server';
import { OrderInfo } from "@/app/student/configdoc/page";
import { spssAxios } from "./axios-config";

export default async function addOrder(
    studentId: string,
    printerId: string,
    order: OrderInfo) {

    const response = await spssAxios.post('/order/student', {
        studentId: studentId,
        printerId: printerId,
        ...order
    }, {
        validateStatus: () => true
    })

    return response.status;
}