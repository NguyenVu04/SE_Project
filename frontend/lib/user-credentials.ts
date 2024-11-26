'use server';

import { options } from "@/app/api/auth/[...nextauth]/authoptions";
import { getServerSession } from "next-auth";
import { redirect, RedirectType } from "next/navigation";
//TODO: get user credentials
export default async function getUserCredentials(role: string): Promise<string | never> {
    const session = await getServerSession(options);

    if (!session || !session.user || !session.user.email)
        return redirect("/signin", RedirectType.replace);

    switch (role) {
        case "student":
            return session.user.email;
        case "spso":

            return session.user.email;
        case "admin":

            return session.user.email;

        default:
            return redirect("/signin", RedirectType.replace);
    }
}