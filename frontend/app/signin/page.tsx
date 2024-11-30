'use client';
import { signIn } from "next-auth/react";
import { Button } from "../../components/ui/button";
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
            <h1 className="text-6xl font-bold text-orange-600">Welcome to the Printer Management System</h1>
            <p className="text-white text-xl">Choose your service</p>
            <Button onClick={() => signInWithGoogle("student")} className="mt-4 w-40 bg-orange-600 text-xl">Student Login</Button>
            <Button onClick={() => signInWithGoogle("spso")} className="mt-4 w-40 bg-orange-600 text-xl">SPSO Login</Button>
            <Button onClick={() => signInWithGoogle("admin")} className="mt-4 w-40 bg-orange-600 text-xl">Admin Login</Button>
        </div>
    );
}