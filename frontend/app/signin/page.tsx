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
            <h1 className="text-6xl font-bold text-slate-200 text-center uppercase">chào mừng đến với hệ thống in thông minh</h1>
            <Button onClick={() => signInWithGoogle("student")} className="mt-4 w-80 bg-green-700 text-xl p-4">Sinh viên đăng nhập</Button>
            <Button onClick={() => signInWithGoogle("spso")} className="mt-4 w-80 bg-green-700 text-xl p-4">Nhân viên hệ thống đăng nhập</Button>
            <Button onClick={() => signInWithGoogle("admin")} className="mt-4 w-80 bg-green-700 text-xl p-4">Quản trị viên đăng nhập</Button>
        </div>
    );
}