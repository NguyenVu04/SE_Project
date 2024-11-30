'use server';
import { HistoryItem } from "@/app/history/page";
import { spssAxios } from "./axios-config";

export default async function getStudentHistory(studentId: string) {
    const response = await spssAxios.get('history/printing/student', {
        params: {
            studentId: studentId
        },
        validateStatus: () => true
    })

    return response.data as HistoryItem[]
}