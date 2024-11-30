'use server';

import { spssAxios } from "./axios-config";

export default async function getAllPrinters() {
    const response = spssAxios.get('/printer/all');

    return response;
}