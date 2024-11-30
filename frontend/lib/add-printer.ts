'use server';

import { redirect, RedirectType } from "next/navigation";
import { spssAxios } from "./axios-config";

export default async function createPrinter(form: FormData) {
    const response = await spssAxios.post('spso/printer',
        Object.fromEntries(form));

    if (response.status !== 200) {
        redirect(`/spso/printer?error=${response.status}`, RedirectType.replace);
    }
    redirect('/spso/printer');
}