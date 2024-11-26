'use client';

import { useSession } from "next-auth/react";
import { redirect } from "next/navigation";

export default function TestPage() {
    const user = useSession({
        required: true,
        onUnauthenticated: () => {
            redirect("/api/auth/signin?callbackUrl=/testpage");
        }
    });

    return (
        <div>
            <h1>{user.data?.user?.email}</h1>
        </div>
    );
}