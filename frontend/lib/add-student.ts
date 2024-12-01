'use server';
import { spssAxios } from "./axios-config";

export default async function addStudent(form: FormData) {
    const response = await spssAxios.post('/admin/student', Object.fromEntries(form), {
        validateStatus: () => true
    });

    return response.status;
}