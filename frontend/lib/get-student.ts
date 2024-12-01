'use server';
import { Student } from "@/app/admin/page";
import { spssAxios } from "./axios-config";

export default async function getStudent(studentId: string) {
    const response = await spssAxios.get('/student', {
        params: {
            id: studentId
        },
        validateStatus: () => true
    });
    if (response.status !== 200) {
        return null;
    }
    return response.data as Student;
}