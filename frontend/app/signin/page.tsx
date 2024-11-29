'use client';
import { signIn } from "next-auth/react";
export default function SignInPage() {
    const signInWithGoogle = (role: string) => signIn("google",
        {
            redirect: true,
            callbackUrl: `/${role}`
        });

    return (
        <div className="flex flex-col w-full h-full fixed items-center justify-center" style={{
            background: 'linear-gradient(to bottom, #0381FF, #02067A)'
          }}>
            <button type="button" onClick={() => signInWithGoogle("student")}>Student Login</button>
            <button type="button" onClick={() => signInWithGoogle("spso")}>SPSO Login</button>
            <button type="button" onClick={() => signInWithGoogle("admin")}>Admin Login</button>
        </div>
    );
}