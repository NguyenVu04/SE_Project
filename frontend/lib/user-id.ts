'use server';

import { options } from "@/app/api/auth/[...nextauth]/authoptions";
import { getServerSession } from "next-auth";
import { redirect, RedirectType } from "next/navigation";
import { spssAxios } from "./axios-config";

export default async function getUserId(role: string): Promise<string | never> {
    const session = await getServerSession(options);

    if (!session || !session.user || !session.user.email)
        return redirect("/signin", RedirectType.replace);

    const email = session.user.email;

    switch (role) {
        case "student":
            
            const studentResponse = await spssAxios.get('/student/id', {
                params: {
                    email: email
                },
                headers: {
                    "Content-Type": "application/json",
                }
            });
            if (studentResponse.status !== 200 || !studentResponse.data.id)
                return redirect("/signin?error=404", RedirectType.replace);
            
            return studentResponse.data.id;

        case "spso":

                const spsoResponse = await spssAxios.get('/spso/id', {
                    params: {
                        email: email
                    },
                    headers: {
                        "Content-Type": "application/json",
                    }
                });
                if (spsoResponse.status !== 200 || !spsoResponse.data.id)
                    return redirect("/signin?error=404", RedirectType.replace);
                
                return spsoResponse.data.id;

        case "admin":

                const adminResponse = await spssAxios.get('/admin', {
                    params: {
                        email: email
                    },
                    headers: {
                        "Content-Type": "application/json",
                    }
                });
                if (adminResponse.status !== 200)
                    return redirect("/signin?error=404", RedirectType.replace);
                
                return email;

        default:
            return redirect("/signin?error=400", RedirectType.replace);
    }
}