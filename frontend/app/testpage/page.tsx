'use server';

import { options } from "@/lib/AuthOptions";
import { getServerSession } from "next-auth";

export default async function TestPage() {
    const user = await getServerSession(options);

    return (
        <div>
            <h1>{user?.user?.email}</h1>
        </div>
    );
}