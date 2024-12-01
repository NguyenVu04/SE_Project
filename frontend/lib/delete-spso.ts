'use server';

import { spssAxios } from "./axios-config";

export default async function deleteSpso(id: string) {
    const response = await spssAxios.delete('/admin/spso', {
        params: {
            id: id
        },
        validateStatus: () => true
    });

    return response.status
}