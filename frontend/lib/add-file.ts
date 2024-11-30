'use server';
import { spssAxios } from "./axios-config";

export default async function addFile(studentId: string, file: File) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('studentId', studentId);
    const response = await spssAxios.post('/document', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
        validateStatus: () => true
    });

    return response.status;
}