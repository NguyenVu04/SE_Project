'use server';
import { spssAxios } from "./axios-config";

export default async function getDocuments(studentId: string) {
    const response = await spssAxios.get('/document/all', {
        params: {
            studentId: studentId
        }
    });
    return response.data;
}