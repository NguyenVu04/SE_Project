'use client';
import { Button } from "@/components/ui/button";
import { use } from "react";
import { useRouter } from "next/navigation";
export default function Home() {
  const router = useRouter();
  const handleSignInClick = () => {
    router.push("/signin");  // Redirect to the sign in page
  };
  return (
    <div className="flex flex-col w-full h-full fixed items-center justify-center" style={{
      background: 'linear-gradient(to bottom, #0381FF, #02067A)'
    }}>
      <h1 className="text-6xl font-bold text-orange-600">Welcome to the Printer Management System</h1>
      <p className="text-white mt-4">Please login to continue</p>
      <Button className="mt-4 bg-orange-600" onClick={handleSignInClick}>Sign In</Button>
    </div>
  );
}
