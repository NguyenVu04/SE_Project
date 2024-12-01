'use server';

import { Student } from "@/app/admin/page";
import { spssAxios } from "./axios-config";

export default async function getAllStudents() {
    const response = await spssAxios.get('/admin/students', {
        validateStatus: () => true
    });

    if (response.status !== 200) {
        return null;
    }

    return response.data as Student[];
}
