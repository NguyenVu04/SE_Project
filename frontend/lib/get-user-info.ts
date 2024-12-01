'use server';
import { SPSO, Student } from "@/app/admin/page";
import { spssAxios } from "./axios-config";

export default async function getUserInfo(
    id: string,
    role: "student" | "spso" | "admin") {

    switch (role) {
        case "student":
            const student = await spssAxios.get('/student', {
                params: {
                    id: id
                },
                validateStatus: () => true
            });

            if (student.status !== 200) {
                return null;
            }

            return student.data as Student;
        case "spso":
            const spso = await spssAxios.get('/spso', {
                params: {
                    id: id
                },
                validateStatus: () => true
            });

            if (spso.status !== 200) {
                return null;
            }

            return spso.data as SPSO;
        case "admin":
            const admin = await spssAxios.get('/admin', {
                params: {
                    id: id
                },
                validateStatus: () => true
            });

            if (admin.status !== 200) {
                return null;
            }

            return admin.data;
    }
}