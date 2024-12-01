'use server';

import { spssAxios } from "./axios-config";

export default async function addSpso(form: FormData) {
    const response = await spssAxios.post('/admin/spso', Object.fromEntries(form), {
        validateStatus: () => true
    });
    return response.status;
}